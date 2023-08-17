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

import dao.KhoaDAO;
import model.Khoa;
import util.MySQLConnUtils;

@WebServlet({"/khoa","/khoa/form","/khoa/delete"})
public class KhoaController extends HttpServlet{
	private KhoaDAO khoaDAO;

    public void init() {
        Connection conn;

        try {
            conn = MySQLConnUtils.getMySQLConnection();
            khoaDAO = new KhoaDAO(conn);
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
                case "/khoa/form":
                    showForm(request,response);
                    break;
                case  "/khoa/delete":
                    deleteKhoa(request,response);
                    break;
                default:
                    listKhoa(request,response);
                    break;
            }
        }catch( Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listKhoa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        try {
            List<Khoa> lstKhoa = khoaDAO.lstAllKhoa();

            request.setAttribute("listKhoa", lstKhoa);

            RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/khoa/khoalist.jsp");
            dispatcher.forward(request, response);
        }catch(SQLException e) {
            e.printStackTrace();
        }
    }

    private void showForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id")== null ? "0" : request.getParameter("id"));

        if(id != 0) {
            Khoa existingKhoa = khoaDAO.getById(id);
            request.setAttribute("khoa", existingKhoa);
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/khoa/khoaform.jsp");
        dispatcher.forward(request, response);
    }

    private void deleteKhoa(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id"));

        Khoa khoa = new Khoa(id);
        khoaDAO.deleteKhoa(khoa);
        response.sendRedirect(request.getContextPath() + "/khoa");
    }

    private void insertOrUpdate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
        int id = Integer.parseInt(request.getParameter("id")== null ? "0" : request.getParameter("id"));
        String tenkhoa = request.getParameter("tenkhoa");
        boolean trangthai = Boolean.parseBoolean(request.getParameter("trangthai"));

        if(id == 0) {
            Khoa newKhoa = new Khoa(tenkhoa, trangthai);
            khoaDAO.insertKhoa(newKhoa);
        }else {
            Khoa khoa = new Khoa(id,tenkhoa, trangthai);
            khoaDAO.updateKhoa(khoa);
        }
        response.sendRedirect(request.getContextPath() + "/khoa");
    }
}
