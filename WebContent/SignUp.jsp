<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
</head>
<body>
<div>
<jsp:include page="Head.jsp"></jsp:include>
</div>
<h1>注册</h1>
<hr>
<form action="signup" enctype="multipart/form-data" method="post">
	*用户名:<input name="name" type="text"><br><br>
	*密码:<input name="password" type="password"><br><br>
	头像: <input type="file" name="upfile"><br/><br>
	年级:<select name="grade">
	<option value="freshman">freshman</option>
	<option value="sophomoreer">sophomoreer</option>
	<option value="junior">junior</option>
	<option value="senior">senior</option>
	</select><br><br>
	性别:<input name="gender" type="radio" value="male">male
		   <input name="gender" type="radio" value="female">female<br><br>
	喜好:<input name="Hobby" type="checkbox" value="CountryFolk">Country Folk
		  <input name="Hobby" type="checkbox" value="UrbanFolk">Urban Folk 
		  <input name="Hobby" type="checkbox" value="NewFolk">New Folk 
		  <input name="Hobby" type="checkbox" value="FolkRock">Folk Rock <br><br>
		  <input type="Submit" value="注册"><br>
</form>
<div>
<jsp:include page="Foot.jsp"></jsp:include>
</div>
</body>
</html>