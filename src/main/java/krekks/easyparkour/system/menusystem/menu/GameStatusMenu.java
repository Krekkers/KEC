package krekks.easyparkour.system.menusystem.menu;

import krekks.easyparkour.system.menusystem.Menu;
import krekks.easyparkour.system.menusystem.MenuUtility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static krekks.easyparkour.Config.MENUCLICKNOISE;
import static krekks.easyparkour.Config.Toggle;
import static krekks.easyparkour.misc.CustomItem.createCustomItem;
import static krekks.easyparkour.misc.StartGame.startGame;

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

        ItemStack refresh;
        refresh = createCustomItem(Material.BLUE_STAINED_GLASS_PANE, 1, "&bRefresh", "Refreshes the UI");

        //setting the menu items
        inventory.setItem(8, refresh);
        inventory.setItem(11, gameStatus);
        fillInventoryWith(createCustomItem(Material.BLACK_STAINED_GLASS_PANE, 1, "&8Filler"));

    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Toggled")){
            startGame(e.getWhoClicked());
            p.playSound(p.getLocation(), MENUCLICKNOISE,3,1);
            setMenuItems();
        }
        if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Refresh")){
            setMenuItems();
            p.playSound(p.getLocation(), MENUCLICKNOISE,3,1);
        }
    }
}
