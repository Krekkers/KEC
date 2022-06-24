package krekks.easycheckpoints.PlayerData;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class PlayerDataHandler {

    public static final ArrayList<PlayerData> data = new ArrayList<>();

    public static PlayerData AddToList(Player p, Location l){
        PlayerData _data = new PlayerData(p,l);
        data.add(_data);
        return _data;
    }
    public static void RemoveFromList(Player p){
        data.removeIf(player -> player.getP().equals(p));
    }

    public static void SetCheckpointOf(Player p, Location loc){
        //loops trough the list to find the player and sets its checkpoint (which it will do once!)
        for(PlayerData player : data){
            if(player.getP().equals(p) && player.getCheckpointLocation() != loc){
                player.setCheckpointLocation(loc);
            }
        }
    }





}
