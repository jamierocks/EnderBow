package io.github.lexware.bukkit.enderbow;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.mcstats.MetricsLite;

import java.io.IOException;

/**
 * Created by jamie on 09/01/15.
 */
public class EnderBowPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new EnderBowListener(this), this);

        ItemStack enderBow = new ItemStack(Material.BOW);
        ItemMeta itemMeta = enderBow.getItemMeta();
        itemMeta.setDisplayName("Ender bow");
        enderBow.setItemMeta(itemMeta);
        ShapedRecipe enderBowRecipe = new ShapedRecipe(enderBow);
        enderBowRecipe.shape("eee", "ebe", "eee");
        enderBowRecipe.setIngredient('e', Material.ENDER_PEARL);
        enderBowRecipe.setIngredient('b', Material.BOW);
        
        getServer().addRecipe(enderBowRecipe);

        try {
            MetricsLite metrics = new MetricsLite(this);
            metrics.start();
        } catch (IOException e) {
            getLogger().info("Couldn't send Metrics data.");
        }
    }
    
    @Override
    public void onDisable() {
        getServer().resetRecipes();
    }
}
