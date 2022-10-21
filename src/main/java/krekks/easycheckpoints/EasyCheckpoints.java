package krekks.easycheckpoints;


import krekks.easycheckpoints.command.*;
import krekks.easycheckpoints.event.*;
import krekks.easycheckpoints.playerdata.PlayerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.time.Instant;

import static krekks.easycheckpoints.playerdata.PlayerDataHandler.data;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.finishedList;
import static org.bukkit.Bukkit.broadcastMessage;

public final class EasyCheckpoints extends JavaPlugin {

    PluginManager pluginManager = Bukkit.getPluginManager();
    public static FileConfiguration config = null;
    public static World world;              //unused
    public static boolean Toggle;
    public static boolean joinLogging;
    public static Plugin plugin;
    public static int sec;
    public static Instant time;

    public static double finishX = 0;       //Position X of the place where players will be teleported to
    public static double finishY = 0;       //Position Y of the place where players will be teleported to
    public static double finishZ = 0;       //Position Z of the place where players will be teleported to

    public static double spawnX = 0;
    public static double spawnY = 0;
    public static double spawnZ = 0;

    public static Sound MENUCLICKNOISE;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("LAUNCHING KEC!");
        //setting up
        plugin = this;
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
        loadConfig();
        loadBStats();
        time = Instant.now();
        //world = Bukkit.getWorld(config.getString("world"));
        MENUCLICKNOISE = Sound.valueOf(config.getString("menuclicksound"));
        joinLogging = config.getBoolean("joinloggingonlaunch");
        Toggle = config.getBoolean("autostart");
        getLogger().info("Config has been setup");
        pluginManager.registerEvents(new DeathEvent(), this);
        pluginManager.registerEvents(new PlayerMove(), this);
        pluginManager.registerEvents(new InventoryEvents(), this);
        pluginManager.registerEvents(new Leave(), this);
        pluginManager.registerEvents(new Join(), this);
        pluginManager.registerEvents(new Interact(),this);
        getLogger().info("Events have been setup");;
        getCommand("KecGetList").setExecutor(new GetListCommand());
        getCommand("KecGetPlayerInList").setExecutor(new GetPlayerInListCommand());
        getCommand("Back").setExecutor(new GoBackCommand());
        getCommand("KecStart").setExecutor(new ToggleCommand());
        getCommand("KecGetTop").setExecutor(new GetTopCommand());
        getCommand("kecbroadcasttop").setExecutor(new DisplayTopCommand());
        getCommand("togglejoinlogging").setExecutor(new ToggleJoinLogging());
        getCommand("KecSetCheckpointOf").setExecutor(new SetCheckpointCommand());
        getCommand("KecReset").setExecutor(new RestartCommand());
        getCommand("KecShowPluginData").setExecutor(new PluginInfoCommand());
        getCommand("KecChangeFinishLocation").setExecutor(new ChangeFinishLocationCommand());
        getCommand("KecChangeSpawnLocation").setExecutor(new ChangeSpawnLocationCommand());
        getCommand("KecGameManager").setExecutor(new GameManagerCommand());
        getCommand("KecPlayerStats").setExecutor(new PlayerStatsMenuCommand());
        getLogger().info("Commands are setup");
        // if the plugin gets reloaded I want it to not break
        getLogger().info("If there are any online players they now have no checkpoint location!");
        broadcastMessage(ChatColor.translateAlternateColorCodes('&' , "&eK&cE&eC &6Has Loaded"));
        for(Player p : Bukkit.getOnlinePlayers()){
            PlayerDataHandler.AddToList(p,null);
        }
        //bstats
        final int pluginId = 15743;
        Metrics metrics = new Metrics(this,pluginId);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        data.clear();
        finishedList.clear();
    }

    /**
     * Loads the basic config.
     */
    public void loadConfig(){
        finishX = config.getDoubleList("finishlocation").get(0);
        finishY = config.getDoubleList("finishlocation").get(1);
        finishZ = config.getDoubleList("finishlocation").get(2);
        spawnX = config.getDoubleList("spawnlocation").get(0);
        spawnY = config.getDoubleList("spawnlocation").get(1);
        spawnZ = config.getDoubleList("spawnlocation").get(2);
        joinLogging = config.getBoolean("joinloggingonlaunch");
        Toggle = config.getBoolean("autostart");
        broadcastMessage(ChatColor.translateAlternateColorCodes('&' , "&eK&cE&eC &6Has Been Reloaded!"));
    }

    public void loadBStats(){


    }

}
