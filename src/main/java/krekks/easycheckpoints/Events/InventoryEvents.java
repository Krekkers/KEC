package krekks.easycheckpoints.Events;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

import static krekks.easycheckpoints.EasyCheckpoints.Toggle;

public class InventoryEvents implements Listener {
    //italian code!
    public static ItemStack goBackItem = createGoBackItem(Material.REDSTONE, "Go Back", "Use this item to go back","or use /back");

    @EventHandler
    void InventoryUpdater(InventoryClickEvent e){
        if(Toggle && !e.getWhoClicked().hasPermission("Krekks.perms")){
            if(e.getSlot() < 0) return;             //avoids big scary error
            e.setCancelled(true);
            e.getInventory().setItem(8, goBackItem);
        }

    }
    @EventHandler
    void ItemDropEvent(PlayerDropItemEvent e){
        if (Toggle) e.setCancelled(true);
        e.getPlayer().getInventory().setItem(8, goBackItem);

    }
    //dont ask why i have put this here.
    public static ItemStack createGoBackItem(Material mat,String name, String... lore){
        ItemStack item = new ItemStack(mat);
        final ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        meta.setLore(Arrays.asList(lore));
        item.setItemMeta(meta);
        return item;
    }


}
