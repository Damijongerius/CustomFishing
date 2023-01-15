package me.dami.net.CustomFishing.FishingClasses;

import org.antlr.v4.runtime.misc.NotNull;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FishingRegions {

    //region Variables
    private List<FishingItems> items = new ArrayList<>();
    private float totalDrop; //total of all items drop;

    private FishingStatus status = FishingStatus.OFF;

    private float enchantChance; //chance to get an enchant on it
    private float dropChance; //chance to get something

    //end region

    public FishingRegions(ConfigurationSection _section){

        this.status = FishingStatus.valueOf(_section.getString("status"));

        List<Map<String,Object>> items = (List<Map<String, Object>>) _section.get("items");
        for(Map<String, Object> item : items){
            Map.Entry<String,Object> entry = item.entrySet().iterator().next();
            FishingItems newItem = new FishingItems(ItemStack.deserialize((Map<String, Object>) entry.getValue()));

            ArrayList<Double> dropChances = (ArrayList<Double>) item.get("dropChance");
            ArrayList<Double> xpRange = (ArrayList<Double>) item.get("Xp");
            ArrayList<Integer> dropAmounts = (ArrayList<Integer>) item.get("dropAmounts");

            newItem.setDropChance(new float[] {dropChances.get(0).floatValue(),dropChances.get(1).floatValue()});
            newItem.setXpRange(new float[] {xpRange.get(0).floatValue(),xpRange.get(1).floatValue()});
            newItem.setItemAmount(new int[] {dropAmounts.get(0),dropAmounts.get(1)});

            this.items.add(newItem);
        }
    }

    public FishingRegions(){

    }

    public FishingItems GetRandomItem(){
        double randomNum = (Math.random() * 10000) / 100;
        System.out.println("random number:" + randomNum);

        double drop = 0;
        for (FishingItems item : items){
            drop += item.getDropChance()[1];
            System.out.println(drop);
            if(drop > randomNum){
                return item;
            }
        }
        return items.get(0);
    }

    public List<FishingItems> getItems(){
        return this.items;
    }

    public List<FishingItems> getItems(int iIndex, int jIndex) {
        List<FishingItems> newItems = new ArrayList<>();
        for (int i = iIndex; i < jIndex && i < this.items.size(); i++) {
            newItems.add(this.items.get(i));
        }
        return newItems;
    }

    public void RemoveItem(FishingItems _item){
        this.items.remove(_item);
        this.totalDrop -= _item.getDropChance()[0];
        CalculateDropChances();
    }

    public FishingItems getItemWithMaterial(Material _material){
        for(FishingItems item : items) if(item.getItem().getType() == _material) return item;
        return null;
    }


    public void AddItem(FishingItems _item) {
        this.items.add(_item);
        this.totalDrop += _item.getDropChance()[0];
        CalculateDropChances();
    }

    public void ClearItems(){
        items.clear();
    }

    public void CalculateDropChances(){
        for(FishingItems item : items){
            item.setDropPercent(totalDrop);
        }
    }

    public FishingStatus getStatus() {
        return status;
    }

    public void setStatus(FishingStatus _status) {
        status = _status;
    }

    public Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Map<String,Object>> newItems = new ArrayList<>();
        for (FishingItems fi : items) {
            newItems.add(fi.serialize());
        }
        map.put("items", newItems);
        map.put("status", status.toString());
        map.put("totalDrop", totalDrop);
        return map;
    }

}
