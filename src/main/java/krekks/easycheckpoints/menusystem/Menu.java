package krekks.easycheckpoints.menusystem;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

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

    @Override
    public Inventory getInventory() {
        return inventory;
    }
}
