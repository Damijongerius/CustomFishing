package me.dami.net.CustomFishing.GUI.Settings;

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

public class FishingItemSettingsGui {

    private static int[] fill = {0,1,2,4,6,7,9,11,13,15,16,17,18,19,20,22,24,25,26};

    public static void OpenGui(Player _p, FishingItems item){
        Inventory gui = Bukkit.createInventory(_p, 27, ChatColor.AQUA + "Item Menu");


        _p.openInventory(SetItems(gui,item));
    }

    public static Inventory SetItems(Inventory inv, FishingItems item){

        ItemStack xpRange = new ItemStack(Material.EXPERIENCE_BOTTLE);
        ItemStack amount = new ItemStack(Material.SUNFLOWER);
        ItemStack dropChance = new ItemStack(Material.TOTEM_OF_UNDYING);
        ItemStack growChance = new ItemStack(Material.BONE_MEAL);

        //------------------------\\

        ItemMeta xpRange_meta = xpRange.getItemMeta();

        ArrayList<String> xpRange_lore = new ArrayList<String>();

        xpRange_meta.setDisplayName(ChatColor.GOLD + "xpRange");
        float[] xp = item.getXpRange();
        xpRange_lore.add("the xpRange it drops when the item is fished");
        xpRange_lore.add("-------------------------------------");
        xpRange_lore.add("Current amounts minXp("+xp[0]+") || maxXp("+xp[1]+")");
        xpRange_lore.add("-------------------------------------");
        xpRange_lore.add("leftClick = +10 on max || rightClick = -10 on max");
        xpRange_lore.add("   ^^     with shift +/- 10 on min     ^^");

        xpRange_meta.setLore(xpRange_lore);
        xpRange.setItemMeta(xpRange_meta);

        //------------------------\\

        ItemMeta amount_meta = amount.getItemMeta();

        ArrayList<String> amount_lore = new ArrayList<String>();

        amount_meta.setDisplayName(ChatColor.GREEN + "itemAmount");
        int[] count = item.getItemAmount();
        amount_lore.add("the amount it can drop when the item is fished");
        amount_lore.add("-------------------------------------");
        amount_lore.add("Current amounts minAmount("+count[0]+") || maxAmount("+count[1]+")");
        amount_lore.add("-------------------------------------");
        amount_lore.add("leftClick = +1 on max || rightClick = -1 on max");
        amount_lore.add("   ^^       with shift +/- 1 on min       ^^");

        amount_meta.setLore(amount_lore);
        amount.setItemMeta(amount_meta);

        //------------------------\\

        ItemMeta dropChance_meta = amount.getItemMeta();

        ArrayList<String> dropChance_lore = new ArrayList<String>();

        dropChance_meta.setDisplayName(ChatColor.GOLD + "dropChance");
        float[] dropc = item.getDropChance();
        dropChance_lore.add("the chance it can drop when the item is fished");
        dropChance_lore.add("-------------------------------------");
        dropChance_lore.add("Current drop chance("+dropc[0]+") || drop percent("+dropc[1]+")");
        dropChance_lore.add("-------------------------------------");
        dropChance_lore.add("leftClick = +1|| rightClick = -1");

        dropChance_meta.setLore(dropChance_lore);
        dropChance.setItemMeta(dropChance_meta);

        //------------------------\\

        ItemMeta growChance_meta = growChance.getItemMeta();

        ArrayList<String> growChance_lore = new ArrayList<String>();

        growChance_meta.setDisplayName(ChatColor.GRAY + "growChance");
        boolean growC = item.isGrowChance();
        growChance_lore.add("click to change state");
        growChance_lore.add("item will increase with effects");
        growChance_lore.add("---------------------------");
        growChance_lore.add("Current state is (" +  growC + ")");

        growChance_meta.setLore(growChance_lore);
        growChance.setItemMeta(growChance_meta);

        //------------------------\\

        for(int slot : fill){
            inv.setItem(slot, StaticGUIItems.white);
        }

        inv.setItem(10,item.getItem());
        inv.setItem(3, xpRange);
        inv.setItem(5, growChance);
        inv.setItem(8, StaticGUIItems.escape);
        inv.setItem(12,amount);
        inv.setItem(14, StaticGUIItems.enchants);
        inv.setItem(21,dropChance);
        inv.setItem(23, StaticGUIItems.delete);

        return inv;
    }
}
