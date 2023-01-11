package krekks.easyparkour.system.storage;

import krekks.easyparkour.playerdata.PlayerData;

import java.sql.*;

public class PlayerSaveUtil {
    static Connection connection = null;

    public static void initDB() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:/plugin/kep/database.db");
        String sql = "CREATE TABLE IF NOT EXISTS kr_KEP (" +
                " uuid VARCHAR(255) PRIMARY KEY," +
                " name VARCHAR(255)," +
                " points int(255)" +
                ")";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.executeUpdate();
    }

    public static void savePlayer(PlayerData pd) throws SQLException {
        String sql = "INSERT INTO kr_KEP (uuid,name,points) values(?,?,?) ON CONFLICT(uuid) DO UPDATE SET `points` = ?";
        PreparedStatement stmt = connection.prepareStatement(sql);
        stmt.setString(1, pd.getPlayer().getUniqueId().toString());
        stmt.setString(2, pd.getPlayer().getName());
        stmt.setInt(3, pd.getPoints());
        stmt.setInt(4, pd.getPoints());
        stmt.executeUpdate();
    }

    public static void getPlayerFromDB(PlayerData pd){
        String sql = "SELECT * FROM kr_KEP WHERE `uuid` = ?";
        PreparedStatement stmt = null;
        try {
            stmt = connection.prepareStatement(sql);
            stmt.setString(1, pd.getPlayer().getUniqueId().toString());
            ResultSet rs = stmt.executeQuery();
            rs.next();
            pd.setPoints(rs.getInt("points"));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }




}
