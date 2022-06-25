package krekks.easycheckpoints.Events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import static krekks.easycheckpoints.EasyCheckpoints.Toggle;

public class InventoryEvents implements Listener {

    @EventHandler
    void InventoryUpdater(InventoryClickEvent e){
        if(Toggle && !e.getWhoClicked().hasPermission("Krekks.perms")){
            e.setCancelled(true);
            e.getInventory().setItem(8, new ItemStack(Material.REDSTONE));
        }

    }
    @EventHandler
    void ItemDropEvent(PlayerDropItemEvent e){
        if (Toggle) e.setCancelled(true);

    }


}
