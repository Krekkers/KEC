package krekks.easyparkour.system.menusystem.menu;

import krekks.easyparkour.playerdata.PlayerData;
import krekks.easyparkour.system.menusystem.Menu;
import krekks.easyparkour.system.menusystem.MenuUtility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;


import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.misc.item.CustomItem.createCustomItem;

public class PlayerStatsMenu extends Menu {

    public PlayerStatsMenu(MenuUtility utility) {
        super(utility);
    }

    //extra values
    public PlayerData playerData;

    @Override
    public String getMenuName() {
        return "Player Status : " + playerData.getPlayer().getName();
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void setMenuItems() {

        //skull item for player display
        ItemStack playerSkull = createCustomItem(Material.PLAYER_HEAD, 1, "&r&l&e" + playerData.getPlayer().getName());
        SkullMeta meta = (SkullMeta) playerSkull.getItemMeta();
        assert meta != null;
        meta.setOwnerProfile(playerData.getPlayer().getPlayerProfile());
        playerSkull.setItemMeta(meta);
        //checkpoint
        ItemStack finishCount = createCustomItem(Material.EMERALD_BLOCK, 1 ,
                config.primary + "Count : "
                + config.secondary + playerData.getFinishCount());
        //checkpoint
        ItemStack checkpointLocation = createCustomItem(Material.BARRIER,1, config.error + "Player has no checkpoint");
        if(playerData.getCheckpointLocation() != null){
            checkpointLocation = createCustomItem(Material.ARROW,1, config.primary + "Checkpoint","Click to go to players last checkpoint.", "Coordinates",
                    "x : " + playerData.getCheckpointLocation().getX(),
                    "y : " + playerData.getCheckpointLocation().getY(),
                    "z : " + playerData.getCheckpointLocation().getZ());
        }
        inventory.setItem(2,playerSkull);
        inventory.setItem(4, finishCount);
        inventory.setItem(6, checkpointLocation);
        fillInventoryWith(createCustomItem(Material.BLACK_STAINED_GLASS_PANE, 1, "&8Filler"));
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Checkpoint")){
            Location newLoc = new Location(p.getWorld(),playerData.getCheckpointLocation().getX(),playerData.getCheckpointLocation().getY(),playerData.getCheckpointLocation().getZ());
            p.teleport(newLoc.add(0.5f,1f,0.5f));
            p.playSound(p.getLocation(), config.MENUCLICKNOISE,3,1);
            setMenuItems();
        }
    }
}
