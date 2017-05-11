package pers.mjw;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchServlet() {
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
		if (request.getParameter("searchName") != null) {
			doPost(request, response);
		} else {
			request.getRequestDispatcher("Personal/Search.jsp").forward(request, response);
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
		PrintWriter out = response.getWriter();
		if (request.getParameter("songname") != null) {
			String songname = request.getParameter("songname");
			String songurl = "http://tingapi.ting.baidu.com/v1/restserver/ting?method=baidu.ting.search.catalogSug&query="
					+ songname;
			session.setAttribute("songurl", songurl);
			out.println("Found");
			response.setHeader("Refresh", "2;URL=songresult");
		} else {
			int i = 1;

			String search = "";
			if (request.getParameter("searchName") != null) {
				search = (String) request.getParameter("searchName");
				session.setAttribute("SearchName", search);
			}

			if (request.getParameter("search") != null) {
				search = request.getParameter("search");
				session.setAttribute("SearchName", search);
			}

			ArrayList<Blog> searchblog = new ArrayList<Blog>();

			String url = "jdbc:mysql://127.0.0.1:3306/sql?useUnicode=true&characterEncoding=utf-8";
			String driver = "com.mysql.jdbc.Driver";
			String sqluser = "root";
			String psw = "";
			try {
				Class.forName(driver);
				Connection con = DriverManager.getConnection(url, sqluser, psw);
				PreparedStatement pstmt = ((java.sql.Connection) con)
						.prepareStatement("SELECT * FROM blog where name ='" + search + "'");
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					Blog searchblog_obj = new Blog((String) rs.getString("name"), (String) rs.getString("head"),
							(String) rs.getString("text"), (String) rs.getString("date"));
					searchblog.add(searchblog_obj);
					i = 0;
				}

			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				i = 1;
				e.printStackTrace();
			}

			if (i == 0) {
				out.println("Success!");
				session.setAttribute("SearchBlog", searchblog);
				response.setHeader("Refresh", "2;URL=result");
			} else {
				out.println("Null!");
				response.setHeader("Refresh", "2;URL=search");
			}
		}
	}

}
