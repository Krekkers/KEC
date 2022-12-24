package krekks.easycheckpoints;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.Sound;

import java.time.Instant;

import static krekks.easycheckpoints.EasyCheckpoints.config;
import static org.bukkit.Bukkit.broadcastMessage;

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
    public static Material checkpoint = Material.matchMaterial(config.getString("checkpointblock"));
    public static Material jump = Material.matchMaterial(config.getString("jumpblock"));
    public static Material boost = Material.matchMaterial(config.getString("boostblock"));
    public static Material elytra = Material.matchMaterial(config.getString("elytrablock"));
    public static Material finish = Material.matchMaterial(config.getString("finishblock"));
    //text
    public static String jumpText = config.getString("jumpmessage");
    public static String checkpointText = config.getString("checkpointmessage");
    public static String boostText = config.getString("boostmessage");
    public static String elytraText = config.getString("elytramessage");
    //sounds
    public static Sound jumpSound = Sound.valueOf(config.getString("jumpsound"));
    public static Sound checkpointSound = Sound.valueOf(config.getString("checkpointsound"));
    public static Sound boostSound = Sound.valueOf(config.getString("boostsound"));
    public static Sound elytraSound = Sound.valueOf(config.getString("elytrasound"));
    //values
    public static double jumpVal = config.getDouble("jumpvalue");
    public static double boostVal = config.getDouble("boostvalue");
    public static double elytraVal = config.getDouble("elytravalue");
    //

    public static void ConfigLoader(){
        time = Instant.now();
        //world = Bukkit.getWorld(config.getString("world"));
        MENUCLICKNOISE = Sound.valueOf(config.getString("menuclicksound"));
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
        broadcastMessage(ChatColor.translateAlternateColorCodes('&' , "&eK&cE&eC &6Has Been Reloaded!"));
    }





}
