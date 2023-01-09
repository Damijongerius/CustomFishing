package me.dami.com.customfishing.Files;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.dami.com.customfishing.CustomFIshing;
import me.dami.com.customfishing.region.FishingItem;
import me.dami.com.customfishing.region.FishingRegion;
import me.dami.com.customfishing.region.FishingStage;
import me.dami.com.customfishing.region.RegionProperties;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ConfigManager {

    private static File configFile;
    private static CustomFIshing main;
    private static FileConfiguration config;

    private static Map<String,FishingRegion> fishingRegions = new LinkedHashMap<>();

    //Finds or generates the custom config file
    public static void setup(CustomFIshing _main) {
        main = _main;

        try {
            configFile = new File(main.getDataFolder(), "FishingRegions.yml");
        } catch (Exception e) {
            main.saveResource("FishingRegions.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);

        ConfigurationSection _fishingRegions;

        try {
            _fishingRegions = config.getConfigurationSection("world.regions");
            System.out.println(_fishingRegions.get("region1"));
        } catch (Exception e) {
            _fishingRegions = null;
        }

        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regionM = container.get(new BukkitWorld(Bukkit.getWorld("world")));

        List<String> regions = new ArrayList<>();
        for (Map.Entry<String, ProtectedRegion> region : regionM.getRegions().entrySet()) {
            String regionName = region.getKey();
            regions.add(regionName);
            ConfigurationSection cs = null;
            if(_fishingRegions != null)
              cs = _fishingRegions.getConfigurationSection(regionName);

            if (cs == null) {
                fishingRegions.put(regionName,new FishingRegion());
            }else{
                fishingRegions.put(regionName,new FishingRegion(cs));
            }
        }

        saveConfig();
    }

    public static void reload(){
        try {
            configFile = new File(main.getDataFolder(), "FishingRegions.yml");
        } catch (Exception e) {
            main.saveResource("FishingRegions.yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);


        RegionContainer container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regionM = container.get(new BukkitWorld(Bukkit.getWorld("world")));

        List<String> regions = new ArrayList<>();
        for (Map.Entry<String, ProtectedRegion> region : regionM.getRegions().entrySet()) {
            String regionName = region.getKey();
            regions.add(regionName);

            if (!fishingRegions.containsKey(regionName)) {
                fishingRegions.put(regionName,new FishingRegion());
            }
        }

        Map<String,Object> fregions = new LinkedHashMap<>();
        for(Map.Entry<String, FishingRegion> fRegion : fishingRegions.entrySet() ){

            fregions.put(fRegion.getKey(), fRegion.getValue().serialize());
        }

        config.set("world.regions", fregions);
        saveConfig();
    }

    public static FishingRegion getFishingRegion(String _region) {
        if(!fishingRegions.containsKey(_region))
            reload();
        return fishingRegions.get(_region);
    }


    public static void UpdateFishingRegion(FishingRegion fishingRegion, String region) {
        fishingRegions.replace(region,fishingRegion);
    }

    public static FileConfiguration get(){
        return config;
    }

    public static void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            main.getLogger().severe("Error saving config: " + e.getMessage());
        }
    }
}
