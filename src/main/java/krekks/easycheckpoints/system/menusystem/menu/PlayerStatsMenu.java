package krekks.easycheckpoints.system.menusystem.menu;

import krekks.easycheckpoints.playerdata.PlayerData;
import krekks.easycheckpoints.system.menusystem.Menu;
import krekks.easycheckpoints.system.menusystem.MenuUtility;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import static krekks.easycheckpoints.Config.MENUCLICKNOISE;
import static krekks.easycheckpoints.misc.CustomItem.createCustomItem;

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
        meta.setOwnerProfile(playerData.getPlayer().getPlayerProfile());
        playerSkull.setItemMeta(meta);
        //checkpoint
        ItemStack checkpointCount = createCustomItem(Material.GOLD_BLOCK, 1 ,"&eCount : &c" + playerData.getGoBackCounter());
        //finished
        ItemStack finished = null;
        if(playerData.getFinished()) finished = createCustomItem(Material.LIME_STAINED_GLASS_PANE, 1, "&aFinished", "True" , "Time it took : " + playerData.getSecondsToFinish() + " Seconds");
        if(!playerData.getFinished()) finished = createCustomItem(Material.RED_STAINED_GLASS_PANE, 1, "&cFinished", "False" , "This player has not finished yet.");
        //checkpoint
        ItemStack checkpointLocation = createCustomItem(Material.ARROW,1, "Player has no checkpoint");
        if(playerData.getCheckpointLocation() != null){
            checkpointLocation = createCustomItem(Material.ARROW,1, "&eCheckpoint","Click to go to players last checkpoint.", "Coordinates",
                    "x : " + playerData.getCheckpointLocation().getX(),
                    "y : " + playerData.getCheckpointLocation().getY(),
                    "z : " + playerData.getCheckpointLocation().getZ());
        }
        //


        inventory.setItem(2,playerSkull);
        inventory.setItem(4, checkpointCount);
        inventory.setItem(6, finished);
        inventory.setItem(22, checkpointLocation);
        fillInventoryWith(createCustomItem(Material.BLACK_STAINED_GLASS_PANE, 1, "&8Filler"));
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Checkpoint")){
            Location newLoc = new Location(p.getWorld(),playerData.getCheckpointLocation().getX(),playerData.getCheckpointLocation().getY(),playerData.getCheckpointLocation().getZ());
            p.teleport(newLoc.add(0.5f,1f,0.5f));
            p.playSound(p.getLocation(), MENUCLICKNOISE,3,1);
            setMenuItems();
        }
    }
}
