package krekks.easyparkour;

import org.bukkit.Material;
import org.bukkit.Sound;

import java.time.Instant;

import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.system.levelsystem.LevelHandler.loadParkourLevels;
import static org.bukkit.Bukkit.getLogger;

public class Config {
    public static boolean Toggle;
    public static boolean joinLogging;
    public static boolean checkpointOnly;
    public static int sec;
    public static Instant time;

    public static double finishX = 0;       //Position X of the place where players will be teleported to
    public static double finishY = 0;       //Position Y of the place where players will be teleported to
    public static double finishZ = 0;       //Position Z of the place where players will be teleported to

    public static double spawnX = 0;
    public static double spawnY = 0;
    public static double spawnZ = 0;

    public static Sound MENUCLICKNOISE;
    //todo : load everything in config
    //materials
    public static Material checkpoint;
    public static Material jump;
    public static Material boost;
    public static Material elytra;
    public static Material nextLevel;
    //text
    public static String jumpText;
    public static String checkpointText;
    public static String boostText;
    public static String elytraText;
    //sounds
    public static Sound jumpSound;
    public static Sound checkpointSound;
    public static Sound boostSound;
    public static Sound elytraSound;
    public static Sound nextLevelSound;
    //values
    public static double jumpVal;
    public static double boostVal;
    public static double elytraVal;

    public static void configLoader(){
        time = Instant.now();
        //world = Bukkit.getWorld(config.getString("world"));

        getLogger().info("Setting up blocks");
        checkpoint = Material.matchMaterial(config.getString("parkoursettings.blocks.checkpointblock"));
        jump = Material.matchMaterial(config.getString("parkoursettings.blocks.jumpblock"));
        boost = Material.matchMaterial(config.getString("parkoursettings.blocks.boostblock"));
        nextLevel = Material.matchMaterial(config.getString("parkoursettings.blocks.finishblock"));

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
        loadParkourLevels();
        checkpointOnly
                = config.getBoolean("parkoursettings.gamesettings.checkpointonly");
    }





}
