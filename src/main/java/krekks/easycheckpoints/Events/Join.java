package krekks.easycheckpoints.Events;

import static krekks.easycheckpoints.PlayerData.PlayerDataHandler.*;
import static org.bukkit.Bukkit.getLogger;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Join implements Listener {
    @EventHandler
    void JoinEvent(PlayerJoinEvent e){
        //adds player to list
        AddToList(e.getPlayer(), null);
        getLogger().info("Player : " + e.getPlayer().getName() + " Has been added to the list!");
    }
}
