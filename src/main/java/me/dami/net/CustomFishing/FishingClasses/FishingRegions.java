package me.dami.net.CustomFishing.FishingClasses;

import org.antlr.v4.runtime.misc.NotNull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

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

            Double[] dropChances = (Double[]) (item.get("dropChance"));
            Double[] xpRange = (Double[]) item.get("Xp");
            int[] dropAmounts = (int[]) item.get("dropAmounts");

            newItem.setDropChance(new float[] {dropChances[0].floatValue(),dropChances[1].floatValue()});
            newItem.setXpRange(new float[] {xpRange[0].floatValue(), xpRange[1].floatValue()});
            newItem.setItemAmount(dropAmounts);

            this.items.add(newItem);
        }
    }

    public FishingRegions(){

    }

    public FishingItems GetRandomItem(){

        return null;
    }

    public List<FishingItems> getItems(){
        return items;
    }

    public List<FishingItems> getItems(int iIndex, int jIndex) {
        List<FishingItems> items = new ArrayList<>();
        for (int i = iIndex; i < jIndex && i < this.items.size(); i++) {
            items.add(this.items.get(i));
        }
        return items;
    }


    public void AddItem(FishingItems _item) {
        this.items.add(_item);

        totalDrop += _item.getDropChance()[0];
        CalculateDropChances();
    }

    public void ClearItems(){
        items.clear();
    }

    public void CalculateDropChances(){
        for(FishingItems item : items){
            item.setDropPercent(totalDrop);
            System.out.println(item.getItem().getType());
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
