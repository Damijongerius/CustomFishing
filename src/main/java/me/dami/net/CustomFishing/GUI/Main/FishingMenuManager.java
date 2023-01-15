package me.dami.net.CustomFishing.GUI.Main;

import me.dami.net.CustomFishing.FishingClasses.FishingEnchantments;
import me.dami.net.CustomFishing.FishingClasses.FishingItems;
import me.dami.net.CustomFishing.FishingClasses.FishingRegions;
import me.dami.net.CustomFishing.FishingClasses.FishingStatus;
import me.dami.net.CustomFishing.GUI.FishingGuiInfo;
import me.dami.net.CustomFishing.GUI.FishingGuiManager;
import me.dami.net.CustomFishing.GUI.FishingGuis;
import me.dami.net.CustomFishing.GUI.Settings.FishingItemSettingsGui;
import me.dami.net.CustomFishing.GUI.StaticGUIItems;
import me.dami.net.CustomFishing.Region.FishingRegionManager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class FishingMenuManager{

    public void inventoryClickEvent(InventoryClickEvent e, FishingGuiInfo _info){
        Integer[] index = _info.getInventoryIndex();

        String region = _info.getRegion();

        FishingRegions fishingRegions = FishingRegionManager.getFishingRegion(region);

        Player p = (Player) e.getWhoClicked();
        int slot = e.getSlot();
        if(e.getCursor() != null && e.getCursor().getType() != Material.AIR){
            HasItem(e,index, fishingRegions, region);
            return;
        }

        if(e.isShiftClick()){
            return;
        }

        if(slot == 11) {
            switch (fishingRegions.getStatus()) {
                case ON -> {
                    fishingRegions.setStatus(FishingStatus.OFF);
                    e.getClickedInventory().setItem(11, StaticGUIItems.statusOff);
                }
                case OFF -> {
                    fishingRegions.setStatus(FishingStatus.NO);
                    e.getClickedInventory().setItem(11, StaticGUIItems.statusNo);
                }
                default -> {
                    fishingRegions.setStatus(FishingStatus.ON);
                    e.getClickedInventory().setItem(11, StaticGUIItems.statusOn);
                }
            }
            FishingMenuGui.SetItems(e.getClickedInventory(),region, index[0]);
            return;
        }

        if(slot == 35 || slot == 44 || slot == 53){
            FishingGuiManager.ChangeIndex(p,index[0] - 3);
            FishingMenuGui.SetItems(e.getClickedInventory(),region, index[0] - 3);
            return;
        }

        if(slot == 27 || slot == 36 || slot == 45){
            if(fishingRegions.getItems().size() > index[0] + 3){
                FishingGuiManager.ChangeIndex(p,index[0] + 3);
                FishingMenuGui.SetItems(e.getClickedInventory(),region, index[0] + 3);
            }
            return;
        }

        if(slot == 15){
            //reset items
            System.out.println("reset");
            fishingRegions.ClearItems();
            FishingGuiManager.ChangeIndex(p,0);
            FishingMenuGui.SetItems(e.getClickedInventory(),region, 0);
        }

        if(slot >= 28 && slot <= 34 || slot >= 37 && slot <= 43 || slot >= 46 && slot <= 52){
            //open item settings
            if(e.getCurrentItem().getType() == Material.AIR){
                return;
            }
            if(Enchantment.DURABILITY.canEnchantItem(e.getCurrentItem())){
                p.closeInventory();
                FishingGuiManager.AddPlayer(p,_info);
                FishingGuiManager.ChangeGui(p, FishingGuis.FishingItemSettings);
                FishingGuiManager.ChangeItem(p, fishingRegions.getItemWithMaterial(e.getCurrentItem().getType()));
                FishingItemSettingsGui.OpenGui(p,fishingRegions.getItemWithMaterial(e.getCurrentItem().getType()));
            }
        }
    }

public void HasItem(InventoryClickEvent e, Integer[] index, FishingRegions _fishingRegions, String region) {
    int slot = e.getSlot();

    boolean InInventory = slot >= 0 && slot <= 53;
    if (!InInventory) {
        return;
    }

    //add item
    ItemStack handItem = e.getCursor();
    boolean itemExists = false;
    FishingItems originalItem = null;
    for (FishingItems fishingItem : _fishingRegions.getItems()) {
        if (fishingItem.getItem().getType() == handItem.getType()) {
            itemExists = true;
            originalItem = fishingItem;
        }
    }

    if (!itemExists) {
        FishingItems fishingItem = new FishingItems(handItem.clone());

        _fishingRegions.AddItem(fishingItem);

        FishingMenuGui.SetItems(e.getClickedInventory(), region, index[0]);

    } else {
        int[] amounts = originalItem.getItemAmount();
        Map<Enchantment, Integer> enchantments = handItem.getEnchantments();
        if (amounts[1] < handItem.getAmount()) originalItem.setItemAmount(new int[]{amounts[0], handItem.getAmount()});

        if (!enchantments.isEmpty()) {
            for (Map.Entry<Enchantment, Integer> enchant : enchantments.entrySet()) {
                FishingEnchantments newEnchantment = new FishingEnchantments(enchant.getKey());

                newEnchantment.setEnchantLevels(new int[]{1, enchant.getValue()});
                if(!originalItem.getPossibleEnchants().contains(newEnchantment)) originalItem.addPossibleEnchant(newEnchantment);
            }
        }

        FishingMenuGui.SetItems(e.getClickedInventory(), region, index[0]);
    }
}

}
