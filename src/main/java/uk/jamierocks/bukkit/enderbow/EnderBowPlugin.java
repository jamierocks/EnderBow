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

import net.gravitydevelopment.updater.Updater;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.MetricsLite;
import uk.jamierocks.bukkit.enderbow.data.Settings;
import uk.jamierocks.bukkit.enderbow.data.locale.Language;

import java.io.IOException;

/**
 * Created by jamie on 09/01/15.
 */
public class EnderBowPlugin extends JavaPlugin {

    private static final ItemStack enderBow = new EnderBow();
    private static EnderBowPlugin instance;

    @Override
    public void onEnable() {
        if (Settings.isAutoUpdateEnabled()) {
            new Updater(this, 88505, getFile(), Updater.UpdateType.DEFAULT, true);
        }

        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException e) {
            getLogger().info("Failed to submit statistics to MCStats!");
        }

        instance = this;

        // Register the event listener
        getServer().getPluginManager().registerEvents(new EnderBowListener(), this);

        // Register the command
        getCommand("enderbow").setExecutor(new EnderBowCommand());

        // Create the recipe
        ShapedRecipe enderBowRecipe = new ShapedRecipe(getEnderBow());
        enderBowRecipe.shape("eee", "ebe", "eee");
        enderBowRecipe.setIngredient('e', Material.ENDER_PEARL);
        enderBowRecipe.setIngredient('b', Material.BOW);

        // Add the recipe
        getServer().addRecipe(enderBowRecipe);
    }

    @Override
    public void onDisable() {
        getServer().resetRecipes();
    }

    public static ItemStack getEnderBow() {
        return enderBow;
    }

    public static EnderBowPlugin getInstance() {
        return instance;
    }

    public static class EnderBow extends ItemStack {

        public EnderBow() {
            super(Material.BOW);

            // Get and set the ItemMeta
            ItemMeta itemMeta = this.getItemMeta();
            itemMeta.setDisplayName(Language.localize("enderbow.name"));
            this.setItemMeta(itemMeta);
        }
    }
}
