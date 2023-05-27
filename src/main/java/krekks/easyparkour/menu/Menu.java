package krekks.easyparkour.menu;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public abstract class Menu implements InventoryHolder {
    protected Inventory inventory;
    protected MenuUtility menuUtility;

    public Menu(MenuUtility utility){
        this.menuUtility = utility;
    }

    public abstract String getMenuName();
    public abstract int getSlots();
    public abstract void setMenuItems();

    /**
     * Custom handling of the menu
     * @param e event
     */
    public abstract void handleMenu(InventoryClickEvent e);

    /**
     * opens the inventory
     */
    public void openMenu(){
         inventory = Bukkit.createInventory(this,getSlots(),getMenuName());
         this.setMenuItems();

         menuUtility.getOwner().openInventory(inventory);
    }

    public void closeMenu(){
        menuUtility.getOwner().closeInventory();
    }

    public void fillInventoryWith(ItemStack item) {
        for (int i = 0; i < getSlots(); i++) {
            if (inventory.getItem(i) == null) {
                inventory.setItem(i, item);
            }
        }
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }



    //extra functionality
    public String decoratedName(String name){
        String n = "&9[&c" + name + "&9]";
        return ChatColor.translateAlternateColorCodes('&', n);
    }

    /**
     * change a item in this specific inventory
     * @param slot
     * @param newItem
     */
    public void changeItem(int slot, ItemStack newItem){
        inventory.setItem(slot,newItem);
    }

    /**
     * Creates a checker pattern from 2 items
     * @param item1
     * @param item2
     */
    public void fillInventoryWithPattern(ItemStack item1, ItemStack item2) {
        for (int i = 0; i < getSlots(); i++) {
            if (inventory.getItem(i) != null)
                return;
            //pattern
            if (i % 2 == 0)
                inventory.setItem(i, item1);
            else
                inventory.setItem(i, item2);
        }
    }

    /**
     * creates a new item
     * @param name
     * @param material
     * @param lore
     * @return
     */
    public ItemStack newItem(String name, Material material, String... lore){
        ItemStack item = new ItemStack(material);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', name));
        item.setItemMeta(meta);
        return item;
    }

    /**
     * made to use less code in menu
     * @param slot
     * @param item
     */
    public void setItem(int slot, ItemStack item){
        inventory.setItem(slot, item);
    }

    public void addItemsToInventory(HashMap<Integer, ItemStack> items){
        for(int i = 0; i < inventory.getSize(); i++){
            if(items.get(i) != null) inventory.setItem(i, items.get(i));
        }
    }



}
