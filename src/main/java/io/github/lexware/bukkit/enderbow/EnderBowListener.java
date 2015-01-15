/**
 * Copyright 2015 Jamie Mansfield <https://github.com/lexware>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.github.lexware.bukkit.enderbow;

import io.github.lexware.bukkit.enderbow.api.EnderArrowHitEvent;
import io.github.lexware.bukkit.enderbow.api.EntityShootEnderBowEvent;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

/**
 * Created by jamie on 09/01/15.
 */
public class EnderBowListener implements Listener {
    private final EnderBowPlugin plugin;

    public EnderBowListener(EnderBowPlugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler
    public void onEntityShootBowEvent(EntityShootBowEvent event) {
        if(event.getBow().hasItemMeta() && event.getBow().getItemMeta().getDisplayName().equals("Ender bow")) {
            EntityShootEnderBowEvent entityShootEnderBowEvent = new EntityShootEnderBowEvent(event);
            plugin.getServer().getPluginManager().callEvent(entityShootEnderBowEvent);
            if(!entityShootEnderBowEvent.isCancelled()) {
                entityShootEnderBowEvent.getProjectile().setMetadata("enderBowData", new FixedMetadataValue(plugin, "enderArrow"));
            }
        }
    }
    
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if(event.getEntity().hasMetadata("enderBowData")) {
            for(MetadataValue value : event.getEntity().getMetadata("enderBowData")) {
                if(value.asString().equals("enderArrow")) {
                    if(event.getEntity().getShooter() instanceof Entity) {
                        EnderArrowHitEvent enderArrowHitEvent = new EnderArrowHitEvent(event.getEntity());
                        plugin.getServer().getPluginManager().callEvent(enderArrowHitEvent);
                        if(!enderArrowHitEvent.isCancelled()) {
                            ((Entity)event.getEntity().getShooter()).teleport(enderArrowHitEvent.getProjectile().getLocation());
                        }
                    }
                }
            }
        }
    }
}
