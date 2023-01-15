package me.dami.net.CustomFishing.CommandManager;

import me.dami.net.CustomFishing.FishingClasses.FishingRegions;
import me.dami.net.CustomFishing.FishingClasses.FishingStatus;
import me.dami.net.CustomFishing.GUI.FishingGuiInfo;
import me.dami.net.CustomFishing.GUI.FishingGuiManager;
import me.dami.net.CustomFishing.GUI.FishingGuis;
import me.dami.net.CustomFishing.GUI.Main.FishingMenuGui;
import me.dami.net.CustomFishing.Region.FishingRegionManager;
import me.dami.net.CustomFishing.Region.RegionManaging;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

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

        if (!command.getName().equalsIgnoreCase("fishing")){
            return false;
        }

        Player p = (Player) sender;
        if (args.length == 0) {
            p.sendMessage(ChatColor.DARK_RED + "Missing an argument /Fishing <argument>");
            return false;
        }
        String region = regionManaging.getRegion(p).iterator().next().getId();
        if (args.length == 2) {
            if(RegionManaging.GetRegions().contains(args[1])){
                region = args[1];
            }else{
                p.sendMessage("that is not a real region");
                return false;
            }
        }

        if (region == null) return false;

        switch (args[0]) {
            case "i":
            case "information":
                ShowCatchItems(region, p);
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
                options = Arrays.asList("information", "menu", "reset", "help");
            }
            if (args.length == 2) {
                options = RegionManaging.GetRegions();

            }
        }
        return options;
    }


        private void ShowCatchItems(String _region, Player p){
        FishingRegions fishingRegion = FishingRegionManager.getFishingRegion(_region);
        p.sendMessage(_region + " items = " + fishingRegion.getItems().toString());
        }

        private void OpenGuiMenu(String _region, Player p){

            FishingGuiInfo guiInfo = new FishingGuiInfo();
            guiInfo.setActiveGui(FishingGuis.FishingRegionGui);
            guiInfo.setRegion(_region);
            guiInfo.setInventoryIndex(new Integer[] {1,100});
            FishingGuiManager.AddPlayer(p,guiInfo);
            FishingMenuGui.OpenGui(p, _region);
        }

        private void resetRegion(String _region){
            FishingRegionManager.getFishingRegion(_region).ClearItems();
            FishingRegionManager.getFishingRegion(_region).setStatus(FishingStatus.OFF);
        }

}
