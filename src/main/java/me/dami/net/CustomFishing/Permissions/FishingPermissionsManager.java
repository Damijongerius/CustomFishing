package me.dami.net.CustomFishing.Permissions;

import me.dami.net.CustomFishing.Config.ConfigurationManager;
import me.dami.net.CustomFishing.Main.CustomizableFishing;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.permissions.PermissionAttachment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FishingPermissionsManager implements Listener {

    private final CustomizableFishing main;
    private final ConfigurationManager fishingpermissions;
    private HashMap<UUID, PermissionAttachment> playerPermissions = new HashMap<>();
    private List<String> adminitrators = new ArrayList<>();
    public FishingPermissionsManager(CustomizableFishing _main, ConfigurationManager _fishingPermissions){
        this.main = _main;
        this.fishingpermissions = _fishingPermissions;
        SetUp();
    }

    @EventHandler
    public void OnPlayerJoin(PlayerJoinEvent e){
        Player player = e.getPlayer();
        setupPermissions(player);
    }
    @EventHandler
    public void OnPlayerLeave(PlayerQuitEvent e){
        Player player = e.getPlayer();
        playerPermissions.remove(player.getUniqueId());
    }

    public void setupPermissions(Player player) {
        PermissionAttachment attachment = player.addAttachment(main);
        this.playerPermissions.put(player.getUniqueId(), attachment);
        try{
            permissionsSetter(player);
        } catch (Exception ignored) {}
    }

    private void permissionsSetter(Player p) throws Exception {
        UUID uuid = p.getUniqueId();
        PermissionAttachment attachment = this.playerPermissions.get(uuid);
        ConfigurationSection fishingSection = null;
        try {
            fishingSection = fishingpermissions.getSection("fishing");
        } catch (Exception ignored) {
            System.out.println("error");
        }
        if(fishingSection != null){
            for(String groups : fishingSection.getKeys(false)) {
                ConfigurationSection section = fishingpermissions.getSection("fishing." + groups);
                if(!groups.equalsIgnoreCase("member")){
                    List<String> players = section.getStringList("players");
                    if(players.contains(uuid)){
                       for(String perm : section.getStringList("permissions")){
                           attachment.setPermission(perm, true);
                       }
                    }
                }else{
                    for(String perm : section.getStringList("permissions")){
                        System.out.println(perm);
                        attachment.setPermission(perm, true);
                    }
                }
            }
        }
    }

    private void SetUp(){
        ConfigurationSection section = null;
        try {
            section = fishingpermissions.getSection("fishing");
        } catch (Exception ignored) {}

        if(section == null){
            List<String> permsAdmin = new ArrayList<>();
            permsAdmin.add("Fishing.Manage");
            fishingpermissions.setConfig("fishing.administrator.permissions", permsAdmin);

            List<String> permsMember = new ArrayList<>();
            permsMember.add("Fishing.Fish");
            fishingpermissions.setConfig("fishing.member.permissions", permsMember);

            fishingpermissions.saveConfig();
        }
    }

}
