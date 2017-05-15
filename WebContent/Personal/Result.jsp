<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"
	import="pers.mjw.*,java.io.*,java.sql.*,java.util.*,javax.servlet.*"%>
<%@ taglib uri="http://displaytag.sf.net" prefix="display"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索结果</title>
<link rel="stylesheet" href="all.css" type="text/css" />
</head>
<body>
	<div>
		<jsp:include page="../Head.jsp"></jsp:include>
	</div>
	<div style="height: 480px;">
		<%
			request.setAttribute("MySearchBlog", session.getAttribute("SearchBlog"));
			String searchName = session.getAttribute("SearchName").toString();
			User user = (User) session.getAttribute("User");
			String userName = user.getName();
			String follow = "";
			if (user.getFriend() != null) {
				String[] friendList = user.getFriend();
				if (user.getFriend() != null) {
					List list = Arrays.asList(friendList);
					if (list.contains(searchName)) {
						follow = "follow";
					}
				}
			}
		%>
		<h1><%=searchName%>的动态
		</h1>
		<div align="center">
			<form action="result" method="post">
				<input name="follow" type="checkbox" value="follow"
					<%if (follow.equals("follow")) {%> checked="checked" <%}%>>关注
				<input type="Submit" name="follow" value="确定" />
			</form>
			<display:table name="MySearchBlog">
				<display:column title="头像">
					<img src=<%="..\\Pic\\" + searchName + ".jpg"%> height="50"
						width="50" />
				</display:column>
				<display:column property="name" title="用户" />
				<display:column property="head" title="标题" />
				<display:column property="text" title="内容" />
				<display:column property="date" title="日期" />
				<display:column title="评论"></display:column>
				<display:column title="添加评论">
					<form action="addcomment" method="post">
						<input name="addComment" type="text"><input type="Submit"
							value="添加">
					</form>
				</display:column>
			</display:table>
		</div>
	</div>
	<div>
		<jsp:include page="../Foot.jsp"></jsp:include>
	</div>
</body>
</html>