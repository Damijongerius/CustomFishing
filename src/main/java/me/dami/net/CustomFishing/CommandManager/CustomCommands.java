package me.dami.net.CustomFishing.CommandManager;

import me.dami.net.CustomFishing.GUI.FishingGuiManager;
import me.dami.net.CustomFishing.GUI.FishingGuis;
import me.dami.net.CustomFishing.GUI.FishingMenuGui;
import me.dami.net.CustomFishing.Region.FishingRegionManager;
import me.dami.net.CustomFishing.Region.RegionManaging;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class CustomCommands implements TabExecutor {

    private FishingMenuGui fishingMenuGui;
    private RegionManaging regionManaging;

    public CustomCommands(FishingMenuGui _fishingMenuGui) {
        this.fishingMenuGui = _fishingMenuGui;
    }


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("sender must be a player");
            return false;
        }

        System.out.println("used command");

        if (!command.getName().equalsIgnoreCase("fishing")) {
            return false;
        }

        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(ChatColor.DARK_RED + "Missing an argument /Fishing <argument>");
            return false;
        }
        String region = regionManaging.getRegion(p).iterator().next().getId();
        System.out.println(region);
        if (args.length == 2) {
            //test if region exists
            //else return

        } else {
            //set region
        }

        if (region == null) return false;

        switch (args[0]) {
            case "i":
            case "information":
                ShowCatchItems(region);
                break;

            case "m":
            case "menu":
                OpenGuiMenu(region, p);
                break;

            case "r":
            case "reset":
                resetRegion(region);
                break;

            case "h":
            case "help"://explain how it works
                break;

            default:
                p.sendMessage(ChatColor.DARK_RED + "Unknown argument; Known arguments are: (information,menu,reset,help)");

        }


        return false;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> options = null;
        Player p = (Player) sender;


        if (command.getName().equalsIgnoreCase("fishing")) {
            if (args.length == 1) {
                options = Arrays.asList("information", "menu", "reset", "help", "confirm");
            }
            if (args.length == 2) {
                options = RegionManaging.GetRegions();

            }
        }
        return options;
    }


        private void ShowCatchItems(String _region){

        }

        private void OpenGuiMenu(String _region, Player p){

            FishingGuiManager.AddGui(p, FishingGuis.FishingRegionGui);
            FishingMenuGui.OpenGui(p, _region);
        }

        private void resetRegion(String _region){

        }

}
