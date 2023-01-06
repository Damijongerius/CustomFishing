package me.dami.com.customfishing.Files;

import org.bukkit.Bukkit;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private static File file;
    private static FileConfiguration regionDrops;

    //Finds or generates the custom config file
    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("CustomConfigPlugin").getDataFolder(), "customconfig.yml");

        if (!file.exists()){
            try{
                file.createNewFile();
            }catch (IOException e){
                //owww
            }
        }
        regionDrops = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return regionDrops;
    }

    public static void save(){
        try{
            regionDrops.save(file);
        }catch (IOException e){
            System.out.println("Couldn't save file");
        }
    }

    public static void reload(){
        regionDrops = YamlConfiguration.loadConfiguration(file);
    }


}
