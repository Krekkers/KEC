package krekks.easyparkour.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easyparkour.playerdata.PlayerDataHandler.getPlayerDataFromList;

public class GoBackCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        getPlayerDataFromList(p).goToCheckPoint();
        return true;
    }
}
