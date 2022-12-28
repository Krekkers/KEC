package krekks.easyparkour.system.menusystem;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

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
}
