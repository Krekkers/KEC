package krekks.easyparkour.command.admin;

import krekks.easyparkour.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ReloadConfigCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Bukkit.broadcast("Reloading Config", "krekks.perms");
        Config.configLoader();
        return true;
    }
}
