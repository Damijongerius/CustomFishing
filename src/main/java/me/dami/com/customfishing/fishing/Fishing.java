package me.dami.com.customfishing.fishing;

import me.dami.com.customfishing.CustomFIshing;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.util.List;
import java.util.Random;

public class Fishing implements Listener {

    private Random random = new Random();
    private CustomFIshing main;
    public Fishing(CustomFIshing _main){
        this.main = _main;
    }

    @EventHandler
    public void onPlayerFish(PlayerFishEvent event) {
        if (event.getState() != PlayerFishEvent.State.CAUGHT_FISH) {
            return;
        }
        Player player = event.getPlayer();

/*
        event.setCancelled(true);
        FishHook hook = event.getHook();
        Entity droppedI = player.getWorld().dropItem(hook.getLocation(), item);
        hook.setHookedEntity(droppedI);
        hook.pullHookedEntity();
        hook.remove();
 */


    }
}
