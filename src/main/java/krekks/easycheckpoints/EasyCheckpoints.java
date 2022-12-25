package krekks.easycheckpoints;


import krekks.easycheckpoints.command.*;
import krekks.easycheckpoints.command.admin.*;
import krekks.easycheckpoints.command.level.LevelSelectorCommand;
import krekks.easycheckpoints.command.level.admin.LevelDataCommand;
import krekks.easycheckpoints.event.*;
import krekks.easycheckpoints.playerdata.PlayerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import static krekks.easycheckpoints.Config.configLoader;
import static krekks.easycheckpoints.playerdata.PlayerDataHandler.finishedList;
import static org.bukkit.Bukkit.broadcastMessage;

public final class EasyCheckpoints extends JavaPlugin {

    PluginManager pluginManager = Bukkit.getPluginManager();
    public static FileConfiguration config = null;
    public static World world;              //unused
    public static Plugin plugin;
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("LAUNCHING KEC!");
        //setting up
        plugin = this;
        plugin.saveDefaultConfig();
        config = plugin.getConfig();
        configLoader();
        eventsRegister();
        commandSetup();
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
        finishedList.clear();
    }

    public void eventsRegister(){
        getLogger().info("Setting up Events...");;
        pluginManager.registerEvents(new DeathEvent(), this);
        pluginManager.registerEvents(new PlayerMove(), this);
        pluginManager.registerEvents(new InventoryEvents(), this);
        pluginManager.registerEvents(new Leave(), this);
        pluginManager.registerEvents(new Join(), this);
        pluginManager.registerEvents(new Interact(),this);
        getLogger().info("Events have been setup");;
    }

    public void commandSetup(){
        getLogger().info("Setting up Commands...");
        getCommand("reloadconfig").setExecutor(new ReloadConfigCommand());
        getCommand("showlevels").setExecutor(new LevelDataCommand());
        getCommand("levels").setExecutor(new LevelSelectorCommand());
        getCommand("GetList").setExecutor(new GetListCommand());
        getCommand("GetPlayerInList").setExecutor(new GetPlayerInListCommand());
        getCommand("Back").setExecutor(new GoBackCommand());
        getCommand("Start").setExecutor(new ToggleCommand());
        getCommand("GetTop").setExecutor(new GetTopCommand());
        getCommand("broadcasttop").setExecutor(new DisplayTopCommand());
        getCommand("togglejoinlogging").setExecutor(new ToggleJoinLogging());
        getCommand("SetCheckpointOf").setExecutor(new SetCheckpointCommand());
        getCommand("Reset").setExecutor(new RestartCommand());
        getCommand("ShowPluginData").setExecutor(new PluginInfoCommand());
        getCommand("ChangeFinishLocation").setExecutor(new ChangeFinishLocationCommand());
        getCommand("ChangeSpawnLocation").setExecutor(new ChangeSpawnLocationCommand());
        getCommand("GameManager").setExecutor(new GameManagerCommand());
        getCommand("PlayerStats").setExecutor(new PlayerStatsMenuCommand());
        getLogger().info("Commands are setup");
    }

}
