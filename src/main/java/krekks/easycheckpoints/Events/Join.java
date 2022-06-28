package krekks.easycheckpoints.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import static krekks.easycheckpoints.Events.InventoryEvents.goBackItem;
import static krekks.easycheckpoints.PlayerData.PlayerDataHandler.AddToList;
import static org.bukkit.Bukkit.getLogger;

public class Join implements Listener {
    @EventHandler
    void JoinEvent(PlayerJoinEvent e){
        //adds player to list
        AddToList(e.getPlayer(), null);
        getLogger().info("Player : " + e.getPlayer().getName() + " Has been added to the list!");
        e.getPlayer().getInventory().setItem(8, goBackItem);
    }
}
