package me.dami.com.customfishing.region;

import org.antlr.v4.runtime.misc.NotNull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FishingItem implements ConfigurationSerializable {

    private ItemStack item;
    private List<FishingEnchantCombination> enchantCombinations;

    private float minXp;
    private float maxXp;

    private float dropchance;
    private float procentDropChance;
    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("Item", item.serialize());
        map.put("minXp", minXp);
        map.put("maxXp", maxXp);
        map.put("dropChance", dropchance);
        map.put("procentDropChance", procentDropChance);
        map.put("enchantCombinations", enchantCombinations);
        return null;
    }


    public FishingItem(@NotNull ItemStack item){
        Map<Enchantment,Integer> enchant = item.getEnchantments();
    }

    //region Getter and setter

    public ItemStack getItem() {
        return item;
    }


    public void setItem(@NotNull ItemStack item) {
        this.item = item;
    }

    public List<FishingEnchantCombination> getEnchantCombinations() {
        return enchantCombinations;
    }

    public void addEnchantCombination(@NotNull FishingEnchantCombination enchants) {
        this.enchantCombinations.add(enchants);
    }

    public void RemoveEnchantCombination(@NotNull FishingEnchantCombination enchants){
        this.enchantCombinations.remove(enchants);
    }

    public float[] getXp(boolean type) {
        if(type)
            return new float[] {(float) (minXp + Math.random() * (maxXp - minXp))};
        return new float[] {minXp,maxXp};
    }

    public void setXp(@NotNull float Xp) {
        this.minXp = Xp - 10;
        this.maxXp = Xp + 10;
    }

    public float getDropchance() {
        return dropchance;
    }

    public void setDropchance(@NotNull float dropchance, float Total) {
        this.dropchance = dropchance;

        procentDropChance = (dropchance / Total) * 100;
    }

    public float getProcentDropChance() {
        return procentDropChance;
    }

    //endregion
}
