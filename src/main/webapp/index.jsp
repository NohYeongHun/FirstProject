<%@page import="java.sql.ResultSet"%>
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
	<h2>와이파이 정보 구하기</h2>
	<p>
		<a href="index.jsp">홈 | </a>
		<a href="history.jsp"> 위치 히스토리 | </a>
		<a href="dataupdate.jsp">OPEN API 정보 가져오기</a>
	</p>
	
	<p>
		<form action="nearwifi.jsp" method="post">
			<label>LAT : <input type="text", name="lat"> </input> </label>
			<label>LNT : <input type="text", name="lnt"> </input>, </label>
			<input type="submit" value="근처 WIFI 정보 보기"></td>
		</form>
	</p>
		
	<table border="17" width="90%" cellspacing="2">
       	<tr>
            <td>거리(km)</td>
            <td>관리번호</td>
            <td>자치구</td>
            <td>와이파이명</td>
            <td>도로명주소</td>
            <td>상세주소</td>
            <td>설치위치(층)</td>
            <td>설치유형</td>
            <td>설치기관</td>
            <td>서비스구분</td>
            <td>망종류</td>
            <td>설치년도</td>
            <td>실내외구분</td>
            <td>WIFI접속환경</td>
            <td>X좌표</td>
            <td>Y좌표</td>
            <td>작업일자</td>          
        </tr>        
    </table>
</body>
</html>