package krekks.easyparkour.menu;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class MenuManager {
    public static final HashMap<Player, MenuUtility> menuUtilityMap = new HashMap<>();
    public static MenuUtility getMenuUtility(Player p){
        MenuUtility utility;
        if(menuUtilityMap.containsKey(p)){
            return menuUtilityMap.get(p);
        }else{
            utility = new MenuUtility(p);
            menuUtilityMap.put(p,utility);

            return utility;
        }
    }

}
