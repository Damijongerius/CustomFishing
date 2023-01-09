package me.dami.com.customfishing.Commands;

import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.dami.com.customfishing.menus.FishingGuiManager;
import me.dami.com.customfishing.menus.FishingGuis;
import me.dami.com.customfishing.menus.RegionInventory;
import me.dami.com.customfishing.region.RegionRewards;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AdminCommands implements TabExecutor {
    RegionContainer container;
    List<String> regions = new ArrayList<>();

    RegionInventory regionInventory = new RegionInventory();
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("sender must be a player");
            return false;
        }
        Player p = (Player) sender;


        if(container == null){
            container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regionM = container.get(new BukkitWorld(p.getWorld()));
            for (Map.Entry<String,ProtectedRegion> region : regionM.getRegions().entrySet()) {
                regions.add(region.getKey());
            }
        }

        if(command.getName().equalsIgnoreCase("fishing")) {
            if (args.length == 0) {
                p.sendMessage(ChatColor.DARK_RED + "Missing an argument /Fishing <argument>");
            } else {
                switch (args[0]) {
                    case "i":
                    case "information": {
                        if (args.length == 2) {
                            //switch for each existing region
                            break;
                        }
                        //show things you can catch
                        break;
                    }

                    case "m":
                    case "menu": {
                        if (args.length == 2) {
                            openInventory(p,args[1]);
                            break;
                        }
                        openInventory(p);
                        break;
                    }

                    case "r":
                    case "reset": {
                        if (args.length == 2) {
                            //switch for each existing region
                            break;
                        }
                        //reset region to default settings
                        break;
                    }

                    case "h":
                    case "help": {
                        //explain how it works
                        break;
                    }

                    case "c":
                    case "confirm": {
                        //confirm reset
                        break;
                    }

                    default: {
                        p.sendMessage(ChatColor.DARK_RED + "Unknown argument; Known arguments are: (information,menu,reset,help,confirm)");
                    }
                }
            }
        }
        return true;
    }

    public void openInventory(Player p){
        String region = RegionRewards.getRegion(p).iterator().next().getId();
        if(region != null) {
            FishingGuiManager.AddGui(p, FishingGuis.FishingRegionGui);
            regionInventory.OpenGui(p, region);
        }else
            p.sendMessage("no region found");
    }
    public void openInventory(Player p, String _region){
        container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regionM = container.get(new BukkitWorld(p.getWorld()));
        for (Map.Entry<String,ProtectedRegion> region : regionM.getRegions().entrySet()) {
            if(_region == region.getKey()){
                FishingGuiManager.AddGui(p, FishingGuis.FishingRegionGui);
                regionInventory.OpenGui(p, _region);
            }
        }

        if(!regions.contains(_region)){
            UpdateRegions(p);
        }
    }

    private void UpdateRegions(Player p){
        container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regionM = container.get(new BukkitWorld(p.getWorld()));
        regions.clear();
        for (Map.Entry<String,ProtectedRegion> region : regionM.getRegions().entrySet()) {
            regions.add(region.getKey());
        }
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> options = null;
        Player p = (Player) sender;

        if(container == null){
            container = WorldGuard.getInstance().getPlatform().getRegionContainer();
            RegionManager regionM = container.get(new BukkitWorld(p.getWorld()));
            for (Map.Entry<String,ProtectedRegion> region : regionM.getRegions().entrySet()) {
                regions.add(region.getKey());
            }
        }



        if(command.getName().equalsIgnoreCase("fishing")) {
            if (args.length == 1) {
                options = Arrays.asList("information", "menu", "reset", "help", "confirm");
            }
            if(args.length == 2){
                if(regions != null){
                    options = regions;
                }
            }
        }

        return options;
    }
}
