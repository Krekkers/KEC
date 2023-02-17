package krekks.easyparkour;

import krekks.easyparkour.playerdata.KrekksPermission;
import org.bukkit.*;

import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.system.levelsystem.LevelHandler.loadParkourLevels;
import static org.bukkit.Bukkit.getLogger;

public class Config {
    public static boolean Toggle;
    public static boolean checkpointOnly;
    public static int LB_refreshRate;
    public static int sec;
    public static Instant time;
    public static String LICENSEKEY;
    public static String PRODUCTKEY = "KEP";
    public static String spawnWorld;
    public static Location spawn = new Location(Bukkit.getWorld("world"),0,0,0);


    public static Sound MENUCLICKNOISE;
    //materials
    public static Material checkpoint;
    public static Material nextLevel;
    public static Material boost = Material.DIAMOND_BLOCK;
    //text
    public static String checkpointText;
    //sounds
    public static Sound checkpointSound;
    public static Sound nextLevelSound;
    //values
    public static int boostAmount = 10;
    //database options
    public static boolean useSQL;
    public static String dbConnectionURL;
    public static String dbUser;
    public static String dbPass;
    public static String dbTable;
    //colors todo: use these colors to display text instead of the current ones
    ChatColor primary;
    ChatColor secondary;
    ChatColor gray;
    ChatColor info;
    ChatColor error;

    public static ArrayList<KrekksPermission> multipliers = new ArrayList<>();

    public static void configLoader(){
        time = Instant.now();

        //------------------------------------------------------------------------------------------------
        //DO NOT TOUCH. Doing so will be seen as an act of piracy. Removing this is still stealing  //  |
        LICENSEKEY = config.getString("licensekey");                                           //  |
        //------------------------------------------------------------------------------------------------

        getLogger().info("Setting up blocks");
        checkpoint = Material.matchMaterial(config.getString("parkoursettings.blocks.checkpointblock"));
        nextLevel = Material.matchMaterial(config.getString("parkoursettings.blocks.nextlevelblock"));
        boost = Material.matchMaterial(config.getString("parkoursettings.blocks.boostblock"));
        getLogger().info("Setting up Text");
        checkpointText = config.getString("parkoursettings.messages.checkpointmessage");
        getLogger().info("Setting up sounds");
        MENUCLICKNOISE = Sound.valueOf(config.getString("parkoursettings.sounds.menuclicksound"));
        checkpointSound = Sound.valueOf(config.getString("parkoursettings.sounds.checkpointsound"));
        nextLevelSound = Sound.valueOf(config.getString("parkoursettings.sounds.nextlevelsound"));

        getLogger().info("Loading Game settings");
        checkpointOnly = config.getBoolean("parkoursettings.gamesettings.checkpointonly");
        spawnWorld = config.getString("parkoursettings.gamesettings.spawnlocation.world");
        spawn.setWorld(Bukkit.getWorld(spawnWorld));
        spawn.setX(config.getDouble("parkoursettings.gamesettings.spawnlocation.x"));
        spawn.setY(config.getDouble("parkoursettings.gamesettings.spawnlocation.y"));
        spawn.setZ(config.getDouble("parkoursettings.gamesettings.spawnlocation.z"));

        getLogger().info("Loading database settings");
        if(useSQL){
            dbConnectionURL = config.getString("parkoursettings.database.link");
            dbUser          = config.getString("parkoursettings.database.username");
            dbPass          = config.getString("parkoursettings.database.password");
            dbTable         = "kr_" + config.getString("parkoursettings.database.table");
        }
        LB_refreshRate = config.getInt("parkoursettings.leaderboardrefreshrate") * 20 * 60; // makes it so refresh rate in minutes
        Bukkit.getLogger().info("REFRESHRATE : " + LB_refreshRate);


        //load multipliers
        for(int i = 0; i < config.getList("parkoursettings.multipliers").size(); i++){
            LinkedHashMap<String, Object> objectlist = (LinkedHashMap<String, Object>) config.getList("parkoursettings.multipliers").get(i);
            KrekksPermission krekksPermission = new KrekksPermission((String) objectlist.get("rank"), (Double) objectlist.get("multiplier"));
            multipliers.add(krekksPermission);
        }

        loadParkourLevels();

    }





}
