/*
 * Copyright 2015 Jamie Mansfield <https://github.com/jamierocks>
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
package uk.jamierocks.bukkit.enderbow.api;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import uk.jamierocks.bukkit.enderbow.EnderBowPlugin;

/**
 * Created by jamie on 15/01/15.
 */
public class EntityShootEnderBowEvent extends EntityEvent implements Cancellable {
    private static final HandlerList handlers = new HandlerList();
    private final EnderBowPlugin.EnderBow bow;
    private Projectile projectile;
    private final float force;
    private boolean cancelled;
    
    public EntityShootEnderBowEvent(LivingEntity entity, EnderBowPlugin.EnderBow bow, Projectile projectile, float force) {
        super(entity);
        this.bow = bow;
        this.projectile = projectile;
        this.force = force;
    }
    
    public EntityShootEnderBowEvent(EntityShootBowEvent event) {
        this(event.getEntity(), (EnderBowPlugin.EnderBow) event.getBow(), (Projectile) event.getProjectile(), event
                .getForce());
    }

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) super.getEntity();
    }
    
    public EnderBowPlugin.EnderBow getBow() {
        return bow;
    }
    
    public Projectile getProjectile() {
        return projectile;
    }
    
    public void setProjectile(Projectile projectile){
        this.projectile = projectile;
    }
    
    public float getForce() {
        return force;
    }

    @Override
    public boolean isCancelled() {
        return cancelled;
    }

    @Override
    public void setCancelled(boolean cancel) {
        this.cancelled = cancel;
    }

    @Override
    public HandlerList getHandlers() {
        return handlers;
    }

    public static HandlerList getHandlerList() {
        return handlers;
    }
}
