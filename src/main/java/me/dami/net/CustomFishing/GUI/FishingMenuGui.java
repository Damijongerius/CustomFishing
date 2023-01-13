package me.dami.net.CustomFishing.GUI;

import me.dami.net.CustomFishing.FishingClasses.FishingItems;
import me.dami.net.CustomFishing.FishingClasses.FishingRegions;
import me.dami.net.CustomFishing.Region.FishingRegionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;
import java.util.Map;

public class FishingMenuGui {

    static int orderW[] = {0,1,2,3,4,5,6,7,8,9,17,18,19,20,21,22,23,24,25,26};

    static int orderL[] = {27,36,45};
    static int orderR[] = {35,44,53};

    static int[][] rows = {{28,29,30,31,32,33,34},{37,38,39,40,41,42,43},{46,47,48,49,50,51,52}};

    public static void OpenGui(Player _p, String _region){
        Inventory gui = Bukkit.createInventory(_p, 54, ChatColor.AQUA + "Fishing Menu");
        FishingRegions fishingRegion = FishingRegionManager.getFishingRegion(_region);

        List<FishingItems> items = fishingRegion.getItems();

        for(int i = 0; i < items.size(); i++){
            int indexX = i % 3;
            int indexY = i / 3;
            gui.setItem(rows[indexX][indexY],items.get(i).getItem().clone());
        }


        for (int index : orderW) {
            gui.setItem(index, StaticGUIItems.white);
        }

        for(int index : orderL){
            gui.setItem(index,StaticGUIItems.navigateLeft);
        }

        for(int index : orderR){
            gui.setItem(index,StaticGUIItems.navigateRight);
        }

        gui.setItem(13, GetRegionItem(_region));

        gui.setItem(15, StaticGUIItems.reset);

        gui.setItem(11, getStatus(fishingRegion));

        _p.openInventory(gui);

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

    private void SetItems(Map<Integer,ItemStack> _guiItems){

    }
}
