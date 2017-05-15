<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>首页</title>
<link rel="stylesheet" href="all.css" type="text/css" />
</head>
<body>
	<div>
		<jsp:include page="Head.jsp"></jsp:include>
	</div>
	<div style="height: 480px;">
		<h1>首页</h1>
		<hr style="height: opx; border: none;" />
		<div align="center">
			<h2 style="align: center;">
				<a href="Music.jsp">顺便听听</a>
			</h2>
		</div>
	</div>
	<jsp:include page="Foot.jsp"></jsp:include>
</body>
</html>