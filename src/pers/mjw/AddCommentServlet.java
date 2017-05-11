package pers.mjw;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class AddCommentServlet
 */
public class AddCommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCommentServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		if (!request.getParameter("comment").equals("")) {
			doPost(request, response);
		} else {
			out.println("No Commtent");
			response.setHeader("Refresh", "2;URL=blogs");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		String blogname = (String) request.getParameter("blogname");
		String blogdate = (String) request.getParameter("blogdate");
		User user1 = (User) session.getAttribute("User");
		String commentuser = user1.getName();
		String comment = "";
		if (!request.getParameter("comment").equals("")) {
			comment = request.getParameter("comment");

			int x = 0;
			PrintWriter out = response.getWriter();

			Connection con;
			PreparedStatement pstmt;
			ResultSet rs;

			String url = "jdbc:mysql://127.0.0.1:3306/sql?useUnicode=true&characterEncoding=utf-8";
			String driver = "com.mysql.jdbc.Driver";
			String user = "root";
			String psw = "";

			String strsql = "insert into comment(blogname,blogdate,commentuser,comment) values(?,?,?,?)";

			try {
				Class.forName(driver);
				con = DriverManager.getConnection(url, user, psw);
				System.out.println(con);
				pstmt = ((java.sql.Connection) con).prepareStatement(strsql);
				pstmt.setString(1, blogname);
				pstmt.setString(2, blogdate);
				pstmt.setString(3, commentuser);
				pstmt.setString(4, comment);
				pstmt.executeUpdate();

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				out.println("ERROR");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				x = 1;
				e.printStackTrace();
			}
			if (x == 0) {
				out.println("Success!");
				response.setHeader("Refresh", "2;URL=blogs");
			} else {
				out.println("Fali!");
				response.setHeader("Refresh", "2;URL=blogs");
			}
		} else {
			response.setHeader("Refresh", "2;URL=blogs");
		}
	}

}
