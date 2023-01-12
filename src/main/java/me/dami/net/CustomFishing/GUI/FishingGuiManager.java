package me.dami.net.CustomFishing.GUI;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public class FishingGuiManager implements Listener {

    private static Map<Player, FishingGuis> activeGuis = new LinkedHashMap<>();
    private static Map<Player, Integer[]> Indexes = new LinkedHashMap<>();

    private FishingMenuManager fishingMenuManager = new FishingMenuManager();

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
                    fishingMenuManager.clickEvent(e,Indexes.get(player));
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
        Indexes.put(_p, new Integer[]{0, 0});
    }

    public static void SetIndex(Player p,int _index){
        Integer[] index = Indexes.get(p);
        index[0] = _index;
        Indexes.replace(p, index);
    }

    public static void SetIndex(Player p, int _index0, int _index1){
        Integer[] index = Indexes.get(p);
        index[0] = _index0;
        index[1] = _index1;
        Indexes.replace(p, index);
    }
}
