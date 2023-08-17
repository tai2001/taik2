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

import dao.LecturerDAO;
import model.Lecturer;
import util.MySQLConnUtils;

@WebServlet({"/lecturer","/lecturer/form","/lecturer/delete"})
public class LecturerController extends HttpServlet{
	private LecturerDAO lecturerDAO;
	
	public void init() {
		Connection conn;
		
		try {
			conn = MySQLConnUtils.getMySQLConnection();
			lecturerDAO= new LecturerDAO(conn);
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
		case "/lecturer/form":
			showForm(request,response);
			break;
		case  "/lecturer/delete":
			deleteLecturer(request, response);
			break;
		default:
			listLecturer(request,response);
			break;
		}
		}catch( Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listLecturer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		try {
			List<Lecturer> lstLecturer = lecturerDAO.lstAllLecturer();
			
			request.setAttribute("listLecturer", lstLecturer);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/lecturer/lecturerlist.jsp");
			dispatcher.forward(request, response);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void showForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		int id = Integer.parseInt(request.getParameter("id")== null ? "0" : request.getParameter("id"));
		
		if(id != 0) {
			Lecturer existingLecturer = lecturerDAO.getById(id);
			request.setAttribute("lecturer", existingLecturer);
		}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/lecturer/lecturerform.jsp");
			dispatcher.forward(request, response);
		
	
	}
	
	private void deleteLecturer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
int id = Integer.parseInt(request.getParameter("id"));
		
		Lecturer lecturer = new Lecturer(id);
		lecturerDAO.deleteLecturer(lecturer);
		response.sendRedirect(request.getContextPath() + "/lecturer");
	}
	
	private void insertOrUpdate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		int id = Integer.parseInt(request.getParameter("id")== null ? "0" : request.getParameter("id"));
		String hoten = request.getParameter("hoten");
		String gioitinh = request.getParameter("gioitinh");
		String ngaysinh = request.getParameter("ngaysinh")== null ? "0" : request.getParameter("ngaysinh");
		String diachi = request.getParameter("diachi");
		String anhdaidien = request.getParameter("anhdaidien");
		String chuyenmon = request.getParameter("chuyenmon");
		String bangcap = request.getParameter("bangcap");
		int sdt = Integer.parseInt(request.getParameter("sdt")== null ? "0" : request.getParameter("sdt"));
		String email = request.getParameter("email");
		boolean trangthai = Boolean.parseBoolean(request.getParameter("trangthai"));
		if(id == 0) {
			Lecturer newLecturer = new Lecturer(hoten, gioitinh, ngaysinh, diachi,anhdaidien,chuyenmon,bangcap,sdt,email,trangthai);
			lecturerDAO.insertLecturer(newLecturer);
		}else {
			Lecturer lecturer = new Lecturer(id,hoten,gioitinh,ngaysinh,diachi,anhdaidien,chuyenmon,bangcap,sdt,email,trangthai);
			lecturerDAO.updateLecturer(lecturer);
		}
		response.sendRedirect(request.getContextPath() + "/lecturer");
	}
}
