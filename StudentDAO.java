package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Student;
public class StudentDAO {
	Connection conn;

    public StudentDAO(Connection conn) {
        // TODO Auto-generated constructor stub

        this.conn = conn;
    }

    //insert
    public boolean insertStudent(Student student) throws SQLException {
        String sql = "INSERT INTO student (mssv, hoten ,gioitinh,ngaysinh,diachi,anhdaidien,sdt,email,trangthai) VALUE ( ? , ? , ? , ? , ? , ? , ? , ? , ? )";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, student.getMssv());
        statement.setString(2, student.getHoten());
        statement.setString(3, student.getGioitinh());
        statement.setString(4, student.getNgaysinh());
        statement.setString(5, student.getDiachi());
        statement.setString(6, student.getAnhdaidien());
        statement.setInt(7, student.getSdt());
        statement.setString(8, student.getEmail());
        statement.setBoolean(9, student.isTrangthai());
        boolean rowInserted = statement.executeUpdate() >  0;
        statement.close();
        return rowInserted;
    }

    //update
    public boolean updateStudent(Student student) throws SQLException{
        String sql = "UPDATE student SET mssv = ?, hoten = ?, gioitinh = ?, ngaysinh = ?, diachi = ?,anhdaidien = ? ,sdt = ? ,email = ? , trangthai = ?";
        sql += "WHERE id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, student.getMssv());
        statement.setString(2, student.getHoten());
        statement.setString(3, student.getGioitinh());
        statement.setString(4, student.getNgaysinh());
        statement.setString(5, student.getDiachi());
        statement.setString(6, student.getAnhdaidien());
        statement.setInt(7, student.getSdt());
        statement.setString(8, student.getEmail());
        statement.setBoolean(9, student.isTrangthai());
        statement.setInt(10, student.getId());

        boolean rowUpdated = statement.executeUpdate() >  0;
        statement.close();
        return rowUpdated;
    }

    //delete
    public boolean deleteStudent(Student student) throws SQLException{
        String sql = "DELETE FROM student WHERE id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, student.getId());

        boolean rowDeleted = statement.executeUpdate() >  0;
        statement.close();
        return rowDeleted;
    }

    //listAllStudent
    public List<Student> lstAllStudent() throws SQLException{
        List<Student> lstAllStudent = new ArrayList<>();

        String sql = "SELECT * FROM student";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            int mssv = resultSet.getInt("mssv");
            String hoten = resultSet.getString("hoten");
            String gioitinh = resultSet.getString("gioitinh");
            String ngaysinh = resultSet.getString("ngaysinh");
            String diachi = resultSet.getString("diachi");
            String anhdaidien = resultSet.getString("anhdaidien");
            int sdt = resultSet.getInt("sdt");
            String email = resultSet.getString("email");
            boolean trangthai = resultSet.getBoolean("trangthai");
            Student employee = new Student(id,mssv,hoten, gioitinh, ngaysinh,diachi, anhdaidien,sdt,email,trangthai);
            lstAllStudent.add(employee);
        }

        resultSet.close();
        statement.close();
        return lstAllStudent;
    }
    
    // dem so luong student trong db
    public int getTotalStudent() {
    	String sql ="SELECT COUNT(*) FROM student"; 
    	try {
    		Statement statement = conn.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while(resultSet.next()) {
            	// chỉ trả về 1 kết quả
            	return resultSet.getInt(1);
            }
		} catch (Exception e) {
			// TODO: handle exception
		}
    	return 0;
    }

    //getById
    public Student getById(int idStudent) throws SQLException{
        Student student = null;
        String sql = "SELECT * FROM student WHERE ID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, idStudent);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            int id = resultSet.getInt("id");
            int mssv = resultSet.getInt("mssv");
            String hoten = resultSet.getString("hoten");
            String gioitinh = resultSet.getString("gioitinh");
            String ngaysinh = resultSet.getString("ngaysinh");
            String diachi = resultSet.getString("diachi");
            String anhdaidien = resultSet.getString("anhdaidien");
            int sdt = resultSet.getInt("sdt");
            String email = resultSet.getString("email");
            boolean trangthai = resultSet.getBoolean("trangthai");
            student = new Student(id,mssv,hoten, gioitinh, ngaysinh,diachi, anhdaidien,sdt,email,trangthai);
        }

        resultSet.close();
        statement.close();

        return student;
    }
}
