package krekks.easycheckpoints.misc;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CustomItem {


    public static ItemStack createCustomItem(Material material, int amount,  String name, String... description){
        ItemStack item = new ItemStack(material);
        item.setAmount(amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> preColor = Arrays.asList(description);
        List<String> postColor = new ArrayList<String>();
        for(String s : preColor){
            s = ChatColor.translateAlternateColorCodes('&' ,s);
            postColor.add(s);
        }
        meta.setLore(postColor);
        item.setItemMeta(meta);
        return item;
    }


}
