package me.dami.net.CustomFishing.GUI;

import me.dami.net.CustomFishing.FishingClasses.FishingEnchantments;
import me.dami.net.CustomFishing.FishingClasses.FishingItems;
import me.dami.net.CustomFishing.FishingClasses.FishingRegions;
import me.dami.net.CustomFishing.FishingClasses.FishingStatus;
import me.dami.net.CustomFishing.Region.FishingRegionManager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class FishingMenuManager {

    static int orderL[] = {27,36,45};
    static int orderR[] = {35,44,53};
    int[][] ItemDiv = {{28,29,30,31,32,33,34},{37,38,39,40,41,42,43},{46,47,48,49,50,51,52}};

    public void clickEvent(InventoryClickEvent e, Integer[] index){
        FishingRegions fishingRegions = null;
        try{
            if(e.getClickedInventory().getType().getDefaultTitle() == "Chest"){
                fishingRegions = FishingRegionManager.getFishingRegion(Objects.requireNonNull(e.getClickedInventory()).getItem(13).getItemMeta().getDisplayName());
            }else{
                return;
            }
        }catch (Exception ed){};

        Player p = (Player) e.getWhoClicked();
        int slot = e.getSlot();
        e.setCancelled(true);
        if(e.getCursor() != null && e.getCursor().getType() != Material.AIR){
            HasItem(e,index, fishingRegions);
            return;
        }

        if(e.isShiftClick()){
            return;
        }

        if(slot == 11) {
            switch (fishingRegions.getStatus()) {
                case ON: {
                    fishingRegions.setStatus(FishingStatus.OFF);
                    e.getClickedInventory().setItem(11, StaticGUIItems.statusOff);
                    break;
                }
                case OFF: {
                    fishingRegions.setStatus(FishingStatus.NO);
                    e.getClickedInventory().setItem(11, StaticGUIItems.statusNo);
                    break;
                }
                case NO: {
                    fishingRegions.setStatus(FishingStatus.ON);
                    e.getClickedInventory().setItem(11, StaticGUIItems.statusOn);
                    break;
                }
                default: {
                    fishingRegions.setStatus(FishingStatus.ON);
                    e.getClickedInventory().setItem(11, StaticGUIItems.statusOn);
                }
            }
            return;
        }

        if(slot == 35 || slot == 44 || slot == 53){
            FishingGuiManager.SetIndex(p,index[0] - 3);
            List<FishingItems> items = fishingRegions.getItems(index[0] - 3, index[0] + 18);
            for (FishingItems item : items) {
                int indexX = fishingRegions.getItems().size() - index[0] - 3 % 3;
                int indexY = fishingRegions.getItems().size() - index[0] - 3 / 3;
                e.getClickedInventory().setItem(ItemDiv[indexX][indexY], SetDisplay(item));
            }
            return;
        }

        if(slot == 27 || slot == 36 || slot == 45){
            if(fishingRegions.getItems().size() > index[0] + 3){
                FishingGuiManager.SetIndex(p,index[0] + 3);
                List<FishingItems> items = fishingRegions.getItems(index[0] + 3, index[0] + 24);
                for (FishingItems item : items) {
                    int indexX = fishingRegions.getItems().size() - index[0] + 3 % 3;
                    int indexY = fishingRegions.getItems().size() - index[0] + 3 / 3;
                    e.getClickedInventory().setItem(ItemDiv[indexX][indexY], SetDisplay(item));
                }
            }
            return;
        }

        if(slot == 15){
            //reset items
        }

        if(slot >= 28 && slot <= 34 || slot >= 37 && slot <= 43 || slot >= 46 && slot <= 52){
            //open item settings
        }
    }

    public void HasItem(InventoryClickEvent e, Integer[] index, FishingRegions _fishingRegions){
        int slot = e.getSlot();

        boolean InInventory = slot >= 0 && slot <= 53;
        if (!InInventory) {
            return;
        }

        //add item
        ItemStack handItem = e.getCursor();
        boolean itemExists = false;
        FishingItems originalItem = null;
        for(FishingItems fishingItem : _fishingRegions.getItems()){
            if(fishingItem.getItem().getType() == handItem.getType()){
                itemExists = true;
                originalItem = fishingItem;
            }
        }

        int indexX = (_fishingRegions.getItems().size() - index[0]) % 3;
        int indexY = (_fishingRegions.getItems().size() - index[0]) / 3;

        //if(!itemExists){
            FishingItems fishingItem = new FishingItems(handItem);

            _fishingRegions.AddItem(fishingItem);

            //e.getClickedInventory().setItem(ItemDiv[indexX][indexY],SetDisplay(fishingItem));

        /*}else{
            int[] amounts = originalItem.getItemAmount();
            Map< Enchantment, Integer> enchantments = handItem.getEnchantments();
            if(amounts[1] < handItem.getAmount()) originalItem.setItemAmount(new int[] {amounts[0], handItem.getAmount()});

            if(!enchantments.isEmpty()){
                List<FishingEnchantments> pair = new ArrayList<>();
                for(Map.Entry<Enchantment,Integer> enchant : enchantments.entrySet()){
                    FishingEnchantments newEnchantment = new FishingEnchantments(enchant.getKey());

                    newEnchantment.setEnchantLevels(new int[] {1,enchant.getValue()});
                    pair.add(newEnchantment);
                    originalItem.addPossibleEnchant(newEnchantment);
                }

                for(FishingEnchantments enchantment : pair){
                    for(FishingEnchantments enchantment1 : pair){
                        if(enchantment1 != enchantment){
                            enchantment.addAvailiblePairing(enchantment1, true);
                        }
                    }
                }

            }

        }
        */
    }

    private ItemStack SetDisplay(FishingItems _item){

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
