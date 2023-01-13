package me.dami.net.CustomFishing.GUI.Settings;

import me.dami.net.CustomFishing.FishingClasses.FishingItems;
import org.bukkit.event.inventory.InventoryClickEvent;

public class FishingItemSettingsManager implements Runnable{

    private InventoryClickEvent e;
    private FishingItems item;

    public FishingItemSettingsManager(InventoryClickEvent _e, FishingItems _item){

    }
    @Override
    public void run() {
        int slot = e.getSlot();

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
            return;
        }

        if(slot == 5){
            //grow on effect
            if(e.isLeftClick()){
                item.setGrowChance(!item.isGrowChance());
            }
            return;
        }

        if(slot == 8){
            //go back to region inv
            if(e.isLeftClick()){

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
            return;
        }

        if(slot == 14){
            //open enchantment menu
            if(e.isLeftClick()){

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
                dropC[0] += 1;
            }
            item.setDropChance(dropC);
            return;
        }

        if(slot == 23){
            //delete item and go back into region inv
            if(e.isLeftClick() && e.isShiftClick()){

            }
            return;
        }
    }
}
