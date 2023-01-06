package me.dami.com.customfishing.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminCommandsTabComplete implements TabCompleter {
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args) {
        List<String> options = new ArrayList<String>();

        if(command.getName() == "Fishing"){
            if(args.length == 1){
                List<String> Options = Arrays.asList("information", "menu", "reset", "help");

            }
        }


        return null;
    }
}
