package me.dami.net.CustomFishing.Region;

import com.sk89q.worldedit.bukkit.BukkitAdapter;
import com.sk89q.worldedit.bukkit.BukkitWorld;
import com.sk89q.worldedit.math.Vector3;
import com.sk89q.worldguard.LocalPlayer;
import com.sk89q.worldguard.WorldGuard;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;
import com.sk89q.worldguard.protection.regions.RegionContainer;
import me.dami.net.CustomFishing.Main.CustomizableFishing;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class RegionManaging {

    private static WorldGuardPlugin wgp;

    private static void SetWorldGuardPlugin(WorldGuardPlugin _wgp){
        wgp = _wgp;
    }

    private static WorldGuardPlugin GetWorldGuardPlugin(){
        return wgp;
    }

    private static  RegionContainer container;

    private static List<String> regions = new ArrayList<>();

    public static Set<ProtectedRegion> getRegion(Player player) {

        if(wgp == null){
            wgp = (WorldGuardPlugin) CustomizableFishing.get().getServer().getPluginManager().getPlugin("WorldGuard");
        }

        LocalPlayer localPlayer = wgp.wrapPlayer(player);
        Vector3 playerVector = localPlayer.getLocation().toVector();
        RegionContainer regionContainer = WorldGuard.getInstance().getPlatform().getRegionContainer();
        com.sk89q.worldguard.protection.managers.RegionManager regionManager = regionContainer.get(BukkitAdapter.adapt(player.getWorld()));
        Set<ProtectedRegion> applicableRegionSet = regionManager.getApplicableRegions(playerVector.toBlockPoint()).getRegions();

        return applicableRegionSet;
    }

    public static List<String> getRegions() {
        if (container == null) {
            container = WorldGuard.getInstance().getPlatform().getRegionContainer();
        }

        regions = new ArrayList<>();
        com.sk89q.worldguard.protection.managers.RegionManager regionM = container.get(new BukkitWorld(Bukkit.getWorlds().get(0)));
        for (Map.Entry<String,ProtectedRegion> region : regionM.getRegions().entrySet()) {
            regions.add(region.getKey());
        }

        return regions;
    }

    public static  List<String> GetRegions(){
        return regions;
    }
}
