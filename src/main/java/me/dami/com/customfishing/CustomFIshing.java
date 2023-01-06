package me.dami.com.customfishing;

import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

public class CustomFIshing extends JavaPlugin {

    private Fishing fishing;

    public CustomFIshing(){
        fishing = new Fishing(this);
    }
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(fishing, this);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
