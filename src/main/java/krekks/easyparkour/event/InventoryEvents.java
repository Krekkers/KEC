package krekks.easyparkour.event;

import krekks.easyparkour.menu.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.InventoryHolder;

public class InventoryEvents implements Listener {
    @EventHandler
    void inventoryClick(InventoryClickEvent e){
        //return and do nothing if item is null.
        if (e.getCurrentItem() == null) return;
        //making sure
        InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof Menu menu) {
            e.setCancelled(true);
            menu.handleMenu(e);
        }
        //set items if those slots are null
        /*
        if(e.getInventory().getItem(0) == null || e.getInventory().getItem(8) == null) {
            e.getInventory().setItem(8, ItemMaker.checkpointItem);
            e.getInventory().setItem(0, ItemMaker.levelSelector);
        }*/
        //if user has staff permissions they can move items in inventory
        //could cause duplication of checkpoint and level selector item
        if(e.getWhoClicked().hasPermission("krekks.staff")) return;
        e.setCancelled(true);



    }
    //prevents people from dropping the item before the game starts
    @EventHandler
    void itemDropEvent(PlayerDropItemEvent e){
        //allows admins to drop items
        if(e.getPlayer().hasPermission("krekks.staff")) return;
        e.setCancelled(true); //cancel event if player does not have staff permission
    }


}
