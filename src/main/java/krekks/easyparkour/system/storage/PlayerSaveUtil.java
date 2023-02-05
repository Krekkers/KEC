package krekks.easyparkour.system.storage;

import krekks.easyparkour.playerdata.PlayerData;
import krekks.easyparkour.system.leaderboardsystem.LeaderboardObj;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.sql.*;

import static krekks.easyparkour.system.leaderboardsystem.LeaderboardLoader.lb_List;

public class PlayerSaveUtil {
    static Connection connection = null;
    static String connURL = "jdbc:sqlite:parkour.db";

    /**
     * Initializes Database connection
     * @throws SQLException
     */
    public static void initDB() throws SQLException {
        connection = DriverManager.getConnection(connURL);
        String sql = "CREATE TABLE IF NOT EXISTS kr_KEP (" +
                " uuid VARCHAR(255) PRIMARY KEY," +
                " name VARCHAR(255)," +
                " points int(255)," +
                " finishcount int(255)" +
                ")";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.executeUpdate();
    }

    /**
     * saves playerdata if no player exists update points
     * @param pd
     * @throws SQLException
     */
    public static void savePlayer(PlayerData pd) throws SQLException {
        String sql = "INSERT INTO kr_KEP (uuid,name,points,finishcount) values(?,?,?,?) ON CONFLICT(uuid) DO UPDATE SET points = ?, finishcount = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, pd.getPlayer().getUniqueId().toString());
        stmt.setString(2, pd.getPlayer().getName());
        stmt.setInt(3, pd.getPoints());
        stmt.setInt(4, pd.getFinishCount());
        stmt.setInt(5, pd.getPoints());
        stmt.setInt(6, pd.getFinishCount());
        stmt.executeUpdate();
    }


    public static void loadLeaderboard(){
        //TODO optimize this to make it not take 10 years to load
        String sql = "SELECT * FROM kr_KEP";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                lb_List.add(new LeaderboardObj(Bukkit.getOfflinePlayer(rs.getString("uuid")), rs.getString("name"),rs.getInt("points"), rs.getInt("finishcount")));
                Bukkit.getLogger().info("t : " + rs.getString("uuid"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * DOES NOT GET but gets sets points in playerdata
     * @param pd
     */
    public static void getPlayerFromDB(PlayerData pd){
        String sql = "SELECT * FROM kr_KEP WHERE `uuid` = ?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, pd.getPlayer().getUniqueId().toString());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            pd.setPoints(rs.getInt("points"));
            pd.setFinishCount(rs.getInt("finishcount"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static int getPlayerPointsFromDB(Player p){
        String sql = "SELECT * FROM kr_KEP WHERE `uuid` = ?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, p.getUniqueId().toString());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("points");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static int getPlayerFinishCountFromDB(Player p){
        String sql = "SELECT * FROM kr_KEP WHERE `uuid` = ?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, p.getUniqueId().toString());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            return rs.getInt("finishcount");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }




}
