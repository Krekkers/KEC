package krekks.easycheckpoints.Events;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import static krekks.easycheckpoints.EasyCheckpoints.config;
import static krekks.easycheckpoints.PlayerData.PlayerDataHandler.*;

public class PlayerMove implements Listener {
    Material CheckMat = Material.matchMaterial(config.getString("checkpointblocks"));
    @EventHandler
    void MoveCheck(PlayerMoveEvent e){
        Block b = e.getPlayer().getLocation().getBlock();
        if(b.getType() == CheckMat){
            SetCheckpointOf(e.getPlayer(), b.getLocation());
        }
    }

}
