package com.sn.fly_sim.helpers;

import com.sn.fly_sim.FlySimulator;
import com.sn.fly_sim.Player;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;


public class PlayerHandler extends DBConfigs {
    // ToDo пока функции работают как пустышки, реализовать тело, когда подключу бд
    Connection dbConnection;

    public PlayerHandler() throws SQLException, ClassNotFoundException {
        getDbConnection();
    }

    public void getDbConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");

        String connectionStr = "jdbc:mysql://" + dbHost + ":" + dbPort + '/' + dbName;

        dbConnection = DriverManager.getConnection(connectionStr, dbUser, dbPass);
    }

    public boolean checkForLogin(String login){
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + DBConfigs.USER_TABLE + " WHERE "
                + DBConfigs.USER_NAME + "=?";

        try {
            // Считанными из места хранения данных игроков
            PreparedStatement prSt = dbConnection.prepareStatement(select);
            prSt.setString(1, login);

            resultSet = prSt.executeQuery();
            int count = 0;
            while(resultSet.next())
                count++;

            if (count > 0)
                return true;
        } catch (SQLException e){
            e.printStackTrace();
        }
        return false;
    }

    public Player getPlayer(String login, String pass) {
        ResultSet resultSet = null;
        String select = "SELECT * FROM " + DBConfigs.USER_TABLE + " WHERE "
                + DBConfigs.USER_NAME + "=? AND " + DBConfigs.USER_PASS + "=?";

        try {
            // Считанными из места хранения данных игроков
            PreparedStatement prSt = dbConnection.prepareStatement(select);
            prSt.setString(1, login);
            prSt.setString(2, pass);

            resultSet = prSt.executeQuery();

            if (resultSet != null) {
                resultSet.next();
                String name = resultSet.getString(2);
                int record = resultSet.getInt(4);
                return new Player(name, record);
            }
        } catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    /*
    Если фукнция возвращает false, значит игрок с таким ником уже существует. И создание не удалось
    Если true, значит создание успешно
     */
    public void createPlayer(String login, String password){
        String insert = "INSERT INTO " + DBConfigs.USER_TABLE + "("
                + DBConfigs.USER_NAME + "," + DBConfigs.USER_PASS + ","
                + DBConfigs.USER_RECORD + ")"
                + "VALUES(?, ?, ?)";

        try {
            PreparedStatement prSt = dbConnection.prepareStatement(insert);
            prSt.setString(1, login);
            prSt.setString(2, password);
            prSt.setInt(3, 0);

            prSt.executeUpdate();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void rewriterecord(String login, int record){
        ResultSet resultSet = null;
        String update = "UPDATE " + DBConfigs.USER_TABLE +
        " SET " +  DBConfigs.USER_RECORD + " = " + record +
        " WHERE (" +  DBConfigs.USER_NAME + " = " + DBConfigs.USER_NAME + ")";
        try {
            PreparedStatement prSt = dbConnection.prepareStatement(update);
            prSt.execute();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
