package xyz.damt.mod.util.cosmetics.texture;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;

public interface Texture {

    void particle(JavaPlugin javaPlugin, Location location, Effect effect, int amount, int radius, int seconds);
    void stop();

}
