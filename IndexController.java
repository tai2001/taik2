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
import dao.StudentDAO;


import model.Khoa;
import model.Student;

import util.MySQLConnUtils;

@WebServlet("")
public class IndexController extends HttpServlet {
	private KhoaDAO khoaDAO;

    public void init(){
        Connection conn;
        try {
            conn = MySQLConnUtils.getMySQLConnection();
            khoaDAO = new KhoaDAO(conn);
        }catch(ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{



        try {
            List<Khoa> lstKhoa = khoaDAO.lstAllKhoa();

            request.setAttribute("listKhoa", lstKhoa);

            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            dispatcher.forward(request, response);
        }catch (SQLException e) {
            e.printStackTrace();
        }




    }
}
