package io.github.lexware.bukkit.enderbow;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Projectile;
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
            event.getProjectile().setMetadata("enderBowData", new FixedMetadataValue(plugin, "enderArrow"));
        }
    }
    
    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if(event.getEntity().hasMetadata("enderBowData")) {
            for(MetadataValue value : event.getEntity().getMetadata("enderBowData")) {
                if(value.asString().equals("enderArrow")) {
                    ((Entity)event.getEntity().getShooter()).teleport(event.getEntity().getLocation());
                }
            }
        }
    }
}
