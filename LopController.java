package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.LopDAO;
import model.Lop;
import util.MySQLConnUtils;

@WebServlet({"/lop","/lop/form","/lop/delete"})
public class LopController extends HttpServlet{
	private LopDAO lopDAO;

    public void init() {
        Connection conn;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            lopDAO = new LopDAO(conn);
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        try {
            insertOrUpdate(request,response);
        }catch(SQLException e){
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/lop/form":
                    showForm(request,response);
                    break;
                case  "/lop/delete":
                    deleteLop(request,response);
                    break;
                default:
                    listLop(request,response);
                    break;
            }
        }catch( Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listLop(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        try {
            List<Lop> lstLop = lopDAO.lstAllLop();

            request.setAttribute("listLop", lstLop);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/lop/loplist.jsp");
            dispatcher.forward(request, response);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id")== null ? "0" : request.getParameter("id"));

        if(id != 0) {
            Lop existingLop = lopDAO.getById(id);
            request.setAttribute("lop", existingLop);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/lop/lopform.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteLop(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));

        Lop lop = new Lop(id);
        lopDAO.deleteLop(lop);
        response.sendRedirect(request.getContextPath() + "/lop");
    }

    private void insertOrUpdate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id")== null ? "0" : request.getParameter("id"));
        String malop = request.getParameter("malop");
        String hotengvcn = request.getParameter("hotengvcn");
        boolean trangthai = Boolean.parseBoolean(request.getParameter("trangthai"));

        if(id == 0) {
            Lop newLop = new Lop(malop,hotengvcn, trangthai);
            lopDAO.insertLop(newLop);
        }else {
            Lop lop = new Lop(id,malop,hotengvcn, trangthai);
            lopDAO.updateLop(lop);
        }
        response.sendRedirect(request.getContextPath() + "/lop");
    }
}
