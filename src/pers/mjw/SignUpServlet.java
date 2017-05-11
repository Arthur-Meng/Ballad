
package pers.mjw;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
 * Servlet implementation class SignUpServlet
 */
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String savepath;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SignUpServlet() {
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
		// 站内跳转
		// response.sendRedirect("SignUp.jsp");
		request.getRequestDispatcher("SignUp.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int x = 0;// 特殊情况的标志
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();

		// Create a new file upload handler
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		// Parse the request
		List<FileItem> items = null;
		String username = "";
		String password = "";
		String grade = "";
		String gender = "";
		String hobbyall = "";
		/*
		 * File file=null; FileInputStream is=null; InputStream fis=null;
		 */

		try {
			items = upload.parseRequest(request);
		} catch (FileUploadException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		Iterator<FileItem> iter = items.iterator();

		while (iter.hasNext()) {
			FileItem item = iter.next();
			if (item.isFormField()) {
				String name = item.getFieldName();
				String value = new String(item.getString("iso-8859-1").getBytes("iso-8859-1"), "utf-8");
				if (name.equals("name")) {
					if (value.equals("")) {
						out.println("Null!");
						response.setHeader("Refresh", "2;URL=signup");
					}
					username = value;
					System.out.println(username);
				}
				if (name.equals("password")) {
					if (value.equals("")) {
						out.println("Null!");
						response.setHeader("Refresh", "2;URL=signup");
					}
					password = value;
					System.out.println(value);
				}
				if (name.equals("grade")) {
					grade = value;
					System.out.println(value);
				}
				if (name.equals("gender")) {
					gender = value;
					System.out.println(value);
				}
				if (name.equals("Hobby")) {
					hobbyall += "-" + value;
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
					 * if(!fileName.equals("")){ file = new File(fileName); is =
					 * new FileInputStream(file); fis=(InputStream)is; }
					 */

				}
			}
		}

		String url = "jdbc:mysql://127.0.0.1:3306/sql?useUnicode=true&characterEncoding=utf-8";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String psw = "";
		String strsql = "insert into user(name,password,grade,gender,hobby) values(?,?,?,?,?)";

		try {
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, user, psw);
			PreparedStatement pstmt = ((java.sql.Connection) con).prepareStatement(strsql);
			pstmt.setString(1, username);
			pstmt.setString(2, password);
			pstmt.setString(3, grade);
			pstmt.setString(4, gender);
			pstmt.setString(5, hobbyall);
			// if(!(fis==null)&&file!=null){pstmt.setBinaryStream(6,fis,file.length());System.out.println("Pic");}
			// else pstmt.setBinaryStream(6,null);
			pstmt.executeUpdate();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			out.println("ERROR");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			// 数据库错误，如已经有一个一样的name
			x = 1;
		}

		if (x == 1) {
			out.println("User has existed!");
			response.setHeader("Refresh", "2;URL=signup");
		} else {
			HttpSession session = request.getSession();
			session.setAttribute("AllSignUp", new VisitListener());
			out.println("Success!");
			response.setHeader("Refresh", "2;URL=login");

		}

	}

}
