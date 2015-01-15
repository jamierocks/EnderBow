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
package io.github.lexware.bukkit.enderbow.api;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;

/**
 * Created by jamie on 15/01/15.
 */
public class EntityShootEnderBowEvent extends EntityShootBowEvent {
    public EntityShootEnderBowEvent(EntityShootBowEvent event) {
        super(event.getEntity(), event.getBow(), (Projectile) event.getProjectile(), event.getForce());
    }
    public EntityShootEnderBowEvent(LivingEntity entity, ItemStack bow, Projectile projectile, float force) {
        super(entity, bow, projectile, force);
    }
}
