package pers.mjw;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import pers.mjw.listener.VisitListener;

/**
 * Servlet implementation class SettingServlet
 */
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String savepath;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SettingServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		savepath = config.getServletContext().getRealPath("/");
		// Create a factory for disk-based file items

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.getRequestDispatcher("Personal/Setting.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int x = 0;// 特殊情况的标志
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		// Create a new file upload handler
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		// Parse the request
		List<FileItem> items;
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("User");
		String username = user.getName();
		String password = "";
		String grade = "";
		String gender = "";
		String hobbyall = "";
		try {
			items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = item.getString();
					if (name.equals("password")) {
						if (value.equals("")) {
							out.println("Null!");
							response.setHeader("Refresh", "2;URL=seeting");
						}
						password = value;
						user.setPassword(password);
						System.out.println(value);
					}
					if (name.equals("grade")) {
						grade = value;
						user.setGrade(grade);
						System.out.println(value);
					}
					if (name.equals("gender")) {
						gender = value;
						user.setGender(gender);
						System.out.println(value);
					}
					if (name.equals("Hobby")) {
						hobbyall += "-" + value;
						user.setHobbyall(hobbyall);
						System.out.println(value);
					}
				} else {
					if (item.getName() != null) {
						String fieldName = item.getFieldName();
						String fileName = item.getName();
						String contentType = item.getContentType();
						boolean isInMemory = item.isInMemory();
						long sizeInBytes = item.getSize();
						int index = fileName.lastIndexOf("\\");
						if (index != -1) {
							fileName = fileName.substring(index + 1);
						}
						String myfilename = "D:\\TomCat\\apache-tomcat-8.5.11\\webapps\\Pic\\" + username + ".jpg";
						System.out.println(myfilename);
						File uploadedFile = new File(myfilename);
						try {
							item.write(uploadedFile);
						} catch (Exception e) {
							e.printStackTrace();
						}
						/*
						 * if(!fileName.equals("")){ file = new File(fileName);
						 * is = new FileInputStream(file); fis=(InputStream)is;
						 * }
						 */
					}
					/*
					 * if(!fileName.equals("")){ file = new File(fileName); is =
					 * new FileInputStream(file); fis=(InputStream)is; }
					 */
				}

			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		String url = "jdbc:mysql://127.0.0.1:3306/sql?useUnicode=true&characterEncoding=utf-8";
		String driver = "com.mysql.jdbc.Driver";
		String sqluser = "root";
		String psw = "";
		String strsql = "update user set password=?,grade=?,gender=?,hobby=? where name=?";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, sqluser, psw);
			System.out.println(con);
			PreparedStatement pstmt = ((java.sql.Connection) con).prepareStatement(strsql);
			pstmt.setString(1, password);
			pstmt.setString(2, grade);
			pstmt.setString(3, gender);
			pstmt.setString(4, hobbyall);
			pstmt.setString(5, username);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			out.println("ERROR1");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// 数据库错误
			e.printStackTrace();
			out.println("ERROR2");
			x = 1;
		}

		if (x == 1) {
			out.println("Error!");
			response.setHeader("Refresh", "2;URL=setting");
		} else {
			out.println("Success!");
			response.setHeader("Refresh", "2;URL=setting");
		}

	}

}
