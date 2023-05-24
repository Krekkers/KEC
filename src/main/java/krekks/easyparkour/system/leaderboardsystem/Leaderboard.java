package krekks.easyparkour.system.leaderboardsystem;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

import java.util.ArrayList;
import java.util.Comparator;

import static krekks.easyparkour.system.leaderboardsystem.LeaderboardLoader.lb_List;

public class Leaderboard {
    //holds all the data for the leaderboard itself
    private int id;
    private String name;

    private Location loc;
    private int limit;
    private double lineOffset = -0.3;
    private String type; // how is this one sorted?
    private ArrayList<String> lines = new ArrayList<>();
    private ArrayList<Entity> lineEntity = new ArrayList<>();

    public Leaderboard(int id, String name, Location loc, int limit, String type) {
        this.id = id;
        this.name = name;
        this.loc = loc;
        this.limit = limit;
        this.type = type;
        Bukkit.getLogger().info("loc :" + loc);
        Comparator<LeaderboardPlayer> comparator = Comparator.comparing(LeaderboardPlayer::getFinishCount).reversed();
        lines.add(ChatColor.translateAlternateColorCodes('&', name));
        int i = 0;
        for(LeaderboardPlayer lp : lb_List.stream().sorted(comparator).toList()){
            lines.add(ChatColor.translateAlternateColorCodes('&',
                    "&c" + (i + 1) + " &a" + lp.name + " : &c" + lp.getFinishCount()));
            i += 1;
        }
    }

    public void CreateWorldObject(){
        Location loc2 = loc.clone();
        // + 1 is because the name string is reserved
        removeEntities(false); // put this to true to have an loop and break your server :)
        int _l = limit;
        if(lines.size() <= _l)
            _l = lines.size();

        Bukkit.getLogger().info("_l = " + _l);
        for(int i = 0; i < _l + 1; i++){
            //took this from https://www.spigotmc.org/threads/tutorial-holograms-1-8.65183/
            ArmorStand as = (ArmorStand) loc2.getWorld().spawnEntity(loc2, EntityType.ARMOR_STAND); //Spawn the ArmorStand
            lineEntity.add(as);
            as.setGravity(false); //Make sure it doesn't fall
            as.setCanPickupItems(false); //Might as well
            as.setCustomName(lines.get(i)); //Set this to the text you want
            Bukkit.getLogger().info("name " + lines.get(i));
            as.setCustomNameVisible(true); //This makes the text appear no matter if your looking at the entity or not
            as.setVisible(false); //Makes the ArmorStand invisible
            //newline
            loc2.add(0,lineOffset, 0); // Offset
            Bukkit.getLogger().info("location "  + loc2);
        }
    }
    //some stuff interesting to play with if i want to add holograms
    public void addline(String input){
        lines.add(input);
        removeEntities(true);
    }
    public String getLine(int index){
        return lines.get(index);
    }
    public void editLine(int index, String input){
        lines.set(index, input);
        removeEntities(true);
    }
    public void removeLine(int id){
        lines.remove(id);
        removeEntities(true);
    }
    public void removeEntities(boolean replace){
        for(Entity en : lineEntity){
            en.remove();
        }
        if(replace)
            CreateWorldObject();
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLoc() {
        return loc;
    }

    public void setLoc(Location loc) {
        this.loc = loc;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public double getLineOffset() {
        return lineOffset;
    }

    public void setLineOffset(double lineOffset) {
        this.lineOffset = lineOffset;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
