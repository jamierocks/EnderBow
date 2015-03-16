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
            getLogger().info(Language.localize("mcstats.failed"));
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
