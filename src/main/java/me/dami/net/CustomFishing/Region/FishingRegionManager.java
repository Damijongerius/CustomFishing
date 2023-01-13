package me.dami.net.CustomFishing.Region;

import me.dami.net.CustomFishing.Config.ConfigurationManager;
import me.dami.net.CustomFishing.FishingClasses.FishingRegions;
import org.bukkit.configuration.ConfigurationSection;

import java.util.LinkedHashMap;
import java.util.Map;

public class FishingRegionManager {

    private static Map<String, FishingRegions> fishingRegions = new LinkedHashMap<>();

    private static ConfigurationManager configManager;

    public FishingRegionManager(ConfigurationManager _manager){
        this.configManager = _manager;
        ConfigurationSection section = null;
        try {
            section = configManager.getSection("world.regions");
        } catch (Exception e) {
            System.out.println("[fishingRegionManager] nothing found in section");
        }

        for(String region : RegionManaging.getRegions()){
            ConfigurationSection fishingRegion = null;
            if(section != null)
              fishingRegion = section.getConfigurationSection(region);
            if(fishingRegion == null){
                fishingRegions.put(region,new FishingRegions());
            }else{
                fishingRegions.put(region,new FishingRegions(fishingRegion));
            }
        }
        Map<String, Object> map = new LinkedHashMap<>();
        for(Map.Entry<String,FishingRegions> fishingRegions1 : fishingRegions.entrySet()){
            map.put(fishingRegions1.getKey(), fishingRegions1.getValue().serialize());
        }
        configManager.setConfig("world.regions", map);
        configManager.saveConfig();
    }

    //updates non existent regions
    public static void updateFishingRegions(){
        try {
            ConfigurationSection section = configManager.getSection("world.regions");
            for(String region : RegionManaging.getRegions()){
                ConfigurationSection fishingRegion = null;
                if(section != null)
                    fishingRegion = section.getConfigurationSection(region);
                if(fishingRegion == null){
                    fishingRegions.put(region,new FishingRegions());
                }
            }

            Map<String,Object> map = new LinkedHashMap<>();
            for(Map.Entry<String,FishingRegions> fishingRegions1 : fishingRegions.entrySet()){
                map.put(fishingRegions1.getKey(),fishingRegions1.getValue().serialize());
            }
            configManager.setConfig("world.region",map);
            configManager.saveConfig();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static FishingRegions getFishingRegion(String _name){
        return fishingRegions.get(_name);
    }
}
