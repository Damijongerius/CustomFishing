package me.dami.net.CustomFishing.Fishing;

import me.dami.net.CustomFishing.Main.CustomizableFishing;
import org.bukkit.entity.Entity;
import org.bukkit.entity.FishHook;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;
import java.util.Random;

public class Fishing implements Listener {

    private Random random = new Random();
    private CustomizableFishing main;

    public Fishing(CustomizableFishing _main) {
        this.main = _main;
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH) {
            return;
        }
        Player player = event.getPlayer();

        event.setCancelled(true);
        FishHook hook = event.getHook();
/*
        String region = RegionRewards.getRegion(player).iterator().next().getId();
        FishingRegion fr = ConfigManager.getFishingRegion(Objects.requireNonNull(region));

        FishingItem randomItem = fr.GetRandomItem();
        ItemStack rItem = randomItem.getItem().clone();
        rItem.setAmount(randomItem.getDropAmount());
        Entity droppedI = player.getWorld().dropItem(hook.getLocation(), rItem);
        player.giveExp(Math.round(randomItem.getXp(true)[0]));
        hook.setHookedEntity(droppedI);
        hook.pullHookedEntity();
        hook.remove();
 */
    }
}
