package xyz.damt.mod.util.cosmetics.texture.impl;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitTask;
import xyz.damt.mod.util.cosmetics.texture.Texture;

public class Triangle implements Texture {

    private int pointOne, pointTwo, pointThree;
    private BukkitTask bukkitTask;

    @Override
    public void particle(JavaPlugin javaPlugin, Location location, Effect effect, int amount, int radius, int seconds) {
        if (location == null)
            throw new IllegalArgumentException("The location may not be null!");

        this.setTriangle(location, radius);

        bukkitTask = Bukkit.getScheduler().runTaskTimer(javaPlugin, () -> {
            for (int z = 0; z < pointOne; z++) {
                for (int x = pointThree; x < pointTwo; x++) {
                    Location newLocation = location.add(x, 0, z);
                    newLocation.getWorld().playEffect(newLocation, Effect.CRIT, null, amount);
                }
            }
        }, seconds * 20L, seconds * 20L);

        Bukkit.getScheduler().runTaskLaterAsynchronously(javaPlugin, () ->
                bukkitTask.cancel(), seconds * 20L);
    }

    @Override
    public void stop() {
        bukkitTask.cancel();
    }

    private final void setTriangle(Location location, int radius) {

        pointOne = Math.max(radius, location.getBlockZ());
        pointTwo = Math.min(radius, location.getBlockX());
        pointThree = Math.min(-radius, location.getBlockX());

    }

}
