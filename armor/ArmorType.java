package xyz.damt.mod.util.cosmetics.armor;

import lombok.Getter;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

@Getter
public enum ArmorType {

    RED_LEATHER(Color.RED, Material.LEATHER);

    private final Material material;
    private final Color color;

    ArmorType(Color color, Material material) {
        this.material = material;
        this.color = color;
    }

    public void equip(Player player) {
        ItemStack[] stacks = toStack();

        for (int i = 0; i < stacks.length; i++) {
            ItemStack stack = stacks[i];

            if (color != null) {
                if (material.toString().equalsIgnoreCase("LEATHER"))
                    throw new IllegalArgumentException("Anything that is not leather may not be applied!");

                LeatherArmorMeta leatherArmorMeta = (LeatherArmorMeta) stack.getItemMeta();
                leatherArmorMeta.setColor(color);

                stack.setItemMeta(leatherArmorMeta);
            }
        }

        player.getInventory().setArmorContents(stacks);
    }

    public ItemStack[] toStack() {
        final String materialName = material.toString().toUpperCase();
        final ItemStack[] stacks = new ItemStack[4];

        stacks[0] = new ItemStack(Material.valueOf(materialName + "_HELMET"));
        stacks[1] = new ItemStack(Material.valueOf(materialName + "_CHESTPLATE"));
        stacks[2] = new ItemStack(Material.valueOf(materialName + "_LEGGINGS"));
        stacks[3] = new ItemStack(Material.valueOf(materialName + "_BOOTS"));

        return stacks;
    }

}
