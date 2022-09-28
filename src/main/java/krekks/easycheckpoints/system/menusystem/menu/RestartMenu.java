package krekks.easycheckpoints.system.menusystem.menu;

import krekks.easycheckpoints.misc.ResetGame;
import krekks.easycheckpoints.system.menusystem.Menu;
import krekks.easycheckpoints.system.menusystem.MenuUtility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static krekks.easycheckpoints.EasyCheckpoints.MENUCLICKNOISE;
import static krekks.easycheckpoints.misc.CustomItem.createCustomItem;

public class RestartMenu extends Menu {
    public RestartMenu(MenuUtility utility) {
        super(utility);
    }

    @Override
    public String getMenuName() {
        return "Are you sure?";
    }

    @Override
    public int getSlots() {
        return 9;
    }

    @Override
    public void setMenuItems() {
        ItemStack yes = createCustomItem(Material.GREEN_STAINED_GLASS_PANE, 1, "&a&lYes" , "You are 100% sure you want to reset all stats.");
        ItemStack no = createCustomItem(Material.RED_STAINED_GLASS_PANE, 1, "&c&lNo" , "You are not sure you want to do this.");
        inventory.setItem(2, yes);
        inventory.setItem(6, no);
        fillInventoryWith(createCustomItem(Material.BLACK_STAINED_GLASS_PANE, 1, "&8Filler"));
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Yes")){
            p.playSound(p.getLocation(), MENUCLICKNOISE,3,1);
            ResetGame.RestartGame();
            closeMenu();
        }
        if(e.getCurrentItem().getItemMeta().getDisplayName().contains("No")){
            p.playSound(p.getLocation(), MENUCLICKNOISE,3,1);
            closeMenu();
        }
    }
}
