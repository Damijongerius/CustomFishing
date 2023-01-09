package me.dami.com.customfishing.menus;

import me.dami.com.customfishing.Files.ConfigManager;
import me.dami.com.customfishing.region.FishingRegion;
import me.dami.com.customfishing.region.FishingStage;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class ManageFishingInventory {

    /*
    reset button (15)
    scroll left or right (orderL, orderR)
    turn on/off/no fishing (11)
    put in item/remove item
    look in item settings
     */

    //region items
    ItemStack statusOff = new ItemStack(Material.LEVER);
    ItemStack statusOn = new ItemStack(Material.TORCH);
    ItemStack statusNo = new ItemStack(Material.BARRIER);

    //end

    public ManageFishingInventory() {
        //region meta
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
        //endregion
    }

    //shift 3 on move
    public void clickEvent(InventoryClickEvent e) {
        FishingRegion fr = ConfigManager.getFishingRegion(e.getClickedInventory().getItem(13).getItemMeta().getDisplayName());

        Player player = (Player) e.getWhoClicked();
        int slot = e.getSlot();
        System.out.println(e.getCursor());
        System.out.println(slot);
        if (e.getCursor() == null) {
            //item no item in cursor

            switch (slot) {
                //on off no
                case 11: {

                    switch (fr.getStage()) {
                        case ON: {
                            fr.setStage(FishingStage.OFF);
                            e.getClickedInventory().setItem(11, statusOff);
                            break;
                        }
                        case OFF: {
                            fr.setStage(FishingStage.NO);
                            e.getClickedInventory().setItem(11, statusNo);
                            break;
                        }
                        case NO: {
                            fr.setStage(FishingStage.ON);
                            e.getClickedInventory().setItem(11, statusOn);
                            break;
                        }
                        default:{
                            fr.setStage(FishingStage.ON);
                            e.getClickedInventory().setItem(11, statusOn);
                        }
                    }
                    break;
                }

                //reset
                case 15: {

                    break;
                }

                //left
                case 27:
                case 36:
                case 45: {
                    //go left is possible
                    break;
                }

                //right
                case 35:
                case 44:
                case 53: {
                    //go right if possible
                    break;
                }

                //rest
                default: {
                    if (slot > 28 && slot < 34 || slot > 37 && slot < 43 || slot > 46 && slot < 52) {
                        ItemStack item = e.getCurrentItem();
                        if (item != null) {
                            //open item settings
                        }
                    }

                }
            }
        } else {
            //item in cursor
            if (slot > 28 && slot < 34 || slot > 37 && slot < 43 || slot > 46 && slot < 52) {
                //add item to fishingregion
            }
        }
        e.setCancelled(true);
    }
}

