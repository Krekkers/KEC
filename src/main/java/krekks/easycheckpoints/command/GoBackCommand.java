package krekks.easycheckpoints.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easycheckpoints.misc.GoBack.goToCheckPoint;

public class GoBackCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        goToCheckPoint(p);
        return true;
    }
}
