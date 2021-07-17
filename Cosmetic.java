package xyz.damt.mod.util.cosmetics;

import lombok.Getter;
import org.bukkit.Effect;
import org.bukkit.entity.Player;
import xyz.damt.mod.util.cosmetics.armor.ArmorType;
import xyz.damt.mod.util.cosmetics.enums.CosmeticType;
import xyz.damt.mod.util.cosmetics.texture.Texture;

public class Cosmetic {

    @Getter private final Player player;
    @Getter private final CosmeticType cosmeticType;

    private Texture texture;
    private ArmorType armorType;

    public Cosmetic(Player player, CosmeticType cosmeticType) {
        this.player = player;
        this.cosmeticType = cosmeticType;

        CosmeticHandler.getInstance().register(player.getUniqueId(), this);
    }

    public void playParticle(Effect effect, Texture texture, int seconds, int radius, int amountOfParticles) {
        if (!cosmeticType.equals(CosmeticType.PARTICLE)) return;

        this.texture = texture;

        texture.particle(CosmeticHandler.getInstance().getJavaPlugin(),
                player.getLocation(), effect, amountOfParticles, radius, seconds);
    }

    public void equipArmor(ArmorType armorType) {
        if (!cosmeticType.equals(CosmeticType.ARMOR)) return;

        this.armorType = armorType;

        armorType.equip(player);
    }

    public void remove() {
        switch (cosmeticType) {
            case PARTICLE:
                texture.stop();
                break;
            case ARMOR:
                player.getInventory().setArmorContents(null);
                break;
            default:
                break;
        }
    }


}
