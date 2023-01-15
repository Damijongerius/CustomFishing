package me.dami.net.CustomFishing.GUI.Main;

import me.dami.net.CustomFishing.FishingClasses.FishingItems;
import me.dami.net.CustomFishing.FishingClasses.FishingRegions;
import me.dami.net.CustomFishing.GUI.StaticGUIItems;
import me.dami.net.CustomFishing.Region.FishingRegionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FishingMenuGui {

    static int orderW[] = {0,1,2,3,4,5,6,7,8,9,17,18,19,20,21,22,23,24,25,26};

    static int orderL[] = {27,36,45};
    static int orderR[] = {35,44,53};

    static int[][] rows = {{28,29,30,31,32,33,34},{37,38,39,40,41,42,43},{46,47,48,49,50,51,52}};

    public static void OpenGui(Player _p, String _region){
        Inventory gui = Bukkit.createInventory(_p, 54, ChatColor.AQUA + "Fishing Menu");

        _p.openInventory(SetItems(gui,_region, 0));

    }

    public static Inventory SetItems(Inventory _inv, String _region, Integer start){

        FishingRegions fishingRegion = FishingRegionManager.getFishingRegion(_region);

        List<FishingItems> items = fishingRegion.getItems();

        for(int i = start; i < items.size(); i++){
            int indexX = (i - start) % 3;
            int indexY = (i - start) / 3;
            _inv.setItem(rows[indexX][indexY],SetDisplay(items.get(i)));
        }


        for (int index : orderW) {
            _inv.setItem(index, StaticGUIItems.white);
        }

        for(int index : orderL){
            _inv.setItem(index,StaticGUIItems.navigateLeft);
        }

        for(int index : orderR){
            _inv.setItem(index,StaticGUIItems.navigateRight);
        }

        _inv.setItem(13, GetRegionItem(_region));

        _inv.setItem(15, StaticGUIItems.reset);

        _inv.setItem(11, getStatus(fishingRegion));

        return _inv;

    }

    private static ItemStack getStatus(FishingRegions fishingRegion){
        switch (fishingRegion.getStatus()) {
            case ON: return StaticGUIItems.statusOn;
            case OFF: return StaticGUIItems.statusOff;
            case NO: return StaticGUIItems.statusNo;
        }
        return null;
    }

    private static ItemStack GetRegionItem(String _region){
        ItemStack region = new ItemStack(Material.LEATHER_HELMET);
        //------------------------\\

        ItemMeta region_meta = region.getItemMeta();

        region_meta.setDisplayName(_region);
        region_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        region_meta.setLore(null);
        region.setItemMeta(region_meta);

        //------------------------\\
        return region;
    }

    public static ItemStack SetDisplay(FishingItems _item){

        ItemStack DisplayItem = _item.getItem().clone();

        ItemMeta DisplayItem_meta = DisplayItem.getItemMeta();

        ArrayList<String> DisplayItem_lore = new ArrayList<>();

        int[] amounts = _item.getItemAmount();
        float[] xp = _item.getXpRange();
        DisplayItem_lore.add("Amount: ( " + amounts[0] + " , " + amounts[1] + " )");
        DisplayItem_lore.add("DropChance: " + _item.getDropChance()[0]);
        DisplayItem_lore.add("XpDrop: ( " + xp[0] + " , " + xp[1] + ")");
        DisplayItem_meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);

        DisplayItem_meta.setLore(DisplayItem_lore);
        DisplayItem.setItemMeta(DisplayItem_meta);

        return DisplayItem;
    }
}
