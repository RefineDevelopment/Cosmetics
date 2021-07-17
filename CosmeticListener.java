package xyz.damt.mod.util.cosmetics;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.PlayerInventory;
import xyz.damt.mod.util.cosmetics.enums.CosmeticType;

public class CosmeticListener {

    private final CosmeticHandler cosmeticHandler;

    public CosmeticListener(CosmeticHandler cosmeticHandler) {
        this.cosmeticHandler = cosmeticHandler;
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        Cosmetic cosmetic = cosmeticHandler.getCosmetic(event.getPlayer().getUniqueId());

        if (cosmetic == null) return;

        cosmetic.remove();
        cosmeticHandler.destroy(event.getPlayer().getUniqueId());
    }

    @EventHandler
    public void onPlayerInventoryClickEvent(InventoryClickEvent event) {
        if (event.getClickedInventory() == null ||
        !(event.getClickedInventory() instanceof PlayerInventory)) return;

        Player player = (Player) event.getWhoClicked();

        Cosmetic cosmetic = cosmeticHandler.getCosmetic(player.getUniqueId());

        if (cosmetic == null || !cosmetic.getCosmeticType().equals(CosmeticType.ARMOR)
        || !event.getSlotType().equals(InventoryType.SlotType.ARMOR)) return;

        event.setCancelled(true);
    }

}
