<%@page import="com.dao.HistoryDao"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.entitiy.WifiEntity"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="com.dao.WifiDao"%>
<%
	double curLnt = Double.parseDouble(request.getParameter("lnt"));
	double curLat = Double.parseDouble(request.getParameter("lat"));
	new HistoryDao().insertHistoryData(curLat, curLnt);
	ResultSet rs = new WifiDao().selectNearWifiData(curLat, curLnt);
	List<WifiEntity> wifiList = new ArrayList<WifiEntity>();
	HashMap<Integer, Double> distanceMap = new HashMap<Integer, Double>();
	WifiEntity wifi;
	while (rs.next()){
		wifi = new WifiEntity();		
		wifi.setId(Integer.valueOf(rs.getString("id")));
		wifi.setWrdofc(rs.getString("wrdofc"));
		wifi.setMgrNo(rs.getString("mgr_no"));
		wifi.setWifiName(rs.getString("wifi_name"));
		wifi.setStreetAddress(rs.getString("street_address"));
		wifi.setDetailAddress(rs.getString("detail_address"));
		wifi.setInstallFloor(rs.getString("install_floor"));
		wifi.setInstallType(rs.getString("install_type"));
		wifi.setInstallAgency(rs.getString("install_agency"));
		wifi.setInstallYear(rs.getString("install_year"));
		wifi.setInOut(rs.getString("inout_type"));
		wifi.setServiceType(rs.getString("service_type"));
		wifi.setNetType(rs.getString("net_type"));
		wifi.setConnectEnviron(rs.getString("connect_environ"));
		wifi.setLat(Double.parseDouble(rs.getString("lat")));
		wifi.setLnt(Double.parseDouble(rs.getString("lnt")));
		wifi.setWorkDate(rs.getString("work_date"));
		distanceMap.put(wifi.getId(), Double.parseDouble(rs.getString("distance")));
		wifiList.add(wifi);
	}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>와이파이 정보 구하기</h2>
	<p>
		<a href="index.jsp">홈 | <a> <a href="history.jsp"> 위치 히스토리 | </a> 
		<a href="dataupdate.jsp">OPEN API 정보 가져오기</a>
	</p>
	
	<p>
		<form action="nearwifi.jsp" method="post">
			<label>LAT : <input type="text", name="lat"> </input>, </label>
			<label>LNT : <input type="text", name="lnt"> </input> </label>
			<input type="submit" value="근처 WIFI 정보 보기"></td>
		</form>
	</p>
	현재 위도 : <%= curLat %>
	현재 경도 : <%= curLnt %>
	
	<table border="17" width="90%" cellspacing="1">
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
    <%
		for(WifiEntity wifiObj : wifiList){
	%>
		<tr>
			<td><%=String.format("%.4f",distanceMap.get(wifiObj.getId()))%></td>
			<td><%=wifiObj.getMgrNo()%></td>
			<td><%=wifiObj.getWrdofc()%></td>
			<td><%=wifiObj.getWifiName()%></td>
			<td><%=wifiObj.getStreetAddress()%></td>
			<td><%=wifiObj.getDetailAddress()%></td>
			<td><%=wifiObj.getInstallFloor()%></td>
			<td><%=wifiObj.getInstallType()%></td>
			<td><%=wifiObj.getInstallAgency()%></td>
			<td><%=wifiObj.getServiceType()%></td>
			<td><%=wifiObj.getNetType()%></td>
			<td><%=wifiObj.getInstallYear()%></td>
			<td><%=wifiObj.getInOut()%></td>
			<td><%=wifiObj.getConnectEnviron()%></td>
			<td><%=wifiObj.getLat()%></td>
			<td><%=wifiObj.getLnt()%></td>
			<td><%=wifiObj.getWorkDate()%></td>
		</tr>
	<%
		}	
	%>   
    </table>
</body>
</html>
