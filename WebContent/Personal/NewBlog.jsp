<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="ckeditor/ckeditor.js"></script>
<title>新建动态</title>
</head>
<body>
	<div>
		<jsp:include page="../Head.jsp"></jsp:include>
	</div>
	<h1>新建动态</h1>
	<hr>
	<form action="newblog" enctype="multipart/form-data" method="post">
		动态标题: <input type="text" name="head"><br />
		<br /> 图片: <input type="file" name="upfile"><br />
		<br>
		<textarea id="text1" name="text1" rows="10">
</textarea>
		<br /> <input type="Submit" value="提交"><br>
	</form>
	<script type="text/javascript">
		CKEDITOR.replace('text1');
	</script>

	<div>
		<jsp:include page="../Foot.jsp"></jsp:include>
	</div>
</body>
</html>