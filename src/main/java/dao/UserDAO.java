package dao;

import db.MyConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public static boolean isExists(String email) throws SQLException {

        Connection con = MyConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("select email from users");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String e = rs.getString(1);
            if (e.equals(email)) {
                return true;
            }

        }

        return false;
    }

    public static int saveUser(User user) throws SQLException {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps = con.prepareStatement("insert into users (name, email) values (?,?)");
        ps.setString(1, user.getName());
        ps.setString(2, user.getEmail());
        int rs = ps.executeUpdate();
        return rs;

    }

    public static String fetchUserName(String email) throws SQLException {
        Connection con = MyConnection.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        String userName = null;


        con = MyConnection.getConnection();
        ps = con.prepareStatement("SELECT name FROM users WHERE email = ?");
        ps.setString(1, email);
        rs = ps.executeQuery();

        if (rs.next()) { // Check if the result set contains a row
            userName = rs.getString("name"); // Retrieve the name from the result set
        }


        return userName;
    }
}

