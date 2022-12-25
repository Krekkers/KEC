package krekks.easycheckpoints.system.menusystem.menu;

import krekks.easycheckpoints.system.levelsystem.LevelData;
import krekks.easycheckpoints.system.menusystem.Menu;
import krekks.easycheckpoints.system.menusystem.MenuUtility;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import static krekks.easycheckpoints.misc.CustomItem.createCustomItem;
import static krekks.easycheckpoints.system.levelsystem.LevelHandler.*;

public class LevelSelectionMenu extends Menu {
    public LevelSelectionMenu(MenuUtility utility) {
        super(utility);
    }

    @Override
    public String getMenuName() {
        return "Levels";
    }

    @Override
    public int getSlots() {
        return 27;
    }

    @Override
    public void setMenuItems() {
        for(int i = 0; i < levelList.size(); i++){
            LevelData ld = levelList.get(i);
            ItemStack item = createCustomItem(Material.GREEN_WOOL,1,
                    "Level : " + ld.getLevelName()
                    , "ID : " +  ld.getLevelID()
                    , "Difficulty : " + ld.getDifficulty()
                    , "Made by : " + "Username");
            inventory.addItem(item);
        }
        //fill remaining space
        fillInventoryWith(createCustomItem(Material.BLACK_STAINED_GLASS_PANE,1, "",""));
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        if(e.getCurrentItem().getItemMeta().getDisplayName().contains("Level")){
            int id = getDigitFromString(e.getCurrentItem().getItemMeta().getLore().get(0));
            e.getWhoClicked().sendMessage("id = " + id);
            playerSetParkourLevel((Player) e.getWhoClicked(),id);
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
