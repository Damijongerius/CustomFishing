package me.dami.com.customfishing.Commands;

import me.dami.com.customfishing.menus.RegionInventory;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminCommands implements TabExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("sender must be a player");
            return false;
        }

        Player p = (Player) sender;


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
                            //switch for each existing region
                            break;
                        }
                        RegionInventory regionInventory = new RegionInventory();
                        regionInventory.OpenGui(p, "region1");
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

                    case "init": {
                        //initialize for all regions
                        if (args.length == 2) {
                            //init for spesific region
                            break;
                        }
                        break;
                    }

                    default: {
                        p.sendMessage(ChatColor.DARK_RED + "Unknown argument; Known arguments are: (information,menu,reset,help,init,confirm)");
                    }
                }
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> options = null;

        if(command.getName().equalsIgnoreCase("fishing")) {
            if (args.length == 1) {
                options = Arrays.asList("information", "menu", "reset", "help", "init", "confirm");
            }
        }

        return options;
    }
}
