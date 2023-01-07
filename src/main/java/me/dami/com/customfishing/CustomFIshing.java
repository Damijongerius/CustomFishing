package me.dami.com.customfishing;

import me.dami.com.customfishing.Commands.AdminCommands;
import me.dami.com.customfishing.Files.ConfigManager;
import me.dami.com.customfishing.fishing.Fishing;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomFIshing extends JavaPlugin {

    private Fishing fishing;
    private AdminCommands adminC;

    public CustomFIshing(){
        fishing = new Fishing(this);
        adminC = new AdminCommands();
    }
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(fishing, this);

        getConfig().options().copyDefaults();
        saveDefaultConfig();
        ConfigManager.setup(this);

        getCommand("fishing").setExecutor(adminC);
    }

    @Override
    public void onDisable() {
        //on shut down save file?
    }
}
