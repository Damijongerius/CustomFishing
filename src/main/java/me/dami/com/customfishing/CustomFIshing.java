package me.dami.com.customfishing;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.dami.com.customfishing.Commands.AdminCommands;
import me.dami.com.customfishing.Files.ConfigManager;
import me.dami.com.customfishing.fishing.Fishing;
import me.dami.com.customfishing.menus.FishingGuiManager;
import me.dami.com.customfishing.menus.ManageFishingInventory;
import me.dami.com.customfishing.region.RegionRewards;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomFIshing extends JavaPlugin {

    private Fishing fishing;
    private AdminCommands adminC;

    private FishingGuiManager fishingGuiM;
    private static CustomFIshing instance;

    public CustomFIshing(){
        fishing = new Fishing(this);
        adminC = new AdminCommands();
        fishingGuiM = new FishingGuiManager();
        instance = this;
    }
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(fishing, this);
        getServer().getPluginManager().registerEvents(fishingGuiM, this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        ConfigManager.setup(this);

        getCommand("fishing").setExecutor(adminC);


    }

    public static CustomFIshing get(){
        return instance;
    }

    @Override
    public void onDisable() {
        ConfigManager.reload();
        ConfigManager.saveConfig();
    }
}
