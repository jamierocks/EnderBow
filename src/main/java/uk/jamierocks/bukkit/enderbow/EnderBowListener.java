/*
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
package uk.jamierocks.bukkit.enderbow;

import org.bukkit.Sound;
import uk.jamierocks.bukkit.enderbow.api.EntityShootEnderBowEvent;
import org.bukkit.entity.EnderPearl;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;

/**
 * Created by jamie on 09/01/15.
 */
public class EnderBowListener implements Listener {
    private final EnderBowPlugin plugin;

    public EnderBowListener(EnderBowPlugin plugin) {
        this.plugin = plugin;
    }
    
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityShootBowEvent(EntityShootBowEvent event) {
        if(event.getBow().hasItemMeta() && event.getBow().getItemMeta().getDisplayName().equals("Ender bow")) {
            EntityShootEnderBowEvent entityShootEnderBowEvent = new EntityShootEnderBowEvent(event);
            plugin.getServer().getPluginManager().callEvent(entityShootEnderBowEvent);
            if(!entityShootEnderBowEvent.isCancelled()) {
                event.getEntity().launchProjectile(EnderPearl.class).setVelocity(event.getProjectile().getVelocity());
                event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
                event.setCancelled(true);
            }
        }
    }
}
