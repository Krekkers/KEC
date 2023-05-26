package krekks.easyparkour;

import krekks.easyparkour.playerdata.KrekksPermission;
import org.bukkit.*;
import org.bukkit.configuration.file.FileConfiguration;

import java.time.Instant;
import java.util.ArrayList;

import static krekks.easyparkour.KEP.PLUGIN;
import static org.bukkit.Bukkit.getLogger;

/**
 * Config of the plugin
 */
public class Config {
    public FileConfiguration fileConfig;
    public boolean checkpointOnly;
    public int LB_refreshRate;
    public Instant time;
    public String spawnWorld;
    public Location spawn = new Location(Bukkit.getWorld("world"),0,0,0);


    public Sound MENUCLICKNOISE;
    //materials
    public Material checkpoint;
    public Material nextLevel;
    public Material boost = Material.DIAMOND_BLOCK;
    public Material floatblock;
    //text
    public String checkpointText;
    //sounds
    public Sound checkpointSound;
    public Sound nextLevelSound;
    //values
    public int boostAmount = 10;
    //database options
    public boolean useSQL = false;
    public String dbConnectionURL;
    public String dbUser;
    public String dbPass;
    public String dbTable;
    //colors todo: use these colors to display text instead of the current ones
    public ChatColor primary;
    public ChatColor secondary;
    public ChatColor gray;
    public ChatColor info;
    public ChatColor error;

    public static ArrayList<KrekksPermission> multipliers = new ArrayList<>();
    public Config(FileConfiguration fileConfig){
        this.fileConfig = PLUGIN.getConfig();
        time = Instant.now();
        //------------------------------------------------------------------------------------------------
        //DO NOT TOUCH. Doing so will be seen as an act of piracy. Removing this is still stealing  //  |
        //LICENSEKEY = fileConfig.getString("licensekey");                                           //  |
        //------------------------------------------------------------------------------------------------

        getLogger().info("Setting up blocks");
        checkpoint = Material.matchMaterial(fileConfig.getString("parkoursettings.blocks.checkpointblock"));
        nextLevel = Material.matchMaterial(fileConfig.getString("parkoursettings.blocks.nextlevelblock"));
        boost = Material.matchMaterial(fileConfig.getString("parkoursettings.blocks.boostblock"));
        getLogger().info("Setting up Text");
        checkpointText = fileConfig.getString("parkoursettings.messages.checkpointmessage");
        getLogger().info("Setting up sounds");
        MENUCLICKNOISE = Sound.valueOf(fileConfig.getString("parkoursettings.sounds.menuclicksound"));
        checkpointSound = Sound.valueOf(fileConfig.getString("parkoursettings.sounds.checkpointsound"));
        nextLevelSound = Sound.valueOf(fileConfig.getString("parkoursettings.sounds.nextlevelsound"));

        getLogger().info("Loading Game settings");
        checkpointOnly = fileConfig.getBoolean("parkoursettings.gamesettings.checkpointonly");
        spawnWorld = fileConfig.getString("parkoursettings.gamesettings.spawnlocation.world");
        spawn.setWorld(Bukkit.getWorld(spawnWorld));
        spawn.setX(fileConfig.getDouble("parkoursettings.gamesettings.spawnlocation.x"));
        spawn.setY(fileConfig.getDouble("parkoursettings.gamesettings.spawnlocation.y"));
        spawn.setZ(fileConfig.getDouble("parkoursettings.gamesettings.spawnlocation.z"));
        spawn.setYaw((float) fileConfig.getDouble("parkoursettings.gamesettings.spawnlocation.rotation"));

        this.primary = ChatColor.valueOf(fileConfig.getString("parkoursettings.colors.primary"));
        this.secondary = ChatColor.valueOf(fileConfig.getString("parkoursettings.colors.secondary"));
        this.gray = ChatColor.valueOf(fileConfig.getString("parkoursettings.colors.secondary"));
        this.info = ChatColor.valueOf(fileConfig.getString("parkoursettings.colors.info"));
        this.error = ChatColor.valueOf(fileConfig.getString("parkoursettings.colors.error"));

        getLogger().info("Loading database settings");
        if(useSQL){
            dbConnectionURL = fileConfig.getString("parkoursettings.database.link");
            dbUser          = fileConfig.getString("parkoursettings.database.username");
            dbPass          = fileConfig.getString("parkoursettings.database.password");
            dbTable         = "kr_" + fileConfig.getString("parkoursettings.database.table");
        }
        LB_refreshRate = fileConfig.getInt("parkoursettings.leaderboardrefreshrate") * 20 * 60; // makes it so refresh rate in minutes
        getLogger().info("REFRESHRATE : " + LB_refreshRate);


    }


}
