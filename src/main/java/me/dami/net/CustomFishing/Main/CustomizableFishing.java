package me.dami.net.CustomFishing.Main;

import me.dami.net.CustomFishing.CommandManager.CustomCommands;
import me.dami.net.CustomFishing.Config.ConfigurationManager;
import me.dami.net.CustomFishing.Fishing.Fishing;
import me.dami.net.CustomFishing.GUI.FishingGuiManager;
import me.dami.net.CustomFishing.GUI.Main.FishingMenuGui;
import me.dami.net.CustomFishing.GUI.StaticGUIItems;
import me.dami.net.CustomFishing.Permissions.FishingPermissionsManager;
import me.dami.net.CustomFishing.Region.FishingRegionManager;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomizableFishing extends JavaPlugin {
    private static CustomizableFishing instance;
    //region Classes
    private FishingMenuGui fishingMenuGui;
    private Fishing fishing;
    private CustomCommands customCommands ;

    private ConfigurationManager fishingRegionConfig;
    private ConfigurationManager playerPermissions;
    private FishingRegionManager  fishingRegionManager;
    private FishingGuiManager fishingGuiManager;
    private FishingPermissionsManager fishingPermissionsManager;

    //region end
    public CustomizableFishing(){
        instance = this;
    }
    @Override
    public void onEnable(){
        getConfig().options().copyDefaults();
        saveDefaultConfig();


        playerPermissions = new ConfigurationManager(this,"FishPermissions");
        fishingRegionConfig = new ConfigurationManager(this,"FishingRegions");

        fishingMenuGui = new FishingMenuGui();
        fishing = new Fishing(this);
        customCommands = new CustomCommands(fishingMenuGui);
        fishingPermissionsManager = new FishingPermissionsManager(this, playerPermissions);

        fishingRegionManager = new FishingRegionManager(fishingRegionConfig);
        fishingGuiManager = new FishingGuiManager();

        StaticGUIItems localStaticGUIItems = new StaticGUIItems();

        getServer().getPluginManager().registerEvents(fishing, this);
        getServer().getPluginManager().registerEvents(fishingGuiManager, this);
        getServer().getPluginManager().registerEvents(fishingPermissionsManager, this);

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
