package krekks.easyparkour.system.license;


import org.apache.commons.io.IOUtils;
import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;
import org.json.JSONObject;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import static krekks.easyparkour.Config.LICENSEKEY;
import static krekks.easyparkour.Config.PRODUCTKEY;
import static krekks.easyparkour.KEP.PLUGIN;
import static krekks.easyparkour.misc.KrekkMessages.krekksLoggerFine;

public class LincenseChecker {
    public static void versionCheck(){




    }

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
                    Bukkit.getLogger().warning("" +
                            "┏━┓━┏┓┏━━━┓━━━━┏┓━━━┏━━┓┏━━━┓┏━━━┓┏━┓━┏┓┏━━━┓┏━━━┓━━━━┏┓┏━┓┏━━━┓┏┓━━┏┓\n" +
                            "┃┃┗┓┃┃┃┏━┓┃━━━━┃┃━━━┗┫┣┛┃┏━┓┃┃┏━━┛┃┃┗┓┃┃┃┏━┓┃┃┏━━┛━━━━┃┃┃┏┛┃┏━━┛┃┗┓┏┛┃\n" +
                            "┃┏┓┗┛┃┃┃━┃┃━━━━┃┃━━━━┃┃━┃┃━┗┛┃┗━━┓┃┏┓┗┛┃┃┗━━┓┃┗━━┓━━━━┃┗┛┛━┃┗━━┓┗┓┗┛┏┛\n" +
                            "┃┃┗┓┃┃┃┃━┃┃━━━━┃┃━┏┓━┃┃━┃┃━┏┓┃┏━━┛┃┃┗┓┃┃┗━━┓┃┃┏━━┛━━━━┃┏┓┃━┃┏━━┛━┗┓┏┛━\n" +
                            "┃┃━┃┃┃┃┗━┛┃━━━━┃┗━┛┃┏┫┣┓┃┗━┛┃┃┗━━┓┃┃━┃┃┃┃┗━┛┃┃┗━━┓━━━━┃┃┃┗┓┃┗━━┓━━┃┃━━\n" +
                            "┗┛━┗━┛┗━━━┛━━━━┗━━━┛┗━━┛┗━━━┛┗━━━┛┗┛━┗━┛┗━━━┛┗━━━┛━━━━┗┛┗━┛┗━━━┛━━┗┛━━\n" +
                            "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                            "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                            "\n" +
                            "┏━━━┓┏━━━┓┏━━━┓┏┓━━┏┓┏━━┓┏━━━┓┏━━━┓┏━━━┓\n" +
                            "┃┏━┓┃┃┏━┓┃┃┏━┓┃┃┗┓┏┛┃┗┫┣┛┗┓┏┓┃┃┏━━┛┗┓┏┓┃\n" +
                            "┃┗━┛┃┃┗━┛┃┃┃━┃┃┗┓┃┃┏┛━┃┃━━┃┃┃┃┃┗━━┓━┃┃┃┃\n" +
                            "┃┏━━┛┃┏┓┏┛┃┃━┃┃━┃┗┛┃━━┃┃━━┃┃┃┃┃┏━━┛━┃┃┃┃\n" +
                            "┃┃━━━┃┃┃┗┓┃┗━┛┃━┗┓┏┛━┏┫┣┓┏┛┗┛┃┃┗━━┓┏┛┗┛┃\n" +
                            "┗┛━━━┗┛┗━┛┗━━━┛━━┗┛━━┗━━┛┗━━━┛┗━━━┛┗━━━┛\n" +
                            "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━\n" +
                            "━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━");
                    Bukkit.getLogger().info("Please provide a valid license key!");

                    disablePlugin();
                    this.cancel();
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
        try{
            String nonce = getJson(new URL("http://wpwoopluginseller.local/?nonce=1&license_key=" + LICENSEKEY)).getString("");
            return getJson(new URL(
                    "http://wpwoopluginseller.local/wp-json/wlm/v1/license?license_key="
                            + LICENSEKEY + "&product_id=" + PRODUCTKEY + "&nonce=" + nonce)).getString("") == LICENSEKEY;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static JSONObject getJson(URL url) throws IOException {
        String json = IOUtils.toString(url, Charset.forName("UTF-8"));
        return new JSONObject(json);
    }

}
