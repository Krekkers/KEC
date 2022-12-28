package krekks.easyparkour.misc;

import krekks.easyparkour.playerdata.PlayerData;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import static krekks.easyparkour.playerdata.PlayerDataHandler.getCheckpointOf;
import static krekks.easyparkour.playerdata.PlayerDataHandler.getFromList;

public class GoBack extends JavaPlugin {
    //goes back to the checkpoint
    /**
     * This make the player go back to the checkpoint
     * @param p player to get back to his checkpoint
     */
    public static void goToCheckPoint(Player p){
        if(getCheckpointOf(p) == null) return;
        Location checkpoint = getCheckpointOf(p);
        Location newLoc = new Location(p.getWorld(),checkpoint.getX(),checkpoint.getY(),checkpoint.getZ());
        if(newLoc == null)return;
        //setting location values to be teleported to
        newLoc.add(0.5f,1f,0.5f);
        newLoc.setPitch(p.getLocation().getPitch());
        newLoc.setYaw(p.getLocation().getYaw());
        //teleports
        p.teleport(newLoc);
        PlayerData d = getFromList(p);

    }

}
