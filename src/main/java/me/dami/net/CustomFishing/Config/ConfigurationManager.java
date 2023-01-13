package me.dami.net.CustomFishing.Config;

import me.dami.net.CustomFishing.Main.CustomizableFishing;
import org.bukkit.configuration.Configuration;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigurationManager {

    private static Map<String,ConfigurationManager> managers = new LinkedHashMap<>();

    private File configFile;
    private static CustomizableFishing main;
    private FileConfiguration config;

    private String fileName;

    public ConfigurationManager(CustomizableFishing _main, String _fileName){
        this.main = _main;
        fileName = _fileName;
        managers.put(_fileName,this);

        startUp();
    }

    //get config data
    public void startUp(){
        try {
            configFile = new File(main.getDataFolder(), fileName + ".yml");
        } catch (Exception e) {
            main.saveResource( fileName+".yml", false);
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public ConfigurationSection getSection(String path) throws Exception {
        return config.getConfigurationSection(path);
    }

    public void setConfig(String _path, Object _info){
        config.set(_path, _info);
    }

    //saveConfigFile
    public void saveConfig() {
        try {
            this.config.save(this.configFile);
        } catch (IOException e) {
            main.getLogger().severe("Error saving config: " + e.getMessage());
        }
    }

    public static ConfigurationManager getConfigurationManager(String _fileName)
    {
        return managers.get(_fileName);
    }

}
