package spms.servlets;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import spms.dao.UserDao;
import spms.vo.User;

@SuppressWarnings("serial")
@WebServlet("/auth/login")
public class LogInServlet extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 로그인 경로로 들어왔을 때 로그인 화면을 보여주자
		RequestDispatcher rd = req.getRequestDispatcher
						("/auth/Login.jsp");
		rd.forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
		      ServletContext sc = this.getServletContext();
		      UserDao userDao = (UserDao) sc.getAttribute("userDao");
		      
		      User user = userDao.exist(
		    		  req.getParameter("id"), 
		    		  req.getParameter("password"));
		      if (user != null) {
		        HttpSession session = req.getSession();
		        session.setAttribute("user", user);
		        resp.sendRedirect("../index.jsp");

		      } else {
		        RequestDispatcher rd = req.getRequestDispatcher(
		            "./LogInFail.jsp");
		        rd.forward(req, resp);
		      }
		}catch(Exception e) {
			throw new ServletException(e);
		}
	}
}










