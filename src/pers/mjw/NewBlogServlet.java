package pers.mjw;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

/**
 * Servlet implementation class NewBlogServlet
 */
public class NewBlogServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String savepath;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public NewBlogServlet() {
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
		request.getRequestDispatcher("Personal/NewBlog.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		int x = 0;
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");
		HttpSession session = request.getSession();
		User user1 = (User) session.getAttribute("User");
		String username = user1.getName();

		String head = "";
		String text = "";
		PrintWriter out = response.getWriter();

		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setHeaderEncoding("utf-8");
		List<FileItem> items;
		String date = "";
		Timestamp ts = new Timestamp(System.currentTimeMillis());
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");

		date = sdf.format(ts);

		try {
			items = upload.parseRequest(request);
			Iterator<FileItem> iter = items.iterator();
			while (iter.hasNext()) {
				FileItem item = iter.next();
				if (item.isFormField()) {
					String name = item.getFieldName();
					String value = new String(item.getString("iso-8859-1").getBytes("iso-8859-1"),"utf-8");
					if (name.equals("head")) {
						if (value.equals("")) {
							out.println("Null!");
							response.setHeader("Refresh", "2;URL=newblog");
						}
						head = value;
						System.out.println(value);
					}
					if (name.equals("text1")) {
						if (value.equals("")) {
							out.println("Null!");
							response.setHeader("Refresh", "2;URL=newblog");
						}
						text = value;
						
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
						String myfilename = "D:\\TomCat\\apache-tomcat-8.5.11\\webapps\\Pic\\" + username + "-" + date
								+ ".jpg";
						System.out.println(myfilename);

						File uploadedFile = new File(myfilename);
						try {
							item.write(uploadedFile);
						} catch (Exception e) {
							e.printStackTrace();
						}

					}

				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		Connection con;
		PreparedStatement pstmt;
		ResultSet rs;

		String url = "jdbc:mysql://127.0.0.1:3306/sql?useUnicode=true&characterEncoding=utf-8";
		String driver = "com.mysql.jdbc.Driver";
		String user = "root";
		String psw = "";

		String strsql = "insert into blog(name,head,text,date) values(?,?,?,?)";

		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, psw);
			System.out.println(con);
			pstmt = ((java.sql.Connection) con).prepareStatement(strsql);
			pstmt.setString(1, username);
			pstmt.setString(2, head);
			pstmt.setString(3, text);
			pstmt.setString(4, date);
			pstmt.executeUpdate();

		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			out.println("ERROR");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			x = 1;

		}
		if (x == 0) {
			out.println("Success!");
			response.setHeader("Refresh", "2;URL=personal");
		} else {
			out.println("Fali!");
			response.setHeader("Refresh", "2;URL=newblog");
		}
	}

}
