package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.User;

public class UserDAO {
	Connection conn;

    public UserDAO(Connection conn) {
        // TODO Auto-generated constructor stub

        this.conn = conn;
    }

    //insert
    public boolean insertUser(User user) throws SQLException {
        String sql = "INSERT INTO user (user_name,user_password, trangthai) VALUE (? , ? , ?)";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, user.getUser_name());
        statement.setString(2, user.getUser_password());
        statement.setBoolean(3, user.isTrangthai());

        boolean rowInserted = statement.executeUpdate() >  0;
        statement.close();
        return rowInserted;
    }

    //update
    public boolean updateUser(User user) throws SQLException{
        String sql = "UPDATE user SET user_name = ?,user_password = ?, trangthai = ?";
        sql += "WHERE id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, user.getUser_name());
        statement.setString(2, user.getUser_password());
        statement.setBoolean(3, user.isTrangthai());
        statement.setInt(3, user.getId());

        boolean rowUpdated = statement.executeUpdate() >  0;
        statement.close();
        return rowUpdated;
    }

    //delete
    public boolean deleteUser(User user) throws SQLException{
        String sql = "DELETE FROM user WHERE id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, user.getId());

        boolean rowDeleted = statement.executeUpdate() >  0;
        statement.close();
        return rowDeleted;
    }

    //listAllUser
    public List<User> lstAllUser() throws SQLException{
        List<User> lstUser = new ArrayList<>();

        String sql = "SELECT * FROM user";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String user_name = resultSet.getString("user_name");
            String user_password = resultSet.getString("user_password");
            boolean trangthai = resultSet.getBoolean("trangthai");

            User user = new User(id, user_name,user_password, trangthai);
            lstUser.add(user);
        }

        resultSet.close();
        statement.close();
        return lstUser;
    }

    //getById
    public User getById(int idUser) throws SQLException{
        User user = null;
        String sql = "SELECT * FROM user WHERE ID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, idUser);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
        	int id = resultSet.getInt("id");
            String user_name = resultSet.getString("user_name");
            String user_password = resultSet.getString("user_password");
            boolean trangthai = resultSet.getBoolean("trangthai");


            user = new User(id, user_name,user_password, trangthai);
        }

        resultSet.close();
        statement.close();

        return user;
    }
}
