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

import dao.StudentDAO;
import model.Khoa;
import model.Student;
import util.MySQLConnUtils;

@WebServlet({"/student","/student/form","/student/delete"})
public class StudentController extends HttpServlet{
	private StudentDAO studentDAO;
	
	public void init() {
		Connection conn;
		
		try {
			conn = MySQLConnUtils.getMySQLConnection();
			studentDAO= new StudentDAO(conn);
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
		case "/student/form":
			showForm(request,response);
			break;
		case  "/student/delete":
			deleteStudent(request, response);
			break;
		default:
			listStudent(request,response);
			break;
		}
		}catch( Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		try {
			List<Student> lstStudent = studentDAO.lstAllStudent();
			
			request.setAttribute("listStudent", lstStudent);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("WEB-INF/student/studentlist.jsp");
			dispatcher.forward(request, response);
		}catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	private void showForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		int id = Integer.parseInt(request.getParameter("id")== null ? "0" : request.getParameter("id"));
		
		if(id != 0) {
			Student existingStudent = studentDAO.getById(id);
			request.setAttribute("student", existingStudent);
		}
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/student/studentform.jsp");
			dispatcher.forward(request, response);
		
	
	}
	
	private void deleteStudent(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
int id = Integer.parseInt(request.getParameter("id"));
		
		Student student = new Student(id);
		studentDAO.deleteStudent(student);
		response.sendRedirect(request.getContextPath() + "/student");
	}
	
	private void insertOrUpdate(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException{
		int id = Integer.parseInt(request.getParameter("id")== null ? "0" : request.getParameter("id"));
		int mssv = Integer.parseInt(request.getParameter("msnv")== null ? "0" : request.getParameter("msnv"));
		String hoten = request.getParameter("hoten");
		String gioitinh = request.getParameter("gioitinh");
		String ngaysinh = request.getParameter("ngaysinh")== null ? "0" : request.getParameter("ngaysinh");
		String diachi = request.getParameter("diachi");
		String anhdaidien = request.getParameter("anhdaidien");
		int sdt = Integer.parseInt(request.getParameter("sdt")== null ? "0" : request.getParameter("sdt"));
		String email = request.getParameter("email");
		boolean trangthai = Boolean.parseBoolean(request.getParameter("trangthai"));
		if(id == 0) {
			Student newStudent = new Student(mssv, hoten, gioitinh, ngaysinh, diachi,anhdaidien,sdt,email,trangthai);
			studentDAO.insertStudent(newStudent);
		}else {
			Student student = new Student(id,mssv, hoten,gioitinh,ngaysinh,diachi,anhdaidien,sdt,email,trangthai);
			studentDAO.updateStudent(student);
		}
		response.sendRedirect(request.getContextPath() + "/student");
	}
}
