package me.dami.net.CustomFishing.Main;

import me.dami.net.CustomFishing.CommandManager.CustomCommands;
import me.dami.net.CustomFishing.Config.ConfigurationManager;
import me.dami.net.CustomFishing.Fishing.Fishing;
import me.dami.net.CustomFishing.GUI.FishingGuiManager;
import me.dami.net.CustomFishing.GUI.FishingMenuGui;
import me.dami.net.CustomFishing.GUI.StaticGUIItems;
import me.dami.net.CustomFishing.Region.FishingRegionManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomizableFishing extends JavaPlugin {
    private static CustomizableFishing instance;
    //region Classes
    private FishingMenuGui fishingMenuGui;
    private Fishing fishing;
    private CustomCommands customCommands ;

    private ConfigurationManager fishingRegionConfig;
    private FishingRegionManager  fishingRegionManager;
    private FishingGuiManager fishingGuiManager;

    //region end
    public CustomizableFishing(){
        instance = this;
    }
    @Override
    public void onEnable(){
        getConfig().options().copyDefaults();
        saveDefaultConfig();


        fishingRegionConfig = new ConfigurationManager(this,"FishingRegions");
        fishingRegionConfig.startUp();

        fishingMenuGui = new FishingMenuGui();
        fishing = new Fishing(this);
        customCommands = new CustomCommands(fishingMenuGui);

        fishingRegionManager = new FishingRegionManager(fishingRegionConfig);
        fishingGuiManager = new FishingGuiManager();

        StaticGUIItems localStaticGUIItems = new StaticGUIItems();

        getServer().getPluginManager().registerEvents(fishing, this);
        getServer().getPluginManager().registerEvents(fishingGuiManager, this);

        getCommand("fishing").setExecutor(customCommands);


    }

    @Override
    public void onDisable(){
        fishingRegionManager.updateFishingRegions();
    }

    public static CustomizableFishing get(){
        return instance;
    }


}
