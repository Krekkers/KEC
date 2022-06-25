package krekks.easycheckpoints.PlayerData;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import java.util.ArrayList;

import static krekks.easycheckpoints.EasyCheckpoints.config;

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

    public static PlayerData GetFromList(String p){
        for(PlayerData _data : data){
            if(_data.getP().getName().equals(p)){
                return _data;
            }
        }
        return null;
    }

    public static Location GetCheckpointOf(Player p){
        for(PlayerData player : data){
            if(player.getP() == p){
                return player.getCheckpointLocation();
            }
        }
        return null;
    }

    public static void SetCheckpointOf(Player p, Location loc){
        //loops trough the list to find the player and sets its checkpoint (which it will do once!)
        for(PlayerData player : data){
            if(player.getP().equals(p)){
                if(!loc.equals(player.getCheckpointLocation())){
                    player.setCheckpointLocation(loc);
                    p.sendMessage(ChatColor.translateAlternateColorCodes('&',config.getString("checkpointmessages")));
                    p.playSound(p.getLocation(), Sound.PISTON_EXTEND,1,2);
                }

            }
        }
    }



}
