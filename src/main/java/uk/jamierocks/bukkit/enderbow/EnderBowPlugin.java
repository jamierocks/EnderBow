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

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.MetricsLite;
import uk.jamierocks.bukkit.enderbow.data.locale.Language;

import java.io.IOException;

/**
 * Created by jamie on 09/01/15.
 */
public class EnderBowPlugin extends JavaPlugin {
    private static final ItemStack enderBow = new ItemStack(Material.BOW) {
        {
            ItemMeta itemMeta = this.getItemMeta();
            itemMeta.setDisplayName("Ender bow");

            this.setItemMeta(itemMeta);
        }
    };
    private static EnderBowPlugin instance;
    
    @Override
    public void onEnable() {
        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException e) {
            getLogger().info(Language.localize("mcstats.failed"));
        }
        
        instance = this;
        
        getServer().getPluginManager().registerEvents(new EnderBowListener(this), this);

        getCommand("enderbow").setExecutor(new EnderBowCommand());

        ShapedRecipe enderBowRecipe = new ShapedRecipe(getEnderBow());
        enderBowRecipe.shape("eee", "ebe", "eee");
        enderBowRecipe.setIngredient('e', Material.ENDER_PEARL);
        enderBowRecipe.setIngredient('b', Material.BOW);
        
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
}
