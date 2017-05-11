<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="pers.mjw.*,java.io.File"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>WebHead</title>
<style type="text/css">
h2 {
	font-size: 100%;
	border: solid 1px red;
	display: inline;
	zoom: 1;
	padding: 5px;
	margin: 5px;
}
</style>
</head>
<body>
	<table>
		<tr>
			<td>
				<h2>Ballad Club-just to enjoy the beauty of ballad&nbsp&nbsp&nbsp&nbsp&nbsp</h2>
			</td>
			<%
				if (session.getAttribute("IfLogin") == null || !session.getAttribute("IfLogin").equals("Yes")) {
			%>
			<td><h2>
					<a href="index">首页</a>
				</h2></td>
			<td><h2>
					<a href="signup">注册</a>
				</h2></td>
			<td><h2>
					<a href="login">登陆</a>
				</h2></td>

			<%
				} else {

					if (session.getAttribute("User") != null) {
						User user = (User) session.getAttribute("User");
						String userName = user.getName();
			%>
			<td><img src=<%="..\\Pic\\" + userName + ".jpg"%> height="50"
				width="50" /></td>
			<td><h2>
					Hello
					<%=userName%></h2></td>
			<td><h2>
					<a href="index">首页</a>
				</h2></td>
			<td><h2>
					<a href="personal">个人主页</a>
				</h2>
			<td>
			<td><h2>
					<a href="blogs">动态</a>
				</h2>
			<td>
			<td><h2>
					<a href="newblog">新建动态</a>
				</h2>
			<td>
			<td><h2>
					<a href="friend">好友</a>
				</h2>
			<td>
			<td><h2>
					<a href="search">搜索</a>
				</h2>
			<td>
			<td><h2>
					<a href="setting">设置</a>
				</h2>
			<td>
			<td><h2>
					<a href="logout">登出</a>
				</h2>
			<td>
				<%
					}
					}
				%>
			
		</tr>
	</table>
	<hr>
</body>
</html>