<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>登陆</title>
<link rel="stylesheet" href="all.css" type="text/css" />
</head>
<body>
	<div>
		<jsp:include page="Head.jsp"></jsp:include>
	</div>
	<div style="height: 480px;">
		<h1>登陆</h1>
		<hr style="height: opx; border: none;" />
		<div align="center">
			<form action="login" method="post">
				<%
					String username = "";
					//获取当前站点的所有Cookie
					if (request.getCookies() != null) {
						Cookie[] cookies = request.getCookies();
						if (cookies != null) {
							for (int i = 0; i < cookies.length; i++) {//对cookies中的数据进行遍历，找到用户名、密码的数据
								if ("name".equals(cookies[i].getName())) {
									username = cookies[i].getValue();
								}
							}
						}
					}
				%>
				<br>
				用户名:<input name="name" type="text" value="<%=username%>"><br>
				<br> 密码:<input name="pw" type="password"><br> <br>
				<input name="saveme" type="checkbox" value="save">记住我<br>
				<br> <input type="Submit" value="登陆" style="width:100px;height:40px;"><br>
			</form>
		</div>
	</div>
	<div>
		<jsp:include page="Foot.jsp"></jsp:include>
	</div>
</body>
</body>
</html>