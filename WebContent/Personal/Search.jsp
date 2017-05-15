<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索</title>
<link rel="stylesheet" href="all.css" type="text/css" />
</head>
<body>
	<div>
		<jsp:include page="../Head.jsp"></jsp:include>
	</div>
	<div style="height: 480px;">
		<h1>搜索</h1>
		<hr style="height: opx; border: none;" />
		<div align="center">
			<br />
			<br />
			<form action="search" method="post">
				用户名：<input type="text" name="search"> <input type="Submit"
					value="找人" style="width:80px;height:30px;"><br>
			</form>
			<br />
			<br />
			<form action="search" method="post">
				歌名(歌手)：<input name="songname" id="name" type="text">
				<input type="Submit" value="找歌" style="width:80px;height:30px;"><br>
			</form>
			<br />
			<br />
		</div>
	</div>
	<div>
		<jsp:include page="../Foot.jsp"></jsp:include>
	</div>
</body>
</html>