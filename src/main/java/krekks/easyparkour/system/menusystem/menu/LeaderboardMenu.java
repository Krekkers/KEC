package krekks.easyparkour.system.menusystem.menu;

import krekks.easyparkour.playerdata.PlayerData;
import krekks.easyparkour.system.leaderboardsystem.LeaderboardObj;
import krekks.easyparkour.system.menusystem.Menu;
import krekks.easyparkour.system.menusystem.MenuUtility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static krekks.easyparkour.Config.MENUCLICKNOISE;
import static krekks.easyparkour.misc.item.CustomItem.createCustomItem;
import static krekks.easyparkour.system.leaderboardsystem.LeaderboardLoader.lb_List;

public class LeaderboardMenu extends Menu {

    int page = 0;
    int listLimit = 5;
    public PlayerData menuOwner_PD;
    public LeaderboardMenu(MenuUtility utility) {
        super(utility);
    }

    @Override
    public String getMenuName() {
        return "Leaderboard";
    }

    @Override
    public int getSlots() {
        // 9 slots top 6 will be players lower 3 will be controls
        return 9;
    }

    @Override
    public void setMenuItems() {
        //menu items
        inventory.clear();
        int pagesize = 5;
        if(lb_List.size() < 5) pagesize = lb_List.size();
        //loop over page
        for(int i = listLimit * page; i < pagesize; i++){
            ItemStack item = null;
            Comparator<LeaderboardObj> comparator = Comparator.comparing(LeaderboardObj::getFinishCount).reversed();
            LeaderboardObj lb_player = lb_List.stream().sorted(comparator).toList().get(i);
            item = createCustomItem(Material.PLAYER_HEAD, 1, "&a" + lb_player.getName(),
                    "&aPosition : &c" + (i + 1),
                    "&aFinish Count : &c" + lb_player.getFinishCount(),
                    "&aPoints : &c" + lb_player.getPoints());
            SkullMeta sm = (SkullMeta) item.getItemMeta();
            assert sm != null;
            sm.setOwner(lb_player.getP().getName());
            item.setItemMeta(sm);
            inventory.addItem(item);

        }

        /*inventory.setItem(6, createCustomItem(Material.SKELETON_SKULL,1, "&a" + menuOwner_PD.getPlayer().getDisplayName(),
                "&aPoints : &c" + menuOwner_PD.getPoints(),
                "&aFinishcount : &c" + menuOwner_PD.getFinishCount()));*/
        inventory.setItem(6, createCustomItem(Material.RED_WOOL,1, "&cPrevious"));
        inventory.setItem(7, createCustomItem(Material.GRAY_WOOL,1, "&2Page : " + (page + 1)));
        inventory.setItem(8, createCustomItem(Material.GREEN_WOOL,1, "&aNext"));
        fillInventoryWith(createCustomItem(Material.GRAY_STAINED_GLASS_PANE, 1 , "&8#"));
        // 6 7 8 slots are for controlling pages
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
            p.playSound(p.getLocation(), MENUCLICKNOISE,3,1);
        }
        if(item.getItemMeta().getDisplayName().contains("Previous") && page >= 1){
            page -= 1;
            setMenuItems();
            p.playSound(p.getLocation(), MENUCLICKNOISE,3,1);
        }
    }
    public List<LeaderboardObj> sorter(int sortType){
        //default
        Comparator<LeaderboardObj> comparator = Comparator.comparing(LeaderboardObj::getPoints).reversed();
        //types
        //0 = up require
        //1 = down require
        //2 = up reward
        //3 = down reward
        switch(sortType){
            case 0 -> comparator = Comparator.comparing(LeaderboardObj::getPoints);
            case 1 -> comparator = Comparator.comparing(LeaderboardObj::getPoints).reversed();
            case 2 -> comparator = Comparator.comparing(LeaderboardObj::getFinishCount);
            case 3 -> comparator = Comparator.comparing(LeaderboardObj::getFinishCount).reversed();
        }

        //the sorted list
        return lb_List.stream().sorted(comparator).collect(Collectors.toList());
    }

}
