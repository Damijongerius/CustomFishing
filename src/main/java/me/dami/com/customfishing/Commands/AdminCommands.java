package me.dami.com.customfishing.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class AdminCommands implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("sender must be a player");
            return true;
        }

        Player p = (Player) sender;


        if (command.getName() == "Fishing") {
            if (args.length == 0) {
                p.sendMessage(ChatColor.DARK_RED + "Missing an argument /Fishing <argument>");
            } else {
                switch (args[0]) {
                    case "i":
                    case "information": {
                        if(args.length == 2){
                            //switch for each existing region
                            break;
                        }
                        //show things you can catch
                        break;
                    }

                    case "m":
                    case "menu": {
                        if(args.length == 2){
                            //switch for each existing region
                            break;
                        }
                        //open menu with configuration
                        break;
                    }

                    case "r":
                    case "reset": {
                        if(args.length == 2){
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
                    case "confirm":{
                        //confirm
                    }

                    default: {
                        p.sendMessage(ChatColor.DARK_RED + "Unknown argument /Fishing information,menu,reset");
                    }
                }
            }
        }
        return false;
    }
}
