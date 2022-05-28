<%@page import="com.dao.HistoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<h2>정보가 정상적으로 삭제되었습니다.</h2>
	<p>	 
		<a href="history.jsp"> 뒤로 가기</a>
	</p>
	
<%	
	int id = Integer.valueOf(request.getParameter("id"));
	new HistoryDao().deleteHistory(id);
%>

</body>
</html>