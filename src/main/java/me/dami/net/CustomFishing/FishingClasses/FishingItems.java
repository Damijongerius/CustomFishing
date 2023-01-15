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

    private boolean growChance = false;
    private float growWith = 2.5f; //chance grows 2.5 with each effect (keep in mind that is on de drop chance not percent)

    private List<FishingEnchantments> possibleEnchants = new ArrayList<>();
    private float followupChance = 25;

    //end region


    public FishingItems(ItemStack _item){

        Map<Enchantment, Integer> enchantments = _item.getEnchantments();
        if (!enchantments.isEmpty()) {
            for (Map.Entry<Enchantment, Integer> enchantment : enchantments.entrySet()) {
                _item.removeEnchantment(enchantment.getKey());
                FishingEnchantments newEnchantment = new FishingEnchantments(enchantment.getKey());
                newEnchantment.setEnchantLevels(new int[] {1,enchantment.getValue()});
                possibleEnchants.add(newEnchantment);
            }
        }

        this.item = _item;
    }

    //region getters and setters
    public ItemStack getItem() {
        return this.item;
    }

    public float[] getXpRange() {
        return xpRange;
    }

    public void setXpRange(float[] xpRange) {
        if(xpRange[0] > xpRange[1]){
            this.xpRange = new float[] {xpRange[0],xpRange[0]};
            return;
        }
        this.xpRange = xpRange;
    }

    public boolean isGrowChance() {
        return growChance;
    }

    public void setGrowChance(boolean growChance) {
        this.growChance = growChance;
    }

    public int[] getItemAmount() {
        return itemAmount;
    }

    public void setItemAmount(int[] itemAmount) {
        if(itemAmount[0] > itemAmount[1]){
            this.itemAmount = new int[] {itemAmount[0],itemAmount[0]};
            return;
        }
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

    public void removePossibleEnchant(FishingEnchantments enchantment) {
        this.possibleEnchants.remove(enchantment);
    }

    public void resetPossibleEnchants(){
        this.possibleEnchants = null;
    }

    public List<FishingEnchantments> getPossibleEnchants(){
        return this.possibleEnchants;
    }

    public float getFollowupChance() {
        return followupChance;
    }

    public void setFollowupChance(float followupChance) {
        this.followupChance = followupChance;
    }
    //endregion

    public int getRandomAmount(){
        return (int) Math.round(Math.random() * (itemAmount[1] - itemAmount[0]) + itemAmount[0]);
    }

    public int getRandomxp(){
        return (int) Math.round(Math.random() * (xpRange[1] - xpRange[0]) + xpRange[0]);
    }

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
