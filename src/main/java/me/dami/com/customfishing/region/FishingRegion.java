package me.dami.com.customfishing.region;

import org.antlr.v4.runtime.misc.NotNull;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerializable;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FishingRegion implements ConfigurationSerializable {

    private List<FishingItem> items = new ArrayList<>();

    private FishingStage stage;

    public FishingRegion(List<FishingItem> _items, boolean _stage) {
        if(_items != null){
            items = _items;
            stage = FishingStage.ON;
        }else{
            stage = FishingStage.OFF;
        }
    }

    public  FishingRegion(){
        stage = FishingStage.OFF;
    }

    public FishingRegion(ConfigurationSection _section){
        stage = FishingStage.valueOf(_section.getString("stage"));

        System.out.println(_section.get("items"));

    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new LinkedHashMap<>();
        List<Map<String,Object>> newItems = new ArrayList<>();
        for (FishingItem fi : items) {
            newItems.add(fi.serialize());
        }
        map.put("items", newItems);
        map.put("stage", stage.toString());
        return map;
    }


    public List<FishingItem> getItems() {
        return items;
    }

    public void AddItem(@NotNull FishingItem _item) {
        this.items.add(_item);
    }

    public void RemoveItem(@NotNull FishingItem _item) {
        this.items.remove(_item);
    }

    public void SetItems(@NotNull List<FishingItem> _items) {
        if(_items != this.items){
            this.items = _items;
        }
    }

    public void ClearItems(){
        items.clear();
    }

    public FishingStage getStage() {
       return stage;
    }

    public void setStage(@NotNull FishingStage _stage) {
        stage = _stage;
    }



}
