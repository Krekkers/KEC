package krekks.easyparkour.command.admin;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static krekks.easyparkour.KEP.*;
import static krekks.easyparkour.Config.*;
public class PluginInfoCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        //sending static values in a message to the sender.
        sender.sendMessage(ChatColor.GREEN + "Plugin Status");
        sender.sendMessage(ChatColor.GREEN + "> Toggle state : " + ChatColor.RED + Toggle);
        sender.sendMessage(ChatColor.GREEN + "> Plugin name : " + PLUGIN.getName());
        return true;
    }
}
