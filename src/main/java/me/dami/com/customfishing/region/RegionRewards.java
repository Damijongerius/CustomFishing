package me.dami.com.customfishing.region;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.math.Vector3;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.protection.ApplicableRegionSet;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import org.bukkit.entity.Player;

public class RegionRewards {
    private static

    public ProtectedRegion getRegion(Player player) {
        LocalPlayer localPlayer = wgp.wrapPlayer(player);
        Vector3 playerVector = localPlayer.getLocation().toVector();
        RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        RegionManager regionManager = regionContainer.get(BukkitAdapter.adapt(player.getWorld()));
        ApplicableRegionSet applicableRegionSet = regionManager.getApplicableRegions(playerVector.toBlockPoint());
        System.out.println(applicableRegionSet.getRegions());
    }
}
