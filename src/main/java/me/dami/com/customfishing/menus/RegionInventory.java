package me.dami.com.customfishing.menus;

import me.dami.com.customfishing.Files.ConfigManager;
import me.dami.com.customfishing.region.FishingRegion;
import me.dami.com.customfishing.region.FishingStage;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class RegionInventory{

    //region Static Navigation Items
    ItemStack standard = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);

    ItemStack possibleL = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
    ItemStack possibleR =  new ItemStack(Material.GREEN_STAINED_GLASS_PANE);

    ItemStack statusOff = new ItemStack(Material.LEVER);
    ItemStack statusOn = new ItemStack(Material.TORCH);
    ItemStack statusNo = new ItemStack(Material.BARRIER);

    ItemStack reset = new ItemStack(Material.REDSTONE_TORCH);

    int orderW[] = {0,1,2,3,4,5,6,7,8,9,17,18,19,20,21,22,23,24,25,26};

    int orderL[] = {27,36,45};
    int orderR[] = {35,44,53};

    //endregion



    public void OpenGui(Player player, String _region){
        FishingRegion fr = ConfigManager.getFishingRegion(_region);
        Inventory gui = Bukkit.createInventory(player, 54, ChatColor.AQUA + "Fishing Menu");


        ItemStack region = new ItemStack(Material.LEATHER_HELMET);

        ItemStack VisibleItems[][] = new ItemStack[3][7];
        List<ItemStack> Items = new ArrayList<>();

        //region Customize Items

        //------------------------\\


        ItemMeta standard_meta = standard.getItemMeta();

        standard_meta.setDisplayName(ChatColor.RED + "-");

        standard_meta.setLore(null);
        standard.setItemMeta(standard_meta);

        //------------------------\\

        ItemMeta possibleL_meta = possibleL.getItemMeta();

        possibleL_meta.setDisplayName(ChatColor.YELLOW + "Left");

        possibleL_meta.setLore(null);
        possibleL.setItemMeta(possibleL_meta);

        //------------------------\\

        ItemMeta possibleR_meta = possibleR.getItemMeta();

        possibleR_meta.setDisplayName(ChatColor.GREEN + "Right");

        possibleR_meta.setLore(null);
        possibleR.setItemMeta(possibleR_meta);

        //------------------------\\

        ItemMeta statusOff_meta = statusOff.getItemMeta();

        ArrayList<String> statusOff_lore = new ArrayList<String>();

        statusOff_meta.setDisplayName(ChatColor.GRAY + "Disabled Fishing");
        statusOff_lore.add("Custom fishing is disabled here.");
        statusOff_lore.add("Meaning players can't fish custom here.");
        statusOff_lore.add("click item to turn it back on.");

        statusOff_meta.setLore(statusOff_lore);
        statusOff.setItemMeta(statusOff_meta);

        //------------------------\\

        ItemMeta statusOn_meta = statusOn.getItemMeta();

        ArrayList<String> statusOn_lore = new ArrayList<String>();

        statusOn_meta.setDisplayName(ChatColor.GREEN + "Enabled Fishing");
        statusOn_lore.add("Custom fishing is enabled here.");
        statusOn_lore.add("Meaning players can fish here.");
        statusOn_lore.add("click item so people can't fish anymore.");

        statusOn_meta.setLore(statusOn_lore);
        statusOn.setItemMeta(statusOn_meta);

        //------------------------\\


        ItemMeta statusNo_meta = statusNo.getItemMeta();

        ArrayList<String> statusNo_lore = new ArrayList<String>();

        statusNo_meta.setDisplayName(ChatColor.GREEN + "No Fishing");
        statusNo_lore.add("Fishing is disabled here.");
        statusNo_lore.add("Meaning players can't fish here.");
        statusNo_lore.add("click item to turn on fishing.");

        statusNo_meta.setLore(statusNo_lore);
        statusNo.setItemMeta(statusNo_meta);

        //------------------------\\

        ItemMeta reset_meta = reset.getItemMeta();

        ArrayList<String> reset_lore = new ArrayList<String>();

        reset_meta.setDisplayName(ChatColor.RED + "Reset Region");
        reset_lore.add("This will reset region to standard");
        reset_lore.add("fishing rewards this is irreversible");

        reset_meta.setLore(reset_lore);
        reset.setItemMeta(reset_meta);

        //------------------------\\

        ItemMeta region_meta = region.getItemMeta();

        region_meta.setDisplayName(_region);

        region_meta.setLore(null);
        reset.setItemMeta(region_meta);

        //------------------------\\

        //endregion

        for (int index : orderW) {
            gui.setItem(index, standard);
        }
        gui.setItem(13, region);

        gui.setItem(15, reset);

        switch (fr.getStage()) {
            case ON: {

                gui.setItem(11, statusOff);
                break;
            }
            case OFF: {

                gui.setItem(11, statusNo);
                break;
            }
            case NO: {

                gui.setItem(11, statusOn);
                break;
            }
        }

        player.openInventory(gui);
    }
}
