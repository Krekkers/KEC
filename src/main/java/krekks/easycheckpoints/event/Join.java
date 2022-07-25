package krekks.easycheckpoints.event;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static krekks.easycheckpoints.EasyCheckpoints.joinLogging;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.AddToList;
import static org.bukkit.Bukkit.getLogger;

public class Join implements Listener {
    ItemStack goBackItem = setGoBackItem(Material.REDSTONE);
    public static ItemStack setGoBackItem(Material mat){
        ItemStack newItem = new ItemStack(mat);
        ItemMeta itemMeta = newItem.getItemMeta();
        itemMeta.setDisplayName(ChatColor.YELLOW + "Go " + ChatColor.RED + "Back");
        newItem.setItemMeta(itemMeta);
        return newItem;
    }
    @EventHandler
    void JoinEvent(PlayerJoinEvent e){
        //adds player to list
        if(!joinLogging) return;
        if(!e.getPlayer().hasPermission("krekks.perms"))return;
        AddToList(e.getPlayer(), null);
        getLogger().info("Player : " + e.getPlayer().getName() + " Has been added to the list!");
        e.getPlayer().getInventory().setItem(8, goBackItem);

    }
}
