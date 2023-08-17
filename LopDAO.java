package dao;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Lop;

public class LopDAO {
	Connection conn;

    public LopDAO(Connection conn) {
        // TODO Auto-generated constructor stub

        this.conn = conn;
    }

    //insert
    public boolean insertLop(Lop lop) throws SQLException {
        String sql = "INSERT INTO lop (malop,hotengvcn, trangthai) VALUE (? , ? , ?)";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setString(1, lop.getMalop());
        statement.setString(2, lop.getHotengvcn());
        statement.setBoolean(3, lop.isTrangthai());

        boolean rowInserted = statement.executeUpdate() >  0;
        statement.close();
        return rowInserted;
    }

    //update
    public boolean updateLop(Lop lop) throws SQLException{
        String sql = "UPDATE lop SET malop = ?,hotengvcn = ?, trangthai = ?";
        sql += "WHERE id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setString(1, lop.getMalop());
        statement.setString(2, lop.getHotengvcn());
        statement.setBoolean(3, lop.isTrangthai());
        statement.setInt(4, lop.getId());

        boolean rowUpdated = statement.executeUpdate() >  0;
        statement.close();
        return rowUpdated;
    }

    //delete
    public boolean deleteLop(Lop lop) throws SQLException{
        String sql = "DELETE FROM lop WHERE id = ?";

        PreparedStatement statement = conn.prepareStatement(sql);

        statement.setInt(1, lop.getId());

        boolean rowDeleted = statement.executeUpdate() >  0;
        statement.close();
        return rowDeleted;
    }

    //listAllLop
    public List<Lop> lstAllLop() throws SQLException{
        List<Lop> lstLop = new ArrayList<>();

        String sql = "SELECT * FROM lop";
        Statement statement = conn.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()) {
            int id = resultSet.getInt("id");
            String malop = resultSet.getString("malop");
            String hotengvcn = resultSet.getString("hotengvcn");
            boolean trangthai = resultSet.getBoolean("trangthai");

            Lop lop = new Lop(id, malop,hotengvcn, trangthai);
            lstLop.add(lop);
        }

        resultSet.close();
        statement.close();
        return lstLop;
    }

    //getById
    public Lop getById(int idLop) throws SQLException{
        Lop lop = null;
        String sql = "SELECT * FROM lop WHERE ID = ?";
        PreparedStatement statement = conn.prepareStatement(sql);
        statement.setInt(1, idLop);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
        	int id = resultSet.getInt("id");
            String malop = resultSet.getString("malop");
            String hotengvcn = resultSet.getString("hotengvcn");
            boolean trangthai = resultSet.getBoolean("trangthai");

            lop = new Lop(id, malop,hotengvcn, trangthai);
        }

        resultSet.close();
        statement.close();

        return lop;
    }
}
