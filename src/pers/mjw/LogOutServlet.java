package pers.mjw;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogOutServlet
 */
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LogOutServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		if (session.getAttribute("User") != null) {
			session.removeAttribute("User");
			System.out.println("remove User");
		}
		if (session.getAttribute("IfLogin") != null) {
			session.removeAttribute("IfLogin");
			System.out.println("remove IfLogin");
		}

		if (session.getAttribute("SearchBlog") != null) {
			session.removeAttribute("SearchBlog");
			System.out.println("remove SearchBlog");
		}

		if (session.getAttribute("SearchName") != null) {
			session.removeAttribute("SearchName");
			System.out.println("remove SearchName");
		}

		PrintWriter out = response.getWriter();
		out.println("Log Out");
		response.setHeader("Refresh", "2;URL=login");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

	}

}
