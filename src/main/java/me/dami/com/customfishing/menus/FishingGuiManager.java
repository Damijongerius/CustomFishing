package me.dami.com.customfishing.menus;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public class FishingGuiManager implements Listener {

    private static Map<Player,FishingGuis> activeGuis = new LinkedHashMap<>();

    private ManageFishingInventory MFI = new ManageFishingInventory();

    @EventHandler
    public void OnInventoryClose(InventoryCloseEvent e){
        if(activeGuis.containsKey(e.getPlayer())){
            activeGuis.remove(e.getPlayer());
        }
    }

    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(activeGuis.containsKey(player)){
            switch (activeGuis.get(player)){
                case FishingRegionGui:{
                    MFI.clickEvent(e);
                    break;
                }
                case FishingItemEnchants:{

                    break;
                }
                case FishingItemSettings:{

                    break;
                }
            }
        }
    }

    public static void AddGui(Player _p, FishingGuis _gui){
        activeGuis.put(_p, _gui);
    }
}
