<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.dao.WifiDao" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<h2>데이터가 업데이트 되었습니다.</h2>
<%
new WifiDao().insertWifiData();
%>

<a href="index.jsp">홈으로 돌아가기</a>
</body>
</html>