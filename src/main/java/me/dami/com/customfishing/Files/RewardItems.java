package me.dami.com.customfishing.Files;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;


public class RewardItems {
    public ItemStack item;
    public float DropChance;

    public List<List<Enchantment>> eCombinations = new ArrayList<>();

    public float minXp;
    public float maxXp;

    private boolean canBeEnchanted(Enchantment enchantment){
        if(enchantment.canEnchantItem(this.item))
            return true;
        else
            return false;
    }

}
