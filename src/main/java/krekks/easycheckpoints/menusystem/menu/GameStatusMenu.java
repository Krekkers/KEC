package krekks.easycheckpoints.menusystem.menu;

import krekks.easycheckpoints.menusystem.Menu;
import krekks.easycheckpoints.menusystem.MenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static krekks.easycheckpoints.EasyCheckpoints.*;
import static krekks.easycheckpoints.misc.CustomItem.createCustomItem;
import static krekks.easycheckpoints.misc.StartGame.startGame;

public class GameStatusMenu extends Menu {
    public GameStatusMenu(MenuUtility utility) {
        super(utility);
    }

    @Override
    public String getMenuName() {
        return "GameStatus";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void setMenuItems() {
        //gamestate item
        ItemStack gameStatus = null;
        if(Toggle) gameStatus = createCustomItem(Material.LIME_STAINED_GLASS_PANE, 1, "&aToggled", "True", "This displays the status of the game", "True = started" , "False = not active");
        if(!Toggle) gameStatus = createCustomItem(Material.RED_STAINED_GLASS_PANE, 1, "&cToggled", "False", "This displays the status of the game", "True = started" , "False = not active");
        //join logging
        ItemStack join = null;
        if(joinLogging) join = createCustomItem(Material.LIME_STAINED_GLASS_PANE, 1, "&aJoin Logging", "True" , "This displays Join Logging", "True = on" , "False = on");
        if(!joinLogging) join = createCustomItem(Material.RED_STAINED_GLASS_PANE, 1, "&cJoin Logging", "False" , "This displays Join Logging", "True = on" , "False = off");
        //Seconds passed
        ItemStack seconds;
        seconds = createCustomItem(Material.PURPLE_STAINED_GLASS_PANE, 1, "&eSeconds : &c" + sec, "Time spent in seconds");

        ItemStack refresh;
        refresh = createCustomItem(Material.BLUE_STAINED_GLASS_PANE, 1, "&bRefresh", "Refreshes the UI");

        //setting the menu items
        inventory.setItem(8, refresh);
        inventory.setItem(11, gameStatus);
        inventory.setItem(13, seconds);
        inventory.setItem(15, join);
        fillInventoryWith(createCustomItem(Material.BLACK_STAINED_GLASS_PANE, 1, "&8Filler"));

    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Toggled")){
            startGame(e.getWhoClicked());
            e.getWhoClicked().getWorld().playSound(e.getWhoClicked().getLocation(), MENUCLICKNOISE,3,1);
            setMenuItems();
        }
        if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Join Logging")){
            joinLogging = !joinLogging;
            e.getWhoClicked().sendMessage(ChatColor.YELLOW + "Join Logging : " + ChatColor.RED + joinLogging);
            e.getWhoClicked().getWorld().playSound(e.getWhoClicked().getLocation(), MENUCLICKNOISE,3,1);
            setMenuItems();
        }
        if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Refresh")){
            setMenuItems();
            e.getWhoClicked().getWorld().playSound(e.getWhoClicked().getLocation(), MENUCLICKNOISE,3,1);
        }
    }
}
