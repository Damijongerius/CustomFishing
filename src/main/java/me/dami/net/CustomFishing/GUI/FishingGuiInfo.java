package me.dami.net.CustomFishing.GUI;

import me.dami.net.CustomFishing.FishingClasses.FishingItems;
import me.dami.net.CustomFishing.GUI.Enchants.ChatFishingEnchantState;

public class FishingGuiInfo {
    private FishingGuis activeGui = FishingGuis.FishingRegionGui;
    private Integer[] inventoryIndex = new Integer[] {1,1};
    private ChatFishingEnchantState chatEnchantStage;
    private FishingItems item;

    public FishingGuis getActiveGui() {
        return activeGui;
    }

    public void setActiveGui(FishingGuis activeGui) {
        this.activeGui = activeGui;
    }

    public Integer[] getInventoryIndex() {
        return inventoryIndex;
    }

    public void setInventoryIndex(Integer[] inventoryIndex) {
        this.inventoryIndex = inventoryIndex;
    }

    public ChatFishingEnchantState getChatEnchantStage() {
        return chatEnchantStage;
    }

    public void setChatEnchantStage(ChatFishingEnchantState chatEnchantStage) {
        this.chatEnchantStage = chatEnchantStage;
    }

    public FishingItems getItem() {
        return item;
    }

    public void setItem(FishingItems item) {
        this.item = item;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    private String region = "";
}
