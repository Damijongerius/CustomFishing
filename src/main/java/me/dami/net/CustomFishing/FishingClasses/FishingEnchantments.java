package me.dami.net.CustomFishing.FishingClasses;

import me.dami.net.CustomFishing.GUI.Enchants.ChatFishingEnchantState;
import me.dami.net.CustomFishing.GUI.FishingGuiManager;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FishingEnchantments {

    //region variables
    private final Enchantment enchant;

    private int[] enchantLevels = new int[] {1};

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

        return map;
    }

    public static ItemStack SetDisplay(FishingEnchantments e, ChatFishingEnchantState _state){
        ItemStack item = new ItemStack(Material.ENCHANTED_BOOK);

        ItemMeta meta = item.getItemMeta();

        meta.setDisplayName(e.getEnchant().getName());

        ArrayList<String> lore = new ArrayList<String>();


        meta.setDisplayName(ChatColor.DARK_PURPLE + "enchantments");
        switch (_state){
            case CHANCE:{
                float[] chance = e.getEnchantChance();
                String underLine = ChatColor.UNDERLINE +  "CHANCE";
                lore.add("| Levels ||" + underLine + "|| Delete |");
                lore.add("-------------------------------------------");
                if(chance[0] < 10 && chance[1] < 10){
                    lore.add("|(lC +1) ||  chance("+ chance[0] +", " + chance[1] + "%)  || (rC -1)|");
                    break;
                }
                if(chance[0] > 10 && chance[1] > 10 && chance[0] < 100 && chance[1] < 100){
                    lore.add("|(lC +1) || chance("+ chance[0] +", " + chance[1] + "%) || (rC -1)|");
                    break;
                }
                if(chance[0] > 10 || chance[1] > 10){
                    lore.add("|(lC +1) || chance("+ chance[0] +", " + chance[1] + "%)  || (rC -1)|");
                    break;
                }
                if(chance[0] > 100){
                    lore.add("|/chance: "+ chance[0] +" || " + chance[1] + "%");
                    lore.add("|/Left Click(+1) || right Click(-1)");
                }
                break;
            }
            case LEVELS:{
                String underLine = ChatColor.UNDERLINE + "LEVELS";
                int[] enchL = e.enchantLevels;
                lore.add("| Delete ||" + underLine + "|| Chance |");
                lore.add("-------------------------------------------");
                lore.add("|(lC +1) || enchantLevels("+ enchL[0] +" tm "+ enchL[1] +")  || (rC -1)|");
                break;
            }
            case DELETE:{
                String underLine = ChatColor.UNDERLINE + "DELETE";
                lore.add("| Chance ||" + underLine + "|| Levels |") ;
                lore.add("-------------------------------------------");
                lore.add("| DELETE ITEM BY PRESSING RIGHTCLICK  |");
                break;
            }
        }

        meta.setLore(lore);
        item.setItemMeta(meta);

        return item;
    }

}
