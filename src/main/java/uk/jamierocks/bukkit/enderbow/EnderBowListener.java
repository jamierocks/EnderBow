/*
 * This file is part of EnderBow, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2015-2016, Jamie Mansfield <https://www.jamierocks.uk/>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package uk.jamierocks.bukkit.enderbow;

import org.bukkit.Sound;
import org.bukkit.entity.EnderPearl;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import uk.jamierocks.bukkit.enderbow.api.event.EntityShootEnderBowEvent;

/**
 * Created by jamie on 09/01/15.
 */
public class EnderBowListener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityShootBowEvent(EntityShootBowEvent event) {
        if ((event.getBow().hasItemMeta() && event.getBow().getItemMeta().getDisplayName().equals("Ender bow")) || event
                .getBow() instanceof EnderBowPlugin.EnderBow) {
            // Create custom event
            EntityShootEnderBowEvent entityShootEnderBowEvent = new EntityShootEnderBowEvent(event);

            // Call the custom event
            EnderBowPlugin.getInstance().getServer().getPluginManager().callEvent(entityShootEnderBowEvent);

            // Do the following, if it wasn't cancelled
            if (!entityShootEnderBowEvent.isCancelled()) {
                // Fire an ender pearl
                event.getEntity().launchProjectile(EnderPearl.class).setVelocity(event.getProjectile().getVelocity());

                // Play the 'ENDERMAN_TELEPORT' sound
                event.getEntity().getWorld().playSound(event.getEntity().getLocation(), Sound.ENDERMAN_TELEPORT, 1, 1);
            }

            // Cancel the original Event, so no arrows are fired
            event.setCancelled(true);
        }
    }
}
