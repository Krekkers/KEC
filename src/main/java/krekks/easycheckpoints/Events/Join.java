package krekks.easycheckpoints.Events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import static krekks.easycheckpoints.EasyCheckpoints.*;
import static krekks.easycheckpoints.PlayerData.PlayerDataHandler.AddToList;
import static org.bukkit.Bukkit.getLogger;

public class Join implements Listener {
    @EventHandler
    void JoinEvent(PlayerJoinEvent e){
        //adds player to list
        if(!joinLogging) return;
        if(e.getPlayer().hasPermission("krekks.perms"))return;
        AddToList(e.getPlayer(), null);
        getLogger().info("Player : " + e.getPlayer().getName() + " Has been added to the list!");
        if(Toggle)
        e.getPlayer().getInventory().setItem(8, new ItemStack(Material.REDSTONE));
    }
}
