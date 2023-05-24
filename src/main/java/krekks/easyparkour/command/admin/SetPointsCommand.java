package krekks.easyparkour.command.admin;

import krekks.easyparkour.playerdata.PlayerData;
import krekks.easyparkour.playerdata.PlayerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import static krekks.easyparkour.KEP.config;

public class SetPointsCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = Bukkit.getPlayer(args[0]);
        PlayerData pd = PlayerDataHandler.getPlayerDataFromList(p);
        pd.setPoints(Integer.parseInt(args[1]));
        sender.sendMessage(config.primary + "Success! " + pd.getPlayer().getDisplayName() + " Points : " + pd.getPoints());
        return true;
    }
}
