<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="pers.mjw.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>好友</title>
</head>

<body>
	<div>
		<jsp:include page="../Head.jsp"></jsp:include>
	</div>
	<h1>好友</h1>
	<hr>
	<%
		User user = new User();
		if ((User) session.getAttribute("User") != null)
			user = (User) session.getAttribute("User");
		UserDoDAO userDao = new UserDoDAO();
		if (user.getFriend() != null) {
			String[] allSearch = user.getFriend();
			for (int i = 0; i < allSearch.length; i++) {
				String searchName = allSearch[i];
				if (user.Iffrined(searchName)) {
					User searchUser = userDao.find(searchName);
	%><table>
		<%
			if (searchUser.Iffrined(user.getName())) {
		%><tr>
			<td><img
				src=<%="..\\Pic\\" + searchName + ".jpg"%>
				height="50" width="50" /></td>
			<td><h3 >
					<a href="search?searchName=<%=searchName%>"> &nbsp&nbsp&nbsp<%=searchName%>&nbsp&nbsp&nbsp
					</a>
				</h3></td>
		</tr>
		<%
			}
		%>
	</table>
	<%
		}
			}

		}
	%>
	<script type="text/javascript" src="https://cdn.goeasy.io/goeasy.js"></script>

	<script type="text/javascript">
		var goEasy = new GoEasy({
			appkey: "BC-bf72dea123c24a54a1b4c737bafbcea4"
			 });
			goEasy.subscribe({
			channel: '<%=user.getName().toString()%>',
			onMessage: function (message) {
			alert("您被" + message.content+"关注了！");
			}
			});

	</script>
	<h1>已关注</h1>
	<hr>
	<%
		if (user.getFriend() != null) {
			String[] allSearch = user.getFriend();
			for (int i = 1; i < allSearch.length; i++) {
				
				String searchName = allSearch[i];
				
				if(searchName.equals("-")){}
				else{
				
				%><table><%
					%><tr>
					<td><img src=<%="..\\Pic\\" + searchName + ".jpg"%>
					height="50" width="50" /></td>
					<td><h3><a href="search?searchName=<%=searchName%>">
					&nbsp&nbsp&nbsp<%=searchName%>&nbsp&nbsp&nbsp</a></h3></td>
					</tr>
					<%
				%></table><%
				}
			}
		}
	%>
	<div>
		<jsp:include page="../Foot.jsp"></jsp:include>
	</div>
</body>
</html>