<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="pers.mjw.*,java.io.*,java.sql.*,java.util.*,javax.servlet.*"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="all.css" type="text/css" />
<style type="text/css">
.header {
	display: none;
}
</style>
<title>个人中心</title>
</head>
<body>
	<div>
		<jsp:include page="../Head.jsp"></jsp:include>
	</div>
	<div>
		<%
			User user1 = (User) session.getAttribute("User");
			String username = user1.getName();

			String name = "";
			String head = "";
			String text = "";
			String date = "";
			String blogDate = "";
			String commentall = "";
			ArrayList<Blog> blog = new ArrayList<Blog>();
			int i = 0;
			int k = 0;
			String url = "jdbc:mysql://127.0.0.1:3306/sql?useUnicode=true&characterEncoding=utf-8";
			String driver = "com.mysql.jdbc.Driver";
			String sqluser = "root";
			String psw = "";
			try {
				Class.forName(driver);
				Connection con = DriverManager.getConnection(url, sqluser, psw);
				PreparedStatement pstmt = ((java.sql.Connection) con)
						.prepareStatement("SELECT * FROM blog where name ='" + username + "'");
				ResultSet rs = pstmt.executeQuery();
				while (rs.next()) {
					name = (String) rs.getString("name");
					head = (String) rs.getString("head");
					text = (String) rs.getString("text");
					date = (String) rs.getString("date");
					PreparedStatement pstmt1 = ((java.sql.Connection) con).prepareStatement(
							"SELECT * FROM comment where blogname ='" + name + "' and blogdate ='" + date + "'");
					ResultSet rs1 = pstmt1.executeQuery();
					while (rs1.next()) {
						String commentuser = (String) rs1.getString("commentuser");
						String comment = (String) rs1.getString("comment");
						commentall += commentuser + ":" + comment + "  ";
					}
					Blog blog_obj = new Blog(name, head, text, date, commentall);
					commentall = "";
					blog.add(blog_obj);
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
				request.setAttribute("Blog", blog);
			}
		%>

		<h1>个人中心</h1>
		<div align="center">
			<display:table name="Blog" id="showblog" cellpadding="30px">
				<display:column title="(showblog_rowNum)" style="display:none"
					headerClass="header">
					<%
						k = (int) pageContext.getAttribute("showblog_rowNum");
					%>
				</display:column>
				<display:column title="头像">
					<img src=<%="..\\Pic\\" + username + ".jpg"%> height="100"
						width="100" />
				</display:column>
				<display:column property="name" title="用户" />
				<display:column property="head" title="标题" />
				<display:column title="图片">
					<img
						src=<%="..\\Pic\\" + username + "-" + blog.get(k - 1).getDate() + ".jpg"%>
						height="100" width="100" />
				</display:column>
				<display:column property="text" title="内容" />
				<display:column property="date" title="日期" />
				<display:column property="commentall" title="评论"></display:column>

			</display:table>
		</div>
	</div>
	<div>
		<jsp:include page="../Foot.jsp"></jsp:include>
	</div>
</body>
</html>