package krekks.easyparkour;


import krekks.easyparkour.command.GoBackCommand;
import krekks.easyparkour.command.TemplateCommand;
import krekks.easyparkour.command.admin.*;
import krekks.easyparkour.command.level.LevelSelectorCommand;
import krekks.easyparkour.command.level.admin.AddNewLevelCommand;
import krekks.easyparkour.command.level.admin.DeleteLevelCommand;
import krekks.easyparkour.command.level.admin.SetLevelDataCommand;
import krekks.easyparkour.event.*;
import krekks.easyparkour.playerdata.PlayerDataHandler;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.sql.SQLException;

import static krekks.easyparkour.Config.configLoader;
import static krekks.easyparkour.Config.spawnWorld;
import static krekks.easyparkour.system.levelsystem.LevelHandler.saveLevels;
import static krekks.easyparkour.system.storage.PlayerSaveUtil.initDB;

public final class KEP extends JavaPlugin {

    PluginManager pluginManager = Bukkit.getPluginManager();
    public static FileConfiguration config = null;
    public static World gameWorld;             //unused
    public static Plugin PLUGIN;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Launching!");
        //setting up
        PLUGIN = this;
        PLUGIN.saveDefaultConfig();
        config = PLUGIN.getConfig();
        configLoader();
        gameWorld = Bukkit.getWorld(spawnWorld);
        eventsRegister();
        commandSetup();
        //licenseCheck();
        // if the plugin gets reloaded I want it to not break
        getLogger().info("If there are any online players they now have no checkpoint location!");
        for(Player p : Bukkit.getOnlinePlayers()){
            PlayerDataHandler.AddToList(p,null);
        }
        try {
            initDB();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //bstats
        final int pluginId = 15743;
        Metrics metrics = new Metrics(this,pluginId);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        saveLevels();
        Bukkit.getLogger().info("Saved levels");
    }

    public void eventsRegister(){
        getLogger().info("Setting up Events...");
        pluginManager.registerEvents(new DeathEvent(), this);
        pluginManager.registerEvents(new PlayerMove(), this);
        pluginManager.registerEvents(new InventoryEvents(), this);
        pluginManager.registerEvents(new Leave(), this);
        pluginManager.registerEvents(new Join(), this);
        pluginManager.registerEvents(new Interact(),this);
        getLogger().info("Events have been setup");
    }

    public void commandSetup(){
        getLogger().info("Setting up Commands...");
        getCommand("addlevel").setExecutor(new AddNewLevelCommand());
        getCommand("deletelevel").setExecutor(new DeleteLevelCommand());
        getCommand("setleveldata").setExecutor(new SetLevelDataCommand());
        getCommand("reloadconfig").setExecutor(new ReloadConfigCommand());
        getCommand("levels").setExecutor(new LevelSelectorCommand());
        getCommand("GetPlayerInList").setExecutor(new GetPlayerInListCommand());
        getCommand("Back").setExecutor(new GoBackCommand());
        getCommand("SetCheckpointOf").setExecutor(new SetCheckpointCommand());
        getCommand("setlevelof").setExecutor(new TemplateCommand());
        getCommand("setpointsof").setExecutor(new TemplateCommand());
        getCommand("ShowPluginData").setExecutor(new PluginInfoCommand());
        getCommand("PlayerStats").setExecutor(new PlayerStatsMenuCommand());
        getLogger().info("Commands are setup");
    }

}
