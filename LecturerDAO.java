package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Lecturer;


public class LecturerDAO {
	Connection conn;

    public LecturerDAO(Connection conn) {
        // TODO Auto-generated constructor stub

        this.conn = conn;
    }

    //insert
    public boolean insertLecturer(Lecturer lecturer) throws SQLException {
        String sql = "INSERT INTO lecturer (hoten ,gioitinh,ngaysinh,diachi,anhdaidien,chuyenmon,bangcap,sdt,email,trangthai) VALUE ( ? , ? , ? , ? , ? , ? , ? , ? , ? , ?  )";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, lecturer.getHoten());
        statement.setString(2, lecturer.getGioitinh());
        statement.setString(3, lecturer.getNgaysinh());
        statement.setString(4, lecturer.getDiachi());
        statement.setString(5, lecturer.getAnhdaidien());
        statement.setString(6, lecturer.getChuyenmon());
        statement.setString(7, lecturer.getBangcap());
        statement.setInt(8, lecturer.getSdt());
        statement.setString(9, lecturer.getEmail());
        statement.setBoolean(10, lecturer.isTrangthai());
        boolean rowInserted = statement.executeUpdate() >  0;
        statement.close();
        return rowInserted;
    }

    //update
    public boolean updateLecturer(Lecturer lecturer) throws SQLException{
        String sql = "UPDATE lecturer SET  hoten = ?, gioitinh = ?, ngaysinh = ?, diachi = ?,anhdaidien = ? ,chuyenmon = ? ,bangcap = ? ,sdt = ? ,email = ? , trangthai = ?";
        sql += "WHERE id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, lecturer.getHoten());
        statement.setString(2, lecturer.getGioitinh());
        statement.setString(3, lecturer.getNgaysinh());
        statement.setString(4, lecturer.getDiachi());
        statement.setString(5, lecturer.getAnhdaidien());
        statement.setString(6, lecturer.getChuyenmon());
        statement.setString(7, lecturer.getBangcap());
        statement.setInt(8, lecturer.getSdt());
        statement.setString(9, lecturer.getEmail());
        statement.setBoolean(10, lecturer.isTrangthai());
        statement.setInt(11, lecturer.getId());

        boolean rowUpdated = statement.executeUpdate() >  0;
        statement.close();
        return rowUpdated;
    }

    //delete
    public boolean deleteLecturer(Lecturer lecturer) throws SQLException{
        String sql = "DELETE FROM lecturer WHERE id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, lecturer.getId());

        boolean rowDeleted = statement.executeUpdate() >  0;
        statement.close();
        return rowDeleted;
    }

    //listAllLecturer
    public List<Lecturer> lstAllLecturer() throws SQLException{
        List<Lecturer> lstAllLecturer = new ArrayList<>();

        String sql = "SELECT * FROM lecturer";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String hoten = resultSet.getString("hoten");
            String gioitinh = resultSet.getString("gioitinh");
            String ngaysinh = resultSet.getString("ngaysinh");
            String diachi = resultSet.getString("diachi");
            String anhdaidien = resultSet.getString("anhdaidien");
            String chuyenmon = resultSet.getString("chuyenmon");
            String bangcap = resultSet.getString("bangcap");
            int sdt = resultSet.getInt("sdt");
            String email = resultSet.getString("email");
            boolean trangthai = resultSet.getBoolean("trangthai");
            Lecturer lecturer = new Lecturer(id,hoten, gioitinh, ngaysinh,diachi, anhdaidien,chuyenmon,bangcap,sdt,email,trangthai);
            lstAllLecturer.add(lecturer);
        }

        resultSet.close();
        statement.close();
        return lstAllLecturer;
    }

    //getById
    public Lecturer getById(int idLecturer) throws SQLException{
        Lecturer lecturer = null;
        String sql = "SELECT * FROM lecturer WHERE ID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, idLecturer);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
        	int id = resultSet.getInt("id");
            String hoten = resultSet.getString("hoten");
            String gioitinh = resultSet.getString("gioitinh");
            String ngaysinh = resultSet.getString("ngaysinh");
            String diachi = resultSet.getString("diachi");
            String anhdaidien = resultSet.getString("anhdaidien");
            String chuyenmon = resultSet.getString("chuyenmon");
            String bangcap = resultSet.getString("bangcap");
            int sdt = resultSet.getInt("sdt");
            String email = resultSet.getString("email");
            boolean trangthai = resultSet.getBoolean("trangthai");
            lecturer = new Lecturer(id,hoten, gioitinh, ngaysinh,diachi, anhdaidien,chuyenmon,bangcap,sdt,email,trangthai);
        }

        resultSet.close();
        statement.close();

        return lecturer;
    }
}
