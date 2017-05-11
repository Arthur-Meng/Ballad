package pers.mjw;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("Login.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		int x = 1;
		User user = new User();
		user.name = request.getParameter("name");
		user.password = request.getParameter("pw");
		String ifsave = "";
		if (request.getParameter("saveme") != null) {
			ifsave = request.getParameter("saveme");
		}

		String url = "jdbc:mysql://127.0.0.1:3306/sql?useUnicode=true&characterEncoding=utf-8";
		String driver = "com.mysql.jdbc.Driver";
		String sqluser = "root";
		String psw = "";

		PrintWriter out = response.getWriter();
		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, sqluser, psw);
			PreparedStatement pstmt = ((java.sql.Connection) con).prepareStatement(
					"SELECT * FROM user where name='" + user.name + "' and  password='" + user.password + "'");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				user.setGrade(rs.getString("grade"));
				user.setGender(rs.getString("gender"));
				user.setHobbyall(rs.getString("hobby"));
				user.setFriendall(rs.getString("friend"));
				x = 0;
			}

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("ERROE");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			x = 1;
			out.println("SQL!");
			e.printStackTrace();
		}

		if (ifsave.equals("save")) {
			Cookie cookie1 = new Cookie("name", user.name);
			cookie1.setMaxAge(24 * 60 * 60 * 30);
			response.addCookie(cookie1);
		}

		if (x == 1) {
			out.println("Fail!");
			response.setHeader("Refresh", "2;URL=login");
		} else {
			out.println("Success!");
			response.setHeader("Refresh", "2;URL=personal");
			HttpSession session = request.getSession();
			session.setAttribute("IfLogin", "Yes");
			session.setAttribute("User", user);
		}
	}

}