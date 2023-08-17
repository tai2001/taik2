package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.StudentDAO;

/**
 * Servlet implementation class ListStudent
 */
@WebServlet("/ListStudent")
public class ListStudent extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private StudentDAO studentDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListStudent() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		StudentDAO studentDAO = new StudentDAO(null);
		int student = studentDAO.getTotalStudent();
		int endPage = student/3;
		if(student % 3 != 0) {
			endPage ++;
			request.setAttribute("endP", endPage);
			request.getRequestDispatcher("studentlist.jsp").forward(request, response);
		}else {
			endPage ++;
			request.setAttribute("endP", endPage);
			request.getRequestDispatcher("studentlist.jsp").forward(request, response);
		}
		}
		
	

	

}
