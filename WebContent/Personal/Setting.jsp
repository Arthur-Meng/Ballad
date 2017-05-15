<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.sql.*,pers.mjw.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>设置</title>
<link rel="stylesheet" href="all.css" type="text/css" />
</head>
<body>
	<div>
		<jsp:include page="../Head.jsp"></jsp:include>
	</div>
	<div style="height: 480px;">
		<h1>设置</h1>
		<hr style="height: opx; border: none;" />
		<div align="center">
			<%
				User user = (User) session.getAttribute("User");
				List list = new ArrayList();
				if (user.getHobby() != null)
					list = Arrays.asList(user.getHobby());
			%>
			<form action="setting" enctype="multipart/form-data" method="post">
				用户名:<%=user.getName()%><br>
				<br> 密码:<input name="password" type="password"
					value="<%=user.getPassword()%>"><br>
				<br> 头像: <input type="file" name="upfile"><br />
				<br> 年级:<select name="grade">
					<option value="freshman">freshman</option>
					<option value="sophomoreer">sophomoreer</option>
					<option value="junior">junior</option>
					<option value="senior">senior</option>
				</select><br>
				<br> 性别:<input name="gender" type="radio" value="male"
					<%if (user.getGender().equals("male")) {%> checked="checked" <%}%>>male
				<input name="gender" type="radio" value="female"
					<%if (user.getGender().equals("female")) {%> checked="checked"
					<%}%>>female<br>
				<br> 喜好:<input name="Hobby" type="checkbox" value="CountryFolk"
					<%if (list.contains("CountryFolk")) {%> checked="checked" <%}%>>Country
				Folk <input name="Hobby" type="checkbox" value="UrbanFolk"
					<%if (list.contains("UrbanFolk")) {%> checked="checked" <%}%>>Urban
				Folk <input name="Hobby" type="checkbox" value="NewFolk"
					<%if (list.contains("NewFolk")) {%> checked="checked" <%}%>>New
				Folk <input name="Hobby" type="checkbox" value="FolkRock"
					<%if (list.contains("FolkRock")) {%> checked="checked" <%}%>>Folk
				Rock <br>
				<br> <input type="Submit" value="提交" style="width:100px;height:40px;"><br>
			</form>
		</div>
	</div>
	<div>
		<jsp:include page="../Foot.jsp"></jsp:include>
	</div>
</body>
</html>