<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="pers.mjw.*,pers.mjw.listener.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Count</title>
</head>
<body>
<div>
<jsp:include page="Head.jsp"></jsp:include>
</div>
<h1>网站数据</h1>
<hr>
<h1>注册人数:
<%
VisitListener  allSignUp=new VisitListener();
out.println(allSignUp.getAllSignUp());
%>
</h1>
<div>
<jsp:include page="Foot.jsp"></jsp:include>
</div>
</body>
</html>