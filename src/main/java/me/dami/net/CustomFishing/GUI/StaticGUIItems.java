package me.dami.net.CustomFishing.GUI;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class StaticGUIItems {

    //region Static ItemStacks
    public static ItemStack white = new ItemStack(Material.WHITE_STAINED_GLASS_PANE);
    public static ItemStack navigateLeft = new ItemStack(Material.YELLOW_STAINED_GLASS_PANE);
    public static ItemStack navigateRight =  new ItemStack(Material.GREEN_STAINED_GLASS_PANE);
    public static ItemStack statusOff = new ItemStack(Material.LEVER);
    public static ItemStack statusOn = new ItemStack(Material.TORCH);
    public static ItemStack statusNo = new ItemStack(Material.BARRIER);
    public static ItemStack reset = new ItemStack(Material.REDSTONE_TORCH);
    public static ItemStack delete = new ItemStack(Material.BARRIER);
    public static ItemStack escape = new ItemStack(Material.BARRIER);
    public static ItemStack enchants = new ItemStack(Material.ENCHANTED_BOOK);

    //end region

    public StaticGUIItems(){

        //region Customize Items

        //------------------------\\


        ItemMeta white_meta = white.getItemMeta();

        white_meta.setDisplayName(ChatColor.RED + "-");

        white_meta.setLore(null);
        white.setItemMeta(white_meta);

        //------------------------\\

        ItemMeta navigateLeft_meta = navigateLeft.getItemMeta();

        navigateLeft_meta.setDisplayName(ChatColor.YELLOW + "Left");

        navigateLeft_meta.setLore(null);
        navigateLeft.setItemMeta(navigateLeft_meta);

        //------------------------\\

        ItemMeta navigateRight_meta = navigateRight.getItemMeta();

        navigateRight_meta.setDisplayName(ChatColor.GREEN + "Right");

        navigateRight_meta.setLore(null);
        navigateRight.setItemMeta(navigateRight_meta);

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

        ItemMeta delete_meta = delete.getItemMeta();

        ArrayList<String> delete_lore = new ArrayList<String>();

        delete_meta.setDisplayName(ChatColor.RED + "delete Item");
        delete_lore.add("This will remove the item");
        delete_lore.add("all data of this item will be gone");

        delete_meta.setLore(delete_lore);
        delete.setItemMeta(delete_meta);

        //------------------------\\

        ItemMeta escape_meta = delete.getItemMeta();

        escape_meta.setDisplayName(ChatColor.RED + "back");

        escape_meta.setLore(null);
        escape.setItemMeta(escape_meta);

        //------------------------\\

        ItemMeta enchants_meta = enchants.getItemMeta();

        ArrayList<String> enchants_lore = new ArrayList<String>();

        enchants_meta.setDisplayName(ChatColor.DARK_PURPLE + "enchantments");
        enchants_lore.add("click to open menu for all possible enchants");

        enchants_meta.setLore(enchants_lore);
        enchants.setItemMeta(enchants_meta);


        //endregion

    }
}
