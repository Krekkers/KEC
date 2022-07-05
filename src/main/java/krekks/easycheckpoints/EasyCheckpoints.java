package krekks.easycheckpoints;

import krekks.easycheckpoints.Commands.*;
import krekks.easycheckpoints.Events.*;
import krekks.easycheckpoints.PlayerData.PlayerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static org.bukkit.Bukkit.broadcastMessage;
import static krekks.easycheckpoints.PlayerData.PlayerDataHandler.*;
public final class EasyCheckpoints extends JavaPlugin {

    PluginManager pluginManager = Bukkit.getPluginManager();
    public static FileConfiguration config = null;
    public static World world;
    public static boolean Toggle = false;
    public static boolean joinLogging;
    public static Plugin plugin;

    public static double finishX = 0;       //Position X of the place where players will be teleported to
    public static double finishY = 0;       //Position Y of the place where players will be teleported to
    public static double finishZ = 0;       //Position Z of the place where players will be teleported to


    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Launching Better Checkpoints!");
        //setting up
        plugin = this;
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
        finishX = config.getDoubleList("finishlocation").get(0);
        finishY = config.getDoubleList("finishlocation").get(1);
        finishZ = config.getDoubleList("finishlocation").get(2);
        getLogger().info("Config has been setup");
        pluginManager.registerEvents(new Death(), this);
        pluginManager.registerEvents(new PlayerMove(), this);
        pluginManager.registerEvents(new InventoryEvents(), this);
        pluginManager.registerEvents(new Leave(), this);
        pluginManager.registerEvents(new Join(), this);
        pluginManager.registerEvents(new Interact(),this);
        getLogger().info("Events have been setup");;
        getCommand("KecGetList").setExecutor(new GetListCommand());
        getCommand("KecGetPlayerInList").setExecutor(new GetPlayerInListCommand());
        getCommand("Back").setExecutor(new GoBackCommand());
        getCommand("KecToggle").setExecutor(new ToggleCommand());
        getCommand("KecGetTop").setExecutor(new GetTopCommand());
        getCommand("kecbroadcasttop").setExecutor(new DisplayTopCommand());
        getCommand("togglejoinlogging").setExecutor(new ToggleJoinLogging());
        getLogger().info("Commands are setup");
        // if the plugin gets reloaded I want it to not break
        getLogger().info("If there are any online players they now have no checkpoint location!");
        broadcastMessage(ChatColor.GREEN + "KEC has launched!");
        for(Player p : Bukkit.getOnlinePlayers()){
            PlayerDataHandler.AddToList(p,null);
        }
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        data.clear();
        finishedList.clear();
    }
}
