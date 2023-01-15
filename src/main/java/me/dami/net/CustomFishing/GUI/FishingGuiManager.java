package me.dami.net.CustomFishing.GUI;

import me.dami.net.CustomFishing.FishingClasses.FishingItems;
import me.dami.net.CustomFishing.GUI.Enchants.ChatFishingEnchantState;
import me.dami.net.CustomFishing.GUI.Enchants.FishingEnchantManager;
import me.dami.net.CustomFishing.GUI.Main.FishingMenuManager;
import me.dami.net.CustomFishing.GUI.Settings.FishingItemSettingsManager;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;

import java.util.LinkedHashMap;
import java.util.Map;

public class FishingGuiManager implements Listener {

    private static Map<Player, FishingGuiInfo> playerInfo = new LinkedHashMap<>();
    FishingMenuManager fishingMenuManager = new FishingMenuManager();
    FishingEnchantManager fishingEnchantManager = new FishingEnchantManager();

    FishingItemSettingsManager fishingItemSettingsManager = new FishingItemSettingsManager();

    @EventHandler
    public void OnInventoryClose(InventoryCloseEvent e){
        Player p = (Player) e.getPlayer();
        if(playerInfo.containsKey(p)){
            playerInfo.remove(p);
        }
    }

    @EventHandler
    public void OnInventoryClick(InventoryClickEvent e){
        Player player = (Player) e.getWhoClicked();
        if(playerInfo.containsKey(player)){
            switch (playerInfo.get(player).getActiveGui()){
                case FishingRegionGui:{
                    if(e.getClickedInventory().getType().getDefaultTitle() == "Chest") {
                        e.setCancelled(true);
                        fishingMenuManager.inventoryClickEvent(e, playerInfo.get(player));
                    }
                    break;
                }
                case FishingItemEnchants:{
                    if(e.getClickedInventory().getType().getDefaultTitle() == "Chest"){
                        e.setCancelled(true);
                        fishingEnchantManager.inventoryClickEvent(e,playerInfo.get(player));
                    }

                    break;
                }
                case FishingItemSettings:{
                    e.setCancelled(true);
                    if(e.getClickedInventory().getType().getDefaultTitle() == "Chest") {
                        fishingItemSettingsManager.inventoryClickEvent(e, playerInfo.get(player));
                    }
                    break;
                }
            }
        }
    }
    public static void AddPlayer(Player _p, FishingGuiInfo _info){
        playerInfo.put(_p,_info);
        System.out.println(_info.getRegion());
    }

    public static void ChanceRegion(Player _p, String _region){
        playerInfo.get(_p).setRegion(_region);
    }

    public static void ChangeGui(Player _p, FishingGuis _gui){
        playerInfo.get(_p).setActiveGui(_gui);

    }

    public static void ChangeItem(Player _p, FishingItems _item){
        playerInfo.get(_p).setItem(_item);
    }

    public static void ChangeChatEnchantState(Player _p, ChatFishingEnchantState _state){
        playerInfo.get(_p).setChatEnchantStage(_state);
    }

    public static ChatFishingEnchantState GetEnchantStage(Player _p){
        return playerInfo.get(_p).getChatEnchantStage();
    }

    public static void ChangeIndex(Player _p, Integer _index){
        playerInfo.get(_p).setInventoryIndex(new Integer[] {_index, 1000});
    }
}
