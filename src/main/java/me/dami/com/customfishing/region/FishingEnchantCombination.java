package me.dami.com.customfishing.region;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.enchantments.Enchantment;

import java.util.LinkedHashMap;
import java.util.Map;

public class FishingEnchantCombination implements ConfigurationSerializable {

    private Map<Enchantment,Integer> enchantComb;

    private float Chance;

    private float ProcentChance;


    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> object = new LinkedHashMap<>();

        object.put("enchantments", enchantComb);
        object.put("chance", Chance);
        object.put("procentChance", ProcentChance);

        return object;
    }

    public Map<Enchantment, Integer> getEnchantComb() {
        return enchantComb;
    }

    public void AddEnchantComb(Enchantment ench, int numb){
        this.enchantComb.put(ench,numb);
    }

    public void RemoveEnchantComb(Enchantment ench, int numb){
        this.enchantComb.remove(ench,numb);
    }

    public void setEnchantComb(Map<Enchantment, Integer> enchantComb) {
        this.enchantComb = enchantComb;
    }

    public float getChance() {
        return Chance;
    }

    public void setChance(float chance) {
        Chance = chance;
    }

    public float getProcentChance() {
        return ProcentChance;
    }

    public void setProcentChance(float procentChance) {
        ProcentChance = procentChance;
    }
}
