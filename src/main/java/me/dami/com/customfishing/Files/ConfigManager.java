package me.dami.com.customfishing.Files;

import me.dami.com.customfishing.CustomFIshing;
import me.dami.com.customfishing.region.RegionRewards;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private static File configFile;
    private static FileConfiguration config;

    //Finds or generates the custom config file
    public static void setup(CustomFIshing main){
        try{
            configFile = new File(main.getDataFolder(), "FishingRegions.yml");
        }catch(Exception e){
            main.saveResource("FishingRegions.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(configFile);

        List<RewardItems> list = new ArrayList<>();
        RewardItems rw = new RewardItems();
        rw.item = new ItemStack(Material.ACACIA_FENCE, 1);
        rw.maxXp = 1;
        rw.minXp = 2;
        rw.DropChance = 100;
        list.add(rw);

        config.set("RegionRewards", list);
        saveConfig(main);
    }


    public static FileConfiguration get(){
        return config;
    }

    private static void saveConfig(CustomFIshing main) {
        try {
            config.save(configFile);
        } catch (IOException e) {
            main.getLogger().severe("Error saving config: " + e.getMessage());
        }
    }
}
