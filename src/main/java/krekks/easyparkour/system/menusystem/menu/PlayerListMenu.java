package krekks.easyparkour.system.menusystem.menu;

import krekks.easyparkour.playerdata.PlayerData;
import krekks.easyparkour.system.menusystem.Menu;
import krekks.easyparkour.system.menusystem.MenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static krekks.easyparkour.misc.item.CustomItem.createCustomItem;
import static krekks.easyparkour.playerdata.PlayerDataHandler.playerList;

public class PlayerListMenu extends Menu {
    public PlayerListMenu(MenuUtility utility) {
        super(utility);
    }

    @Override
    public String getMenuName() {
        return "Players";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void setMenuItems() {
        //loops trough all players and gets the corresponding playerdata
        for(Player p : Bukkit.getOnlinePlayers()){
            PlayerData pd = playerList.get(p);

            ItemStack item = createCustomItem(Material.matchMaterial("Grass_block"),1
                    ,pd.getPlayer().getName()
                    ,"Points : " + pd.getPoints()
                    ,"Level : " + pd.getLevel());

            inventory.addItem(item);

        }



    }

    @Override
    public void handleMenu(InventoryClickEvent e) {

    }
}
