package krekks.easycheckpoints.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static krekks.easycheckpoints.Config.*;

public class ToggleJoinLogging implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        joinLogging = !joinLogging;
        sender.sendMessage("join logging = " + joinLogging);
        return true;
    }
}
