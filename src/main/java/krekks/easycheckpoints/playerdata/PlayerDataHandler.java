package krekks.easycheckpoints.playerdata;

import krekks.easycheckpoints.Config;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerDataHandler {

    public static final ArrayList<PlayerData> data = new ArrayList<>();


    public static final HashMap<Player, PlayerData> playerList = new HashMap<>();
    public static final ArrayList<Player> finishedList = new ArrayList<>();

    public static PlayerData AddToList(Player p, Location l){
        PlayerData _data = new PlayerData(p,l);
        data.add(_data);
        playerList.put(p,_data);
        return _data;
    }

    /**
     * Removes a player from the list
     * @param p player to be removed
     */
    public static void removeFromList(Player p){
        playerList.remove(p);
        data.removeIf(player -> player.getPlayer().equals(p));
    }

    /**
     * Gets a player from the list using player name
     * @param p player name in the form of a string
     * @return the PlayerData from the player
     */
    public static PlayerData getFromList(String p){
        for(PlayerData _data : data){
            if(_data.getPlayer().getName().equals(p)){
                return _data;
            }
        }
        playerList.get(Bukkit.getPlayer(p));
        return playerList.get(Bukkit.getPlayer(p));
    }

    public static boolean isInList(String p){
        for(PlayerData _data : data){
            if(_data.getPlayer().getName().equals(p)){
                return true;
            }
        }
        return false;
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
            if(player.getPlayer() == p) return player.getCheckpointLocation();
        }
        return null;
    }

    /**
     * Sets the checkpoint of a player
     * @param p player
     * @param loc checkpoint location
     * @param b
     */
    public static void setCheckpointOf(Player p, Location loc, Block b){
        //loops trough the list to find the player and sets its checkpoint (which it will do once!)
        for(PlayerData player : data){
            if(player.getPlayer().equals(p)){
                if(!loc.equals(player.getCheckpointLocation())){
                    player.setCheckpointLocation(loc);
                    player.setCheckpointBlock(b);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&', Config.checkpointText));
                    p.playSound(loc, Config.checkpointSound, 1 , 1);
                }
            }
        }
    }





}
