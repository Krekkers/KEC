package krekks.easyparkour.system.menusystem.menu;

import krekks.easyparkour.system.levelsystem.LevelData;
import krekks.easyparkour.system.menusystem.Menu;
import krekks.easyparkour.system.menusystem.MenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static krekks.easyparkour.Config.MENUCLICKNOISE;
import static krekks.easyparkour.misc.CustomItem.createCustomItem;
import static krekks.easyparkour.system.levelsystem.LevelHandler.levelList;
import static krekks.easyparkour.system.levelsystem.LevelHandler.playerSetParkourLevel;

public class LevelSelectionMenu extends Menu {

    int page = 0;
    int listLimit = 16;

    public LevelSelectionMenu(MenuUtility utility) {
        super(utility);
    }

    @Override
    public String getMenuName() {
        return ChatColor.GREEN + "Levels";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void setMenuItems() {
        inventory.clear();
        int levelCount = levelList.size();
        int pagesize = 18;
        if(levelCount < pagesize)
            pagesize = levelCount;
        for(int i = listLimit * page; i < pagesize; i++){
            LevelData ld = levelList.get(i);
            ItemStack item = createCustomItem(ld.getIcon(),1,
                    "&a&lLevel : &c" + ld.getLevelName()
                    , "&aID : &c" +  ld.getLevelID()
                    , "&aDifficulty : &c" + ld.getDifficulty()
                    , "&aPoints : &c" + ld.getPoints()
                    , "&aMade by : &c" + ld.getCreator());
            inventory.addItem(item);
        }
        //control
        inventory.setItem(23, createCustomItem(Material.GREEN_WOOL,1, "&aNext"));
        inventory.setItem(22, createCustomItem(Material.GRAY_WOOL,1, "&2Page : " + (page + 1)));
        inventory.setItem(21, createCustomItem(Material.RED_WOOL,1, "&cPrevious"));
        //fill remaining space
        fillInventoryWith(createCustomItem(Material.BLACK_STAINED_GLASS_PANE,1, "&8#",""));
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Level")){
            int id = getDigitFromString(e.getCurrentItem().getItemMeta().getLore().get(0));
            e.getWhoClicked().sendMessage("id = " + id);
            playerSetParkourLevel((Player) e.getWhoClicked(),id);
            p.playSound(p.getLocation(), MENUCLICKNOISE,3,1);
        }
        if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Next")){
            page += 1;
            setMenuItems();
            p.playSound(p.getLocation(), MENUCLICKNOISE,3,1);
        }
        if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Previous") && page >= 1){
            page -= 1;
            setMenuItems();
            p.playSound(p.getLocation(), MENUCLICKNOISE,3,1);
        }

    }
    //keeps digits
    int getDigitFromString(String input){
        char[] charArray = input.toCharArray();            //The array
        int sl = input.toCharArray().length;               //The amount of characters in the chararray
        StringBuilder result = new StringBuilder();         //String magic
        for (int i = 0; i < sl; i++){
            if (Character.isDigit(charArray[i])){           //magic
                result.append(charArray[i]);
            }
        }
        return Integer.parseInt(result.toString());
    }

}
