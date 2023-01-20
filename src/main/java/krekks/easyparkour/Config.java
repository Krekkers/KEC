package krekks.easyparkour;

import krekks.easyparkour.playerdata.KrekksPermission;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.time.Instant;
import java.util.ArrayList;

import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.system.levelsystem.LevelHandler.loadParkourLevels;
import static org.bukkit.Bukkit.getLogger;

public class Config {
    public static boolean Toggle;
    public static boolean checkpointOnly;
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
    //text
    public static String checkpointText;
    //sounds
    public static Sound checkpointSound;
    public static Sound nextLevelSound;

    public static ArrayList<KrekksPermission> multipliers = new ArrayList<>();

    public static void configLoader(){
        time = Instant.now();

        //------------------------------------------------------------------------------------------------
        //DO NOT TOUCH. Doing so will be seen as an act of piracy. Removing this is still stealing  //  |
        LICENSEKEY = config.getString("licensekey");                                          //  |
        //------------------------------------------------------------------------------------------------

        getLogger().info("Setting up blocks");
        checkpoint = Material.matchMaterial(config.getString("parkoursettings.blocks.checkpointblock"));
        nextLevel = Material.matchMaterial(config.getString("parkoursettings.blocks.nextlevelblock"));

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
        loadParkourLevels();

    }





}
