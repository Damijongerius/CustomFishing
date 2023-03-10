package me.dami.net.CustomFishing.GUI.Settings;

import me.dami.net.CustomFishing.FishingClasses.FishingItems;
import me.dami.net.CustomFishing.FishingClasses.FishingRegions;
import me.dami.net.CustomFishing.GUI.Enchants.ChatFishingEnchantState;
import me.dami.net.CustomFishing.GUI.Enchants.FishingEnchantGui;
import me.dami.net.CustomFishing.GUI.FishingGuiInfo;
import me.dami.net.CustomFishing.GUI.FishingGuiManager;
import me.dami.net.CustomFishing.GUI.FishingGuis;
import me.dami.net.CustomFishing.GUI.Main.FishingMenuGui;
import me.dami.net.CustomFishing.Region.FishingRegionManager;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;

public class FishingItemSettingsManager{

    public  FishingItemSettingsManager(){}

    public void inventoryClickEvent(InventoryClickEvent e, FishingGuiInfo _info){
        FishingItems item = _info.getItem();
        String region = _info.getRegion();

        int slot = e.getSlot();
        Player p = (Player) e.getWhoClicked();

        if(slot == 3){
            //xpRange
            float[] xpR =  item.getXpRange();
            if(e.isShiftClick()){
                //do min
                if(e.isLeftClick()) xpR[0] += 10;

                if(e.isRightClick()) xpR[0] -= 10;

            }else{
                //do max
                if(e.isLeftClick()) xpR[1] += 10;

                if(e.isRightClick()) xpR[1] -= 10;

            }
            item.setXpRange(xpR);
            FishingItemSettingsGui.SetItems(e.getClickedInventory(),item);
            return;
        }

        if(slot == 5){
            //grow on effect
            if(e.isLeftClick()){
                item.setGrowChance(!item.isGrowChance());
            }
            FishingItemSettingsGui.SetItems(e.getClickedInventory(),item);
            return;
        }

        if(slot == 8){
            //go back to region inv
            if(e.isLeftClick()){
                p.closeInventory();
                FishingGuiManager.AddPlayer(p,_info);
                FishingGuiManager.ChangeGui(p,FishingGuis.FishingRegionGui);
                FishingMenuGui.OpenGui(p, region);
            }
            return;
        }

        if(slot == 12){
            //drop amount
            int[] dAmount = item.getItemAmount();
            if(e.isShiftClick()){
                if(e.isLeftClick()){
                    dAmount[0] += 1;
                }
                if(e.isRightClick()){
                    dAmount[0] -= 1;
                }
            }else{
                if(e.isLeftClick()){
                    dAmount[1] += 1;
                }
                if(e.isRightClick()){
                    dAmount[1] -= 1;
                }
            }
            item.setItemAmount(dAmount);
            FishingItemSettingsGui.SetItems(e.getClickedInventory(),item);
            return;
        }

        if(slot == 14){
            //open enchantment menu
            if(e.isLeftClick()){
                p.closeInventory();
                FishingGuiManager.AddPlayer(p,_info);
                FishingGuiManager.ChangeGui(p,FishingGuis.FishingItemEnchants);
                FishingEnchantGui.OpenGui(p, item,region, ChatFishingEnchantState.CHANCE);
                FishingGuiManager.ChangeChatEnchantState(p, ChatFishingEnchantState.CHANCE);
            }
            return;
        }

        if(slot == 21){
            //drop chance
            float[] dropC = item.getDropChance();
            if(e.isLeftClick()){
                dropC[0] += 1;
            }
            if(e.isRightClick()){
                dropC[0] -= 1;
            }
            item.setDropChance(dropC);
            FishingItemSettingsGui.SetItems(e.getClickedInventory(),item);
            return;
        }

        if(slot == 23){
            //delete item and go back into region inv
            if(e.isLeftClick() && e.isShiftClick()){
                FishingRegions fr = FishingRegionManager.getFishingRegion(region);
                fr.RemoveItem(item);
                p.closeInventory();
                FishingGuiManager.AddPlayer(p,_info);
                FishingGuiManager.ChangeGui(p, FishingGuis.FishingRegionGui);
                FishingMenuGui.OpenGui(p, region);
            }
            return;
        }
    }
}
