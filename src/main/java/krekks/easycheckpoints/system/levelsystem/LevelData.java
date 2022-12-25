package krekks.easycheckpoints.system.levelsystem;

import org.bukkit.Location;

public class LevelData {
    int levelID;
    Location levelSpawn;
    String levelName;
    String difficulty;

    public LevelData (int id, Location l, String name, String d){
        levelID = id;
        levelSpawn = l;
        levelName = name;
        difficulty = d;
    }

    public int getLevelID() {
        return levelID;
    }

    public void setLevelID(int levelID) {
        this.levelID = levelID;
    }

    public Location getLevelSpawn() {
        return levelSpawn;
    }

    public void setLevelSpawn(Location levelSpawn) {
        this.levelSpawn = levelSpawn;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }
}
