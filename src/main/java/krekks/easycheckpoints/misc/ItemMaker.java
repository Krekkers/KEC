package krekks.easycheckpoints.misc;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import static krekks.easycheckpoints.EasyCheckpoints.config;

public class ItemMaker {
    /**
     * This function will make you a item with a name. Doesnt have to be goback item
     * @param mat Input material to turn into a item
     * @return The resulting item.
     */
    public static ItemStack makeGoBackItem(Material mat){
        ItemStack newItem = new ItemStack(mat);
        ItemMeta itemMeta = newItem.getItemMeta();
        itemMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&' , config.getString("backitemname")));
        newItem.setItemMeta(itemMeta);
        return newItem;
    }

}
