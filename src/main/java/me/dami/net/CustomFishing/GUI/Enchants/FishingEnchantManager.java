package me.dami.net.CustomFishing.GUI.Enchants;

import me.dami.net.CustomFishing.FishingClasses.FishingEnchantments;
import me.dami.net.CustomFishing.FishingClasses.FishingItems;
import me.dami.net.CustomFishing.FishingClasses.FishingRegions;
import me.dami.net.CustomFishing.GUI.FishingGuiInfo;
import me.dami.net.CustomFishing.GUI.FishingGuiManager;
import me.dami.net.CustomFishing.GUI.FishingGuis;
import me.dami.net.CustomFishing.GUI.Settings.FishingItemSettingsGui;
import me.dami.net.CustomFishing.Region.FishingRegionManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;

public class FishingEnchantManager {

    private int[][] itemList = {{1, 2, 3, 4, 5}, {10, 11, 12, 13, 14}, {19, 20, 21, 22, 23}};

    public void inventoryClickEvent(InventoryClickEvent e, FishingGuiInfo _info) {
        ChatFishingEnchantState state = _info.getChatEnchantStage();
        FishingItems fishingI = _info.getItem();

        int slot = e.getSlot();
        Player p = (Player) e.getWhoClicked();

        if (slot == 7) {
            fishingI.resetPossibleEnchants();
            return;
        }

        if (slot == 16) {
            if (e.isLeftClick()) {
                fishingI.setFollowupChance(fishingI.getFollowupChance() + 0.5f);
            } else {
                fishingI.setFollowupChance(fishingI.getFollowupChance() - 0.5f);
            }
            FishingEnchantGui.SetItems(e.getClickedInventory(), fishingI, FishingGuiManager.GetEnchantStage(p));
            return;
        }

        if (slot == 25) {
            p.closeInventory();
            FishingGuiManager.AddPlayer(p,_info);
            FishingGuiManager.ChangeGui(p, FishingGuis.FishingItemSettings);
            FishingItemSettingsGui.OpenGui(p, fishingI);
        }

        boolean isInItemList = false;
        for (int itemRay[] : itemList) {
            for (int item : itemRay) {
                if (slot == item) isInItemList = true;
            }
        }
        if (isInItemList) {

            System.out.println(e.getCursor().getType());
            if (e.getCursor().getType() == Material.ENCHANTED_BOOK) {

                ItemStack item = e.getCursor();
                if(item.getItemMeta() instanceof EnchantmentStorageMeta) {
                    EnchantmentStorageMeta meta = (EnchantmentStorageMeta) item.getItemMeta();
                    Map<Enchantment, Integer> enchantments = meta.getStoredEnchants();

                    if (!enchantments.isEmpty()) {
                        for (Map.Entry<Enchantment, Integer> enchant : enchantments.entrySet()) {
                            FishingEnchantments fishingEnchantment = new FishingEnchantments(enchant.getKey());
                            fishingEnchantment.setEnchantLevels(new int[]{1, enchant.getValue()});

                            boolean exists = false;
                            for(FishingEnchantments ench: fishingI.getPossibleEnchants()){
                                if(enchant.getKey().getName() == ench.getEnchant().getName()){
                                    exists = true;
                                }
                                System.out.println(enchant + "-" + ench);
                            }

                            if(fishingEnchantment.getEnchant().canEnchantItem(fishingI.getItem()) && !exists){
                                fishingI.addPossibleEnchant(fishingEnchantment);
                            }
                        }
                    }
                }


                FishingEnchantGui.SetItems(e.getClickedInventory(), fishingI, ChatFishingEnchantState.CHANCE);
                return;
            }

            if (e.isShiftClick()) {
                if (state == ChatFishingEnchantState.CHANCE) {
                    if (e.isLeftClick()) {
                        FishingGuiManager.ChangeChatEnchantState(p, ChatFishingEnchantState.LEVELS);
                    } else {
                        FishingGuiManager.ChangeChatEnchantState(p, ChatFishingEnchantState.DELETE);
                    }
                }
                if (state == ChatFishingEnchantState.DELETE) {
                    if (e.isLeftClick()) {
                        FishingGuiManager.ChangeChatEnchantState(p, ChatFishingEnchantState.CHANCE);
                    } else {
                        FishingGuiManager.ChangeChatEnchantState(p, ChatFishingEnchantState.LEVELS);
                    }
                }
                if (state == ChatFishingEnchantState.LEVELS) {
                    if (e.isLeftClick()) {
                        FishingGuiManager.ChangeChatEnchantState(p, ChatFishingEnchantState.DELETE);
                    } else {
                        FishingGuiManager.ChangeChatEnchantState(p, ChatFishingEnchantState.CHANCE);
                    }
                }
                FishingEnchantGui.SetItems(e.getClickedInventory(), fishingI, FishingGuiManager.GetEnchantStage(p));
            } else {
                //translate list to slots
                List<FishingEnchantments> _enchantments = fishingI.getPossibleEnchants();
                FishingEnchantments enchantment = null;
                for (int i = 0; i < _enchantments.size(); i++) {
                    if (slot == itemList[i % 3][i / 3]) {
                        enchantment = _enchantments.get(i);
                    }
                }

                if (enchantment == null) {
                    System.out.println("return ench == null");
                    return;
                }

                if (state == ChatFishingEnchantState.CHANCE) {
                    float[] chance = enchantment.getEnchantChance();
                    if (e.isLeftClick()) {
                        enchantment.setEnchantChance(new float[]{chance[0] + 1, chance[1]});
                    } else {
                        enchantment.setEnchantChance(new float[]{chance[0] - 1, chance[1]});
                    }
                    FishingEnchantGui.SetItems(e.getClickedInventory(), fishingI, ChatFishingEnchantState.CHANCE);
                }
                if (state == ChatFishingEnchantState.DELETE) {
                    if (e.isRightClick()) {
                        fishingI.removePossibleEnchant(enchantment);
                    }
                    FishingEnchantGui.SetItems(e.getClickedInventory(), fishingI, ChatFishingEnchantState.DELETE);
                }
                if (state == ChatFishingEnchantState.LEVELS) {
                    int[] level = enchantment.getEnchantLevels();
                    if (e.isLeftClick()) {
                        if (enchantment.getEnchant().getMaxLevel() > level[1]) {
                            enchantment.setEnchantLevels(new int[]{1, level[1]++});
                        }
                    } else {
                        if (level[1] > 1) {
                            enchantment.setEnchantLevels(new int[]{1, level[1]--});
                        }
                    }
                    FishingEnchantGui.SetItems(e.getClickedInventory(), fishingI, ChatFishingEnchantState.LEVELS);
                }
            }
        }
    }
}