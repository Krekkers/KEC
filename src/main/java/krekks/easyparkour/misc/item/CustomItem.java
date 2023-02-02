package krekks.easyparkour.misc.item;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class CustomItem {


    public static ItemStack createCustomItem(Material material, int amount,  String name, String... description){
        ItemStack item = new ItemStack(material);
        item.setAmount(amount);
        ItemMeta meta = item.getItemMeta();
        assert meta != null;
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        List<String> postColor = new ArrayList<String>();
        for(String s : description){
            s = ChatColor.translateAlternateColorCodes('&' ,s);
            postColor.add(s);
        }
        meta.setLore(postColor);
        item.setItemMeta(meta);
        return item;
    }


}
