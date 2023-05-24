package krekks.easyparkour.misc.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

import static krekks.easyparkour.KEP.config;
import static krekks.easyparkour.misc.item.CustomItem.createCustomItem;

public class ItemMaker {
    public static ItemStack checkpointItem = createCustomItem(
            Material.matchMaterial(Objects.requireNonNull(config.fileConfig.getString("parkoursettings.items.goback.material"))),
            1,
            ChatColor.translateAlternateColorCodes('&' , Objects.requireNonNull(config.fileConfig.getString("parkoursettings.items.goback.name")))
    );
    public static ItemStack levelSelector = createCustomItem(
            Material.matchMaterial(Objects.requireNonNull(config.fileConfig.getString("parkoursettings.items.levelselector.material"))),
            1,
            ChatColor.translateAlternateColorCodes('&' , Objects.requireNonNull(config.fileConfig.getString("parkoursettings.items.levelselector.name")))
    );

}
