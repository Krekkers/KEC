package krekks.easyparkour.menu.menu;

import krekks.easyparkour.playerdata.PlayerData;
import krekks.easyparkour.manager.levelmanager.LevelData;
import krekks.easyparkour.menu.Menu;
import krekks.easyparkour.menu.MenuUtility;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.misc.item.CustomItem.createCustomItem;
import static krekks.easyparkour.manager.levelmanager.LevelHandler.levelList;
import static krekks.easyparkour.manager.levelmanager.LevelHandler.playerSetParkourLevel;

public class LevelSelectionMenu extends Menu {

    int page = 0;
    int listLimit = 18;
    int sortmode = 0;
    public PlayerData pd;
    public LevelSelectionMenu(MenuUtility utility) {
        super(utility);
    }

    @Override
    public String getMenuName() {
        return config.primary + "Levels";
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
        int curLimit = listLimit * page;
        if(levelCount < curLimit + pagesize)
            pagesize = pagesize - ((curLimit + pagesize) - levelCount);
        if(levelCount == 0){
            ItemStack item = createCustomItem(Material.matchMaterial("Barrier"),1 , config.error + "No levels available");
            inventory.addItem(item);
        }

        for(int i = curLimit; i < curLimit + pagesize; i++){
            LevelData ld = sorter(sortmode).get(i);
            ItemStack item = null;
            //locked
            if(!isLockedLevel(pd.getPoints(), ld) || pd.getPlayer().hasPermission("krekks.admin")) {
                //unlocked
                item = createCustomItem(ld.getIcon(), 1,
                        config.primary + "&lLevel : " + config.secondary + ld.getLevelName()
                        , config.primary + "ID : " + config.secondary + ld.getLevelID()
                        , config.primary + "Difficulty : " + config.secondary + ld.getDifficulty() + " / 10"
                        , config.primary + "Points : " + config.secondary +  ld.getPoints()
                        , config.primary + "Reward : " + config.secondary + ld.getReward()
                        , config.primary + "Made by : " + config.secondary + ld.getCreator());
            }
            else{
                item = createCustomItem(Material.BARRIER,1,
                        config.primary + "&lLevel : " + config.secondary + ld.getLevelName()
                        , config.primary + "ID : " + config.secondary + ld.getLevelID()
                        , config.primary + "Difficulty : " + config.secondary + ld.getDifficulty() + " / 10"
                        , config.primary + "Points : " + config.secondary +  ld.getPoints()
                        , config.primary + "Reward : " + config.secondary + ld.getReward()
                        , config.primary + "Made by : " + config.secondary + ld.getCreator());
            }
            inventory.addItem(item);
        }
        //control
        inventory.setItem(21, createCustomItem(Material.RED_WOOL,1, "&cPrevious"));
        inventory.setItem(22, createCustomItem(Material.GRAY_WOOL,1, "&2Page : " + (page + 1)));
        inventory.setItem(23, createCustomItem(Material.GREEN_WOOL,1, "&aNext"));

        //info
        inventory.setItem(19, createCustomItem(Material.EMERALD,1, config.secondary +"&l" + pd.getLevelData().getLevelName()));
        inventory.setItem(25, createCustomItem(Material.GOLD_INGOT,1, config.primary + "&lPoints : " + config.secondary + pd.getPoints()));
        inventory.setItem(26,  createCustomItem(Material.COMPARATOR,1, config.primary + "Sortmode : " + config.secondary + sortmodeToString()));
        //fill remaining space
        fillInventoryWith(createCustomItem(Material.BLACK_STAINED_GLASS_PANE,1, "&8#",""));
    }

    @Override
    public void handleMenu(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack item = e.getCurrentItem();
        //locked level
        if(item.getType() == Material.BARRIER){
            p.sendMessage(config.error + "This level is locked.");
            p.playSound(p.getLocation(), config.MENUCLICKNOISE,3,1);
            return;
        }
        //handle level
        if(item.getItemMeta().getDisplayName().contains("Level")){
            int id = getDigitFromString(ChatColor.stripColor(item.getItemMeta().getLore().get(0)));
            playerSetParkourLevel((Player) e.getWhoClicked(),id);
            p.playSound(p.getLocation(), config.MENUCLICKNOISE,3,1);
        }
        //pagination
        if(item.getItemMeta().getDisplayName().contains("Next")){
            page += 1;
            setMenuItems();
            p.playSound(p.getLocation(), config.MENUCLICKNOISE,3,1);
        }
        if(item.getItemMeta().getDisplayName().contains("Previous") && page >= 1){
            page -= 1;
            setMenuItems();
            p.playSound(p.getLocation(), config.MENUCLICKNOISE,3,1);
        }
        if(item.getType() == Material.COMPARATOR){
            //logic for sort modes
            sortmode += 1;
            if(sortmode > 5)
                sortmode = 0;
            setMenuItems();
            p.playSound(p.getLocation(), config.MENUCLICKNOISE,3,1);
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
            case 0 -> { return config.secondary + "Difficulty Low > High"; }
            case 1 -> { return config.secondary + "Difficulty High > Low"; }
            case 2 -> { return config.secondary + "Points Low > High"; }
            case 3 -> { return config.secondary + "Points High > Low"; }
            case 4 -> { return config.secondary + "Reward Low > High"; }
            case 5 -> { return config.secondary + "Reward High > Low"; }
        }
        return config.error + "Something went wrong";
    }


    //keeps digits
    int getDigitFromString(String input){
        String in = ChatColor.stripColor(input);
        char[] charArray = in.toCharArray();            //The array
        int sl = in.toCharArray().length;               //The amount of characters in the chararray
        StringBuilder result = new StringBuilder();         //String magic
        //loops over all characters
        for (int i = 0; i < sl; i++){ if (Character.isDigit(charArray[i])){ result.append(charArray[i]); } }
        return Integer.parseInt(result.toString());
    }

}
