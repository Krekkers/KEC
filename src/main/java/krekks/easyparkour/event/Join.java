package krekks.easyparkour.event;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import static krekks.easyparkour.Config.*;
import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.misc.ItemMaker.makeGoBackItem;
import static krekks.easyparkour.playerdata.PlayerDataHandler.AddToList;
import static org.bukkit.Bukkit.getLogger;

public class Join implements Listener {
    ItemStack goBackItem = makeGoBackItem(Material.matchMaterial(config.getString("backmaterial")));
    @EventHandler
    void joinEvent(PlayerJoinEvent e){
        //adds player to list
        if(!joinLogging) return;
        AddToList(e.getPlayer(), new Location(e.getPlayer().getWorld(), spawnX,spawnY,spawnZ));
        getLogger().info("Player : " + e.getPlayer().getName() + " Has been added to the list!");
        e.getPlayer().getInventory().setItem(8, goBackItem);
    }
}