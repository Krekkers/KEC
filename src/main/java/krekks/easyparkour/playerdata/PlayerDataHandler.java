package krekks.easyparkour.playerdata;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.HashMap;

public class PlayerDataHandler {
    public static final HashMap<Player, PlayerData> playerList = new HashMap<>();

    public static PlayerData AddToList(Player p, Location l){
        PlayerData _data = new PlayerData(p);
        playerList.put(p,_data);
        return _data;
    }

    /**
     * Removes a player from the list
     * @param p player to be removed
     */
    public static void removeFromList(Player p){
        playerList.remove(p);
    }

    /**
     * Gets a player from the list using player name
     * @param p player name in the form of a string
     * @return the PlayerData from the player
     */
    public static PlayerData getPlayerDataFromList(Player p){
        return playerList.get(p);
    }

    /**
     * Is the user provided in the list? returns result.
     * @param p player to check if its in list
     * @return true/false if player is in the list
     */
    public static boolean isInListName(String p){
        return playerList.containsKey(Bukkit.getPlayer(p));
    }
    /**
     * Is the user provided in the list? returns result.
     * @param p player to check if its in list
     * @return true/false if player is in the list
     */
    public static boolean isInListPlayer(Player p){
        return playerList.containsKey(p);
    }
    /**
     * Gets the checkpoint of a player and returns it
     * @param p player to check the checkpoint of
     * @return Players Checkpoint
     */
    public static Location getCheckpointOf(Player p){
        if(!playerList.containsKey(p))
            return null;
        return playerList.get(p).getCheckpointLocation();
    }

    /**
     * Sets the checkpoint of a player
     * @param p player
     * @param loc checkpoint location
     */
    public static void setCheckpointOf(Player p, Location loc){
        playerList.get(p).setCheckpoint(loc);
    }






}
