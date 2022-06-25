package krekks.easycheckpoints.Commands;

import com.sun.nio.sctp.SendFailedNotification;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static krekks.easycheckpoints.EasyCheckpoints.Toggle;

public class ToggleCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Toggle = !Toggle;
        sender.sendMessage("Checkpoint plugin = " + Toggle);
        return true;
    }
}
