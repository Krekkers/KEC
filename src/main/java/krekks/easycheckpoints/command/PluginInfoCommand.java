package krekks.easycheckpoints.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static krekks.easycheckpoints.EasyCheckpoints.*;

public class PluginInfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //sending static values in a message to the sender.
        sender.sendMessage(ChatColor.YELLOW + "Plugin Status");
        sender.sendMessage(ChatColor.YELLOW + "> Seconds : " + sec);
        sender.sendMessage(ChatColor.YELLOW + "> Toggle state : " + ChatColor.RED + Toggle);
        sender.sendMessage(ChatColor.YELLOW + "> JoinLoggin state : " + ChatColor.RED + joinLogging);
        sender.sendMessage(ChatColor.YELLOW + "> Plugin name : " + plugin.getName());

        return true;
    }
}
