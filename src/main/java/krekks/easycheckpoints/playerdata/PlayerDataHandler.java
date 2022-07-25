package krekks.easycheckpoints.playerdata;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerDataHandler {

    public static final ArrayList<PlayerData> data = new ArrayList<>();
    public static final ArrayList<Player> finishedList = new ArrayList<>();
    public static PlayerData AddToList(Player p, Location l){
        PlayerData _data = new PlayerData(p,l);
        data.add(_data);
        return _data;
    }

    /**
     * Removes a player from the list
     * @param p player to be removed
     */
    public static void removeFromList(Player p){
        data.removeIf(player -> player.getP().equals(p));
    }

    /**
     * Gets a player from the list using player name
     * @param p player name in the form of a string
     * @return the PlayerData from the player
     */
    public static PlayerData getFromList(String p){
        for(PlayerData _data : data){
            if(_data.getP().getName().equals(p)){
                return _data;
            }
        }
        return null;
    }

    /**
     * Adds the player to the finished list
     * @param p player to add
     * @return the player that got added
     */
    public static Player addToFinished(Player p){
        finishedList.add(p);
        return p;
    }

    /**
     * Gets the checkpoint of a player and returns it
     * @param p player to check the checkpoint of
     * @return Players Checkpoint
     */
    public static Location getCheckpointOf(Player p){
        for(PlayerData player : data){
            if(player.getP() == p) return player.getCheckpointLocation();
        }
        return null;
    }

    /**
     * Sets the checkpoint of a player
     * @param p player
     * @param loc checkpoint location
     */
    public static void setCheckpointOf(Player p, Location loc){
        //loops trough the list to find the player and sets its checkpoint (which it will do once!)
        for(PlayerData player : data){
            if(player.getP().equals(p)){
                if(!loc.equals(player.getCheckpointLocation())) player.setCheckpointLocation(loc);
            }
        }
    }





}
