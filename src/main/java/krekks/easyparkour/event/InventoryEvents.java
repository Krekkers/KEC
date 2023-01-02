package krekks.easyparkour.event;

import krekks.easyparkour.system.menusystem.Menu;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.InventoryHolder;

public class InventoryEvents implements Listener {
    @EventHandler
    void inventoryClick(InventoryClickEvent e){
        //making sure
        InventoryHolder holder = e.getInventory().getHolder();
        if (holder instanceof Menu menu) {
            e.setCancelled(true);
            if (e.getCurrentItem() == null) {
                return;
            }
            menu.handleMenu(e);
        }
        if(e.getWhoClicked().hasPermission("krekks.perms")) return;
        e.setCancelled(true);
    }
    //prevents people from dropping the item before the game starts
    @EventHandler
    void itemDropEvent(PlayerDropItemEvent e){
        if(e.getPlayer().hasPermission("krekks.perms")) return;
        e.setCancelled(true);
    }


}
