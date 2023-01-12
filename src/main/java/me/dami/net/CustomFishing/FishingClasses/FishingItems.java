package me.dami.net.CustomFishing.FishingClasses;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FishingItems {

    //region Variables
    private final ItemStack item;

    private float[] xpRange = new float[] {20,100};

    private int[] itemAmount = new int[] {1,1};

    private float[] dropChance = new float[] {10,0};

    private List<FishingEnchantments> possibleEnchants = new ArrayList<>();

    //end region


    public FishingItems(ItemStack _item){
        Map<Enchantment, Integer> enchantments = _item.getEnchantments();
        if (!enchantments.isEmpty()) {
            List<FishingEnchantments> pair = new ArrayList<>();
            for (Map.Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
                _item.removeEnchantment(enchantment.getKey());
                FishingEnchantments newEnchantment = new FishingEnchantments(enchantment.getKey());
                newEnchantment.setEnchantLevels(new int[] {1,enchantment.getValue()});
                possibleEnchants.add(newEnchantment);
                pair.add(newEnchantment);
            }

            for(FishingEnchantments enchantment : pair){
                for(FishingEnchantments enchantment1 : pair){
                    if(enchantment1 != enchantment){
                        enchantment.addAvailiblePairing(enchantment1, true);
                    }
                }
            }
        }

        this.item = _item;
    }

    //region getters and setters
    public ItemStack getItem() {
        return item.clone();
    }

    public float[] getXpRange() {
        return xpRange;
    }

    public void setXpRange(float[] xpRange) {
        this.xpRange = xpRange;
    }

    public int[] getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int[] itemAmount) {
        this.itemAmount = itemAmount;
    }

    public float[] getDropChance() {
        return dropChance;
    }

    public void setDropPercent(float total){
        this.dropChance[1] = (this.dropChance[0] / total) * 100;
    }

    public void setDropChance(float[] dropChance) {
        this.dropChance = dropChance;
    }

    public void addPossibleEnchant(FishingEnchantments enchantment){
        this.possibleEnchants.add(enchantment);
    }

    public List<FishingEnchantments> getPossibleEnchants(){
        return this.possibleEnchants;
    }
    //endregion

    public Map<String,Object> serialize(){
        Map<String, Object> map = new LinkedHashMap<>();

        map.put("Item", item.serialize());
        map.put("Xp", xpRange);
        map.put("dropChance", dropChance);
        map.put("dropAmounts", itemAmount);

        List<Map<String,Object>> enchantments = new ArrayList<>();
        for(FishingEnchantments fishingEnchants : possibleEnchants){
            enchantments.add(fishingEnchants.Serialize());
        }

        return map;
    }






}