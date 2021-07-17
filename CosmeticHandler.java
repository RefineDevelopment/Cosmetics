package xyz.damt.mod.util.cosmetics;

import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Getter
public class CosmeticHandler {

    @Getter private static CosmeticHandler instance;

    private final Map<UUID, Cosmetic> map = new HashMap<>();
    private final JavaPlugin javaPlugin;

    public CosmeticHandler(JavaPlugin javaPlugin) {
        instance = this;
        this.javaPlugin = javaPlugin;
    }

    public Cosmetic getCosmetic(UUID uuid) {
        return map.get(uuid);
    }

    public void register(UUID uuid, Cosmetic cosmetic) {
        map.put(uuid, cosmetic);
    }

    public void destroy(UUID uuid) {
        map.remove(uuid);
    }

}
