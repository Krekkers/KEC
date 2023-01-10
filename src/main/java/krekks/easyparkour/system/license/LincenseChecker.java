package krekks.easyparkour.system.license;


import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import static krekks.easyparkour.KEP.PLUGIN;
import static krekks.easyparkour.misc.KrekkMessages.krekksLoggerFine;

public class LincenseChecker {


    public static void licenseCheck(){
        int tps = 20; //tps is to calc the looptime
        int loopTime = 600 * tps;
        new BukkitRunnable() {
            @Override
            public void run() {
                if(verifyLicense()){
                 //happy
                    krekksLoggerFine(
                            "License Verified",
                                  "All is oke!");
                }else{
                    //not happy
                    disablePlugin();
                }
            }
        }.runTaskTimer(PLUGIN, 0, loopTime);
        Bukkit.getLogger().fine("Started loop");
    }

    public static void disablePlugin(){
        Bukkit.getServer().getPluginManager().disablePlugin(PLUGIN);
    }


    public static boolean verifyLicense(){
        //will contain the api logic
        return true;
    }



}
