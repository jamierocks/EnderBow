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
package uk.jamierocks.bukkit.enderbow.api.event;

import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by jamie on 15/01/15.
 */
public class EntityShootEnderBowEvent extends EntityEvent implements Cancellable {

    private static final HandlerList handlers = new HandlerList();
    private final ItemStack bow;
    private final float force;
    private boolean cancelled;

    public EntityShootEnderBowEvent(LivingEntity entity, ItemStack bow, float force) {
        super(entity);
        this.bow = bow;
        this.force = force;
    }

    public EntityShootEnderBowEvent(EntityShootBowEvent event) {
        this(event.getEntity(), event.getBow(), event.getForce());
    }

    @Override
    public LivingEntity getEntity() {
        return (LivingEntity) super.getEntity();
    }

    /**
     * Gets the {@code ItemStack} used to fire the arrow.
     *
     * @return the {@code ItemStack} involved in this event
     */
    public ItemStack getBow() {
        return bow;
    }

    /**
     * Gets the force the ender pearl was launched with
     *
     * @return ender pearl shooting force, up to 1.0
     */
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
