package me.dami.com.customfishing;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.Vector3;
import com.sk89q.worldedit.util.Location;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.dami.com.customfishing.Files.ConfigManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.List;
import java.util.Random;

public class Fishing implements Listener {

    private Random random = new Random();
    private CustomFIshing main;
    private WorldGuardPlugin wgp;
    public Fishing(CustomFIshing _main){
        this.main = _main;

        wgp = (WorldGuardPlugin) main.getServer().getPluginManager().getPlugin("WorldGuard");
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH) {
            return;
        }


        Player player = event.getPlayer();
        enterRegion(player);



       // player.sendMessage(ChatColor.GREEN + "You have received a reward for fishing: ");
/*
        event.setCancelled(true);
        FishHook hook = event.getHook();
        Entity droppedI = player.getWorld().dropItem(hook.getLocation(), item);
        hook.setHookedEntity(droppedI);
        hook.pullHookedEntity();
        hook.remove();
 */


    }
    public ProtectedRegion getRegion(Player player) {
        LocalPlayer localPlayer = wgp.wrapPlayer(player);
        Vector3 playerVector = localPlayer.getLocation().toVector();
        RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regionManager = regionContainer.get(BukkitAdapter.adapt(player.getWorld()));
        ApplicableRegionSet applicableRegionSet = regionManager.getApplicableRegions(playerVector.toBlockPoint());
        System.out.println(applicableRegionSet.getRegions());
    }
}
