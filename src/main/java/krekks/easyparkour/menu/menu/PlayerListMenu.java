package krekks.easyparkour.menu.menu;

import krekks.easyparkour.playerdata.PlayerData;
import krekks.easyparkour.menu.Menu;
import krekks.easyparkour.menu.MenuUtility;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.misc.item.CustomItem.createCustomItem;
import static krekks.easyparkour.playerdata.PlayerDataHandler.playerList;

public class PlayerListMenu extends Menu {

    int page = 0;
    int listLimit = 5;
    public PlayerData menuOwner_PD;

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
        //loops through all players and gets the corresponding player-data
        for(Player p : Bukkit.getOnlinePlayers()){
            PlayerData pd = playerList.get(p);


            ItemStack item = createCustomItem(Material.matchMaterial("Grass_block"),1
                    ,pd.getPlayer().getName()
                    ,"Points : " + pd.getPoints()
                    ,"Level : " + pd.getLevel());
            inventory.addItem(item);
        }
        inventory.setItem(24, createCustomItem(Material.RED_WOOL,1, "&cPrevious"));
        inventory.setItem(25, createCustomItem(Material.GRAY_WOOL,1, "&2Page : " + (page + 1)));
        inventory.setItem(26, createCustomItem(Material.GREEN_WOOL,1, "&aNext"));

    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        //should handle clicks
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        //pagination
        assert item != null;
        if(item.getItemMeta().getDisplayName().contains("Next")){
            page += 1;
            setMenuItems();
        }
        if(item.getItemMeta().getDisplayName().contains("Previous") && page >= 1){
            page -= 1;
            setMenuItems();
        }
        p.playSound(p.getLocation(), config.MENUCLICKNOISE,3,1);
    }
}
