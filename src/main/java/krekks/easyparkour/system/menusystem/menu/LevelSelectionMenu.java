package krekks.easyparkour.system.menusystem.menu;

import krekks.easyparkour.playerdata.PlayerData;
import krekks.easyparkour.system.levelsystem.LevelData;
import krekks.easyparkour.system.menusystem.Menu;
import krekks.easyparkour.system.menusystem.MenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static krekks.easyparkour.Config.MENUCLICKNOISE;
import static krekks.easyparkour.misc.item.CustomItem.createCustomItem;
import static krekks.easyparkour.system.levelsystem.LevelHandler.levelList;
import static krekks.easyparkour.system.levelsystem.LevelHandler.playerSetParkourLevel;

public class LevelSelectionMenu extends Menu {

    int page = 0;
    int listLimit = 16;
    int sortmode = 0;
    public PlayerData pd;
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
        if(levelCount == 0){
            ItemStack item = createCustomItem(Material.matchMaterial("Barrier"),1 , ChatColor.RED + "No levels available");
            inventory.addItem(item);
        }
        for(int i = listLimit * page; i < pagesize; i++){
            LevelData ld = sorter(sortmode).get(i);
            ItemStack item = null;
            //locked
            if(!isLockedLevel(pd.getPoints(), ld) || pd.getPlayer().hasPermission("krekks.admin")) {
                //unlocked
                item = createCustomItem(ld.getIcon(), 1,
                        "&a&lLevel : &c" + ld.getLevelName()
                        , "&aID : &c" + ld.getLevelID()
                        , "&aDifficulty : &c" + ld.getDifficulty() + " / 10"
                        , "&aPoints : &c" + ld.getPoints()
                        , "&aReward : &c" + ld.getReward()
                        , "&aMade by : &c" + ld.getCreator());
            }
            else{
                item = createCustomItem(Material.BARRIER,1,
                        "&a&lLevel : &c" + ld.getLevelName()
                        , "&aID : &c" +  ld.getLevelID()
                        , "&aDifficulty : &c" + ld.getDifficulty() + " / 10"
                        , "&aPoints : &c" + ld.getPoints()
                        , "&aReward : &c" + ld.getReward()
                        , "&aMade by : &c" + ld.getCreator());
            }
            inventory.addItem(item);
        }
        //control
        inventory.setItem(21, createCustomItem(Material.RED_WOOL,1, "&cPrevious"));
        inventory.setItem(22, createCustomItem(Material.GRAY_WOOL,1, "&2Page : " + (page + 1)));
        inventory.setItem(23, createCustomItem(Material.GREEN_WOOL,1, "&aNext"));

        //info
        inventory.setItem(19, createCustomItem(Material.EMERALD,1, "&c&l" + pd.getLevelData().getLevelName()));
        inventory.setItem(25, createCustomItem(Material.GOLD_INGOT,1, "&a&lPoints : &c" + pd.getPoints()));
        inventory.setItem(26,  createCustomItem(Material.COMPARATOR,1, "&aSortmode : " + sortmodeToString()));
        //fill remaining space
        fillInventoryWith(createCustomItem(Material.BLACK_STAINED_GLASS_PANE,1, "&8#",""));
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        //locked level
        if(item.getType() == Material.BARRIER){
            p.sendMessage(ChatColor.RED + "This level is locked.");
            p.playSound(p.getLocation(), MENUCLICKNOISE,3,1);
            return;
        }
        //handle level
        if(item.getItemMeta().getDisplayName().contains("Level")){
            int id = getDigitFromString(e.getCurrentItem().getItemMeta().getLore().get(0));
            playerSetParkourLevel((Player) e.getWhoClicked(),id);
            p.playSound(p.getLocation(), MENUCLICKNOISE,3,1);
        }
        //pagination
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
        if(item.getType() == Material.COMPARATOR){

            //logic for sort modes
            sortmode += 1;
            if(sortmode > 5)
                sortmode = 0;
            setMenuItems();
            p.playSound(p.getLocation(), MENUCLICKNOISE,3,1);
            return;
        }

    }

    boolean isLockedLevel(int playerPoints, LevelData ld){
        if(playerPoints < ld.getPoints())
            return true;
        else
            return false;
    }

    public List<LevelData> sorter(int sortType){
        //default
        Comparator<LevelData> comparator = Comparator.comparing(LevelData::getPoints).reversed();
        //types
        //0 = up require
        //1 = down require
        //2 = up reward
        //3 = down reward
        switch(sortType){
            case 0 -> comparator = Comparator.comparing(LevelData::getDifficulty);
            case 1 -> comparator = Comparator.comparing(LevelData::getDifficulty).reversed();
            case 2 -> comparator = Comparator.comparing(LevelData::getPoints);
            case 3 -> comparator = Comparator.comparing(LevelData::getPoints).reversed();
            case 4 -> comparator = Comparator.comparing(LevelData::getReward);
            case 5 -> comparator = Comparator.comparing(LevelData::getReward).reversed();
        }

        //the sorted list
        return levelList.stream().sorted(comparator).collect(Collectors.toList());
    }

    public String sortmodeToString(){
        switch (sortmode){
            case 0 -> { return "&cDifficulty Low > High"; }
            case 1 -> { return "&cDifficulty High > Low"; }
            case 2 -> { return "&cRequired Points Low > High"; }
            case 3 -> { return "&cRequired Points High > Low"; }
            case 4 -> { return "&cReward Low > High"; }
            case 5 -> { return "&cReward High > Low"; }
        }
        return "Something went wrong";
    }


    //keeps digits
    int getDigitFromString(String input){
        char[] charArray = input.toCharArray();            //The array
        int sl = input.toCharArray().length;               //The amount of characters in the chararray
        StringBuilder result = new StringBuilder();         //String magic
        //loops over all characters
        for (int i = 0; i < sl; i++){ if (Character.isDigit(charArray[i])){ result.append(charArray[i]); } }
        return Integer.parseInt(result.toString());
    }

}
