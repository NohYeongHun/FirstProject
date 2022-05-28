<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.entitiy.HistoryEntity"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.dao.HistoryDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		ResultSet rs = new HistoryDao().selectHistoryData();
		HistoryEntity history;
		List<HistoryEntity> historyList = new ArrayList<HistoryEntity>();
		 
		while (rs.next()){
			history = new HistoryEntity();
			history.setId(Integer.valueOf(rs.getString("id")));
			history.setLat(Double.parseDouble(rs.getString("lat")));
			history.setLnt(Double.parseDouble(rs.getString("lnt")));
			history.setSearchDate(rs.getString("search_date"));
			historyList.add(history);
		}
	%>
	<h2>와이파이 정보 구하기</h2>
	<p>	
		<a href="index.jsp">홈 | <a> 
		<a href="history.jsp"> 위치 히스토리 | </a>
		<a href="dataupdate.jsp">OPEN API 정보 가져오기</a>
	</p>
	
	
	<table border="5" width="90%" cellspacing="2">
       	<tr>
            <td>ID</td>
            <td>X좌표</td>
            <td>Y좌표</td>
            <td>조회일자</td>
            <td>비고</td>          
        </tr>
        <%
        	for(HistoryEntity historyObj : historyList){
        %>
        <tr>
			<td><%=historyObj.getId() %></td>        
			<td><%=historyObj.getLat() %></td>
			<td><%=historyObj.getLnt() %></td>
			<td><%=historyObj.getSearchDate()%></td>
			<td>
				<form action="historyDelete.jsp">
					<input type="hidden" name="id" value="<%=historyObj.getId()%>">
					<input type="submit" value="삭제">				
				</form>
			</td>
        </tr>
        <%
        	}        
        %>
    </table>
</body>
</html>