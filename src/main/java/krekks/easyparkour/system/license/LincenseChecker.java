package krekks.easyparkour.system.license;


import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import static krekks.easyparkour.Config.LICENSEKEY;
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
            if(ApiCall("") == LICENSEKEY){
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    public static String ApiCall(String url) throws Exception{
        URL apiURL = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) apiURL.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }




}
