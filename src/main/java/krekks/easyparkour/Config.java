package krekks.easyparkour;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.time.Instant;

import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.system.levelsystem.LevelHandler.loadParkourLevels;
import static org.bukkit.Bukkit.getLogger;

public class Config {
    public static boolean Toggle;
    public static boolean checkpointOnly;
    public static int sec;
    public static Instant time;

    public static String spawnWorld;
    public static Location spawn = new Location(Bukkit.getWorld("world"),0,0,0);

    public static Sound MENUCLICKNOISE;
    //todo : load everything in config
    //materials
    public static Material checkpoint;
    public static Material jump;
    public static Material boost;
    public static Material nextLevel;
    //text
    public static String jumpText;
    public static String checkpointText;
    public static String boostText;
    //sounds
    public static Sound jumpSound;
    public static Sound checkpointSound;
    public static Sound boostSound;
    public static Sound nextLevelSound;
    //values
    public static double jumpVal;
    public static double boostVal;

    public static void configLoader(){
        time = Instant.now();
        //world = Bukkit.getWorld(config.getString("world"));

        getLogger().info("Setting up blocks");
        checkpoint = Material.matchMaterial(config.getString("parkoursettings.blocks.checkpointblock"));
        jump = Material.matchMaterial(config.getString("parkoursettings.blocks.jumpblock"));
        boost = Material.matchMaterial(config.getString("parkoursettings.blocks.boostblock"));
        nextLevel = Material.matchMaterial(config.getString("parkoursettings.blocks.nextlevelblock"));

        getLogger().info("Setting up Text");
        jumpText = config.getString("parkoursettings.messages.jumpmessage");
        checkpointText = config.getString("parkoursettings.messages.checkpointmessage");
        boostText = config.getString("parkoursettings.messages.boostmessage");

        getLogger().info("Setting up sounds");
        MENUCLICKNOISE = Sound.valueOf(config.getString("parkoursettings.sounds.menuclicksound"));
        jumpSound = Sound.valueOf(config.getString("parkoursettings.sounds.jumpsound"));
        checkpointSound = Sound.valueOf(config.getString("parkoursettings.sounds.checkpointsound"));
        boostSound = Sound.valueOf(config.getString("parkoursettings.sounds.boostsound"));
        nextLevelSound = Sound.valueOf(config.getString("parkoursettings.sounds.nextlevelsound"));

        getLogger().info("Loading Game settings");
        jumpVal = config.getDouble("parkoursettings.gamesettings.jumpvalue");
        boostVal = config.getDouble("parkoursettings.gamesettings.boostvalue");
        spawnWorld = config.getString("parkoursettings.gamesettings.spawnlocation.world");
        spawn.setWorld(Bukkit.getWorld(spawnWorld));
        spawn.setX(config.getDouble("parkoursettings.gamesettings.spawnlocation.x"));
        spawn.setY(config.getDouble("parkoursettings.gamesettings.spawnlocation.y"));
        spawn.setZ(config.getDouble("parkoursettings.gamesettings.spawnlocation.z"));
        loadParkourLevels();
        checkpointOnly = config.getBoolean("parkoursettings.gamesettings.checkpointonly");
    }





}
