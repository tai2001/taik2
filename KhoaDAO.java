package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Khoa;

public class KhoaDAO {
	Connection conn;

    public KhoaDAO(Connection conn) {
        // TODO Auto-generated constructor stub

        this.conn = conn;
    }

    //insert
    public boolean insertKhoa(Khoa khoa) throws SQLException {
        String sql = "INSERT INTO khoa (tenkhoa, trangthai) VALUE (? , ?)";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, khoa.getTenkhoa());
        statement.setBoolean(2, khoa.isTrangthai());

        boolean rowInserted = statement.executeUpdate() >  0;
        statement.close();
        return rowInserted;
    }

    //update
    public boolean updateKhoa(Khoa khoa) throws SQLException{
        String sql = "UPDATE khoa SET tenkhoa = ?, trangthai = ?";
        sql += "WHERE id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, khoa.getTenkhoa());
        statement.setBoolean(2, khoa.isTrangthai());
        statement.setInt(3, khoa.getId());

        boolean rowUpdated = statement.executeUpdate() >  0;
        statement.close();
        return rowUpdated;
    }

    //delete
    public boolean deleteKhoa(Khoa khoa) throws SQLException{
        String sql = "DELETE FROM khoa WHERE id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, khoa.getId());

        boolean rowDeleted = statement.executeUpdate() >  0;
        statement.close();
        return rowDeleted;
    }

    //listAllKhoa
    public List<Khoa> lstAllKhoa() throws SQLException{
        List<Khoa> lstKhoa = new ArrayList<>();

        String sql = "SELECT * FROM khoa";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String tenkhoa = resultSet.getString("tenkhoa");
            boolean trangthai = resultSet.getBoolean("trangthai");

            Khoa khoa = new Khoa(id, tenkhoa, trangthai);
            lstKhoa.add(khoa);
        }

        resultSet.close();
        statement.close();
        return lstKhoa;
    }

    //getById
    public Khoa getById(int idKhoa) throws SQLException{
        Khoa khoa = null;
        String sql = "SELECT * FROM khoa WHERE ID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, idKhoa);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            String tenkhoa = resultSet.getString("tenkhoa");
            boolean trangthai = resultSet.getBoolean("trangthai");

            khoa = new Khoa(id, tenkhoa, trangthai);
        }

        resultSet.close();
        statement.close();

        return khoa;
    }
}
