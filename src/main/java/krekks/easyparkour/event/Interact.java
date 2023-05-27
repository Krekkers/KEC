package krekks.easyparkour.event;

import krekks.easyparkour.playerdata.PlayerDataHandler;
import krekks.easyparkour.menu.menu.LevelSelectionMenu;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.Objects;

import static krekks.easyparkour.misc.item.ItemMaker.checkpointItem;
import static krekks.easyparkour.misc.item.ItemMaker.levelSelector;
import static krekks.easyparkour.playerdata.PlayerDataHandler.getPlayerDataFromList;
import static krekks.easyparkour.menu.MenuManager.getMenuUtility;

public class Interact implements Listener {
    @EventHandler
    void interactEvent(PlayerInteractEvent e){
        if(e.getItem() == null) return;
        //go back
        if (Objects.equals(e.getItem().getItemMeta(), checkpointItem.getItemMeta())) {
            getPlayerDataFromList(e.getPlayer()).goToCheckPoint();
            e.setCancelled( true);
        }
        //level selector
        if (Objects.equals(e.getItem().getItemMeta(), levelSelector.getItemMeta())) {
            Player player = e.getPlayer();
            LevelSelectionMenu menu = new LevelSelectionMenu(getMenuUtility(player));
            menu.pd = PlayerDataHandler.getPlayerDataFromList(player);
            menu.openMenu();
        }

    }
    @EventHandler
    void noPlace(BlockPlaceEvent e){
        if(!e.getPlayer().hasPermission("krekks.perms")){
            e.setCancelled(true);
        }
    }
}
