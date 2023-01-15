package me.dami.net.CustomFishing.GUI.Enchants;

import me.dami.net.CustomFishing.FishingClasses.FishingEnchantments;
import me.dami.net.CustomFishing.FishingClasses.FishingItems;
import me.dami.net.CustomFishing.FishingClasses.FishingRegions;
import me.dami.net.CustomFishing.GUI.StaticGUIItems;
import me.dami.net.CustomFishing.Region.FishingRegionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class FishingEnchantGui {


    private static int[] fill = {0,6,8,9,15,17,18,24,26};

    private static int[][] itemList = {{1,2,3,4,5},{10,11,12,13,14},{19,20,21,22,23}};

    public static void OpenGui(Player _p, FishingItems _item, String _region, ChatFishingEnchantState _state) {
        Inventory gui = Bukkit.createInventory(_p, 27, ChatColor.DARK_PURPLE + "Enchant menu");

        _p.openInventory(SetItems(gui,_item,_state));
    }

    public static Inventory SetItems(Inventory inv, FishingItems _item, ChatFishingEnchantState _state){
        List<FishingEnchantments> enchantments = _item.getPossibleEnchants();

        for (int i = 0; i < enchantments.size(); i++) {
            inv.setItem(itemList[i % 3][i / 3], FishingEnchantments.SetDisplay(enchantments.get(i),_state));
        }

        //set items
        for(int slot : fill){
            inv.setItem(slot, StaticGUIItems.white);
        }

        inv.setItem(25, StaticGUIItems.escape);

        inv.setItem(7, StaticGUIItems.reset2);

        //------------------------\\

        ItemStack followUp = new ItemStack(Material.EXPERIENCE_BOTTLE);

        ItemMeta followUp_meta = followUp.getItemMeta();

        ArrayList<String> followUp_lore = new ArrayList<String>();

        followUp_meta.setDisplayName(ChatColor.GOLD + "followUp chance");
        followUp_lore.add("chance of getting more then one enchantment");
        followUp_lore.add("--------------------------------------");
        followUp_lore.add("|  chance = "+ _item.getFollowupChance());

        followUp_meta.setLore(followUp_lore);
        followUp.setItemMeta(followUp_meta);

        //------------------------\\

        inv.setItem(16, followUp);

        return inv;
    }
}
