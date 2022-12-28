package krekks.easyparkour.event;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;

import static krekks.easyparkour.Config.spawn;
import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.misc.ItemMaker.makeGoBackItem;
import static krekks.easyparkour.playerdata.PlayerDataHandler.AddToList;

public class Join implements Listener {
    ItemStack goBackItem = makeGoBackItem(Material.matchMaterial(config.getString("parkoursettings.items.goback.backmaterial")));
    @EventHandler
    void joinEvent(PlayerJoinEvent e){
        AddToList(e.getPlayer(), spawn);
        e.getPlayer().getInventory().setItem(8, goBackItem);
    }
}
