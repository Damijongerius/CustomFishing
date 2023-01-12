package me.dami.net.CustomFishing.FishingClasses;

import org.bukkit.enchantments.Enchantment;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FishingEnchantments {

    //region variables
    private final Enchantment enchant;

    private int[] enchantLevels = new int[] {1};

    private List<FishingEnchantments> canBePairedWith = new ArrayList<>();

    private float[] enchantChance = new float[] {5,0};
    //endregion

    public FishingEnchantments(Enchantment _e){
        this.enchant = _e;
    }

    //region getters and setters
    public Enchantment getEnchant(){
        return this.enchant;
    }

    public int[] getEnchantLevels(){
        return this.enchantLevels;
    }

    public void setEnchantLevels(int[] _levels){
        this.enchantLevels = _levels;
    }

    public List<FishingEnchantments> getAvailablePairing(){
        return canBePairedWith;
    }

    public void addAvailiblePairing(FishingEnchantments _fishingE, boolean _done){
        this.canBePairedWith.add(_fishingE);
        if(_done){
            return;
        }
        _fishingE.addAvailiblePairing(this,true);
    }

    public float[] getEnchantChance(){
        return this.enchantChance;
    }

    public void setEnchantChance(float[] _enchantC){
        this.enchantChance = _enchantC;
    }
    //endregion

    public Map<Enchantment, Integer> getItemEnchants(){
        //set item enchants
        return null;
    }

    public Map<String,Object> Serialize(){
        Map<String,Object> map = new LinkedHashMap<>();

        map.put("Enchant", enchant.getName());
        map.put("Levels", enchantLevels);
        map.put("enchantChance", enchantChance);

        List<String> pairedEnchants = new ArrayList<>();
        for(FishingEnchantments enchantment : canBePairedWith){
            pairedEnchants.add(enchantment.getEnchant().getName());
        }

        return map;
    }

}
