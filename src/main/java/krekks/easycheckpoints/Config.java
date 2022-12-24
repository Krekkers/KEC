package krekks.easycheckpoints;

import org.bukkit.Material;
import org.bukkit.Sound;

import java.time.Instant;

import static krekks.easycheckpoints.EasyCheckpoints.config;
import static org.bukkit.Bukkit.getLogger;

public class Config {
    public static boolean Toggle;
    public static boolean joinLogging;
    public static boolean checkpointOnly = config.getBoolean("checkpointonly");
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
    public static Material finish;
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
    //values
    public static double jumpVal;
    public static double boostVal;
    public static double elytraVal;

    public static void configLoader(){
        time = Instant.now();
        //world = Bukkit.getWorld(config.getString("world"));

        getLogger().info("Setting up blocks");
        checkpoint = Material.matchMaterial(config.getString("checkpointblock"));
        jump = Material.matchMaterial(config.getString("jumpblock"));
        boost = Material.matchMaterial(config.getString("boostblock"));
        elytra = Material.matchMaterial(config.getString("elytrablock"));
        finish = Material.matchMaterial(config.getString("finishblock"));

        getLogger().info("Setting up Text");
        jumpText = config.getString("jumpmessage");
        checkpointText = config.getString("checkpointmessage");
        boostText = config.getString("boostmessage");
        elytraText = config.getString("elytramessage");

        getLogger().info("Setting up sounds");
        MENUCLICKNOISE = Sound.valueOf(config.getString("menuclicksound"));
        jumpSound = Sound.valueOf(config.getString("jumpsound"));
        checkpointSound = Sound.valueOf(config.getString("checkpointsound"));
        boostSound = Sound.valueOf(config.getString("boostsound"));
        elytraSound = Sound.valueOf(config.getString("elytrasound"));

        getLogger().info("Loading Game settings");
        jumpVal = config.getDouble("jumpvalue");
        boostVal = config.getDouble("boostvalue");
        elytraVal = config.getDouble("elytravalue");
        joinLogging = config.getBoolean("joinloggingonlaunch");
        Toggle = config.getBoolean("autostart");
        finishX = config.getDoubleList("finishlocation").get(0);
        finishY = config.getDoubleList("finishlocation").get(1);
        finishZ = config.getDoubleList("finishlocation").get(2);
        spawnX = config.getDoubleList("spawnlocation").get(0);
        spawnY = config.getDoubleList("spawnlocation").get(1);
        spawnZ = config.getDoubleList("spawnlocation").get(2);
        joinLogging = config.getBoolean("joinloggingonlaunch");
        Toggle = config.getBoolean("autostart");
    }





}
