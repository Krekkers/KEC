package krekks.easyparkour.system.menusystem.menu;

import krekks.easyparkour.system.menusystem.Menu;
import krekks.easyparkour.system.menusystem.MenuUtility;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static krekks.easyparkour.misc.item.CustomItem.createCustomItem;

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
        ItemStack refresh;
        refresh = createCustomItem(Material.BLUE_STAINED_GLASS_PANE, 1, "&bThis menu is useless", "");

        //setting the menu items
        inventory.setItem(8, refresh);
        fillInventoryWith(createCustomItem(Material.BLACK_STAINED_GLASS_PANE, 1, "&8Filler"));

    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
    }
}
