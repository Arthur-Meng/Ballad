<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>搜索</title>
</head>
<body>
<div>
<jsp:include page="../Head.jsp"></jsp:include>
</div>
<h1>搜索</h1>
<hr>
<br/><br/>
<form action="search" method="post">
用户名：<input type="text" name="search">
<input type="Submit" value="找人"><br>
</form>
<br/><br/>
<form action="search" method="post">
歌&nbsp&nbsp&nbsp&nbsp名：<input name="songname" id="name" type="text">
<input type="Submit" value="找歌"><br>
</form>
<br/><br/>
<div>
<jsp:include page="../Foot.jsp"></jsp:include>
</div>
</body>
</html>