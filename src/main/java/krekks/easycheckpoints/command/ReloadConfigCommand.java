package krekks.easycheckpoints.command;

import krekks.easycheckpoints.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfigCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Config.configLoader();
        Bukkit.broadcast("Reloading Config", "krekks.perms");
        return true;
    }
}
