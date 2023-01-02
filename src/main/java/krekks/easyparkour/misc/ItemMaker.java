package krekks.easyparkour.misc;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.misc.CustomItem.createCustomItem;

public class ItemMaker {
    public static ItemStack checkpointItem = createCustomItem(
            Material.matchMaterial(config.getString("parkoursettings.items.goback.material")),
            1,
            ChatColor.translateAlternateColorCodes('&' , config.getString("parkoursettings.items.goback.name"))
    );
    public static ItemStack levelSelector = createCustomItem(
            Material.matchMaterial(config.getString("parkoursettings.items.levelselector.material")),
            1,
            ChatColor.translateAlternateColorCodes('&' , config.getString("parkoursettings.items.levelselector.name"))
    );

}
