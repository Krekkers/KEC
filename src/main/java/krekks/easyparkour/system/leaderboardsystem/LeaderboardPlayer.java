package krekks.easyparkour.system.leaderboardsystem;

import org.bukkit.OfflinePlayer;

public class LeaderboardPlayer {
    public OfflinePlayer p;
    public String name;
    public int points;
    public int finishCount;

    public LeaderboardPlayer(OfflinePlayer op, String name, int p, int fc){
        this.p = op;
        this.name = name;
        points = p;
        finishCount = fc;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public OfflinePlayer getP() {
        return p;
    }

    public void setP(OfflinePlayer p) {
        this.p = p;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getFinishCount() {
        return finishCount;
    }

    public void setFinishCount(int finishCount) {
        this.finishCount = finishCount;
    }

}
