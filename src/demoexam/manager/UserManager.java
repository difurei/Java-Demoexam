package demoexam.manager;

import demoexam.Main;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserManager {
    public static String getUserRole(String login, String password) throws SQLException {
        try(Connection c = Main.getConnection())
        {
            String sql = "SELECT * FROM users WHERE UserLogin = ? AND UserPassword = ?";
            PreparedStatement ps = c.prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();
            if (resultSet.next()){
                return resultSet.getString("UserRole");
            }
        }
        return null;
    }
}
