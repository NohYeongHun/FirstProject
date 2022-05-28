package com.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.dto.WifiDto;
import com.dto.WifiList;

public class WifiDao {
	String driver = "org.mariadb.jdbc.Driver";
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;

    public WifiDao() {
         try {
            Class.forName(driver);
            con = DriverManager.getConnection(
                    "jdbc:mariadb://127.0.0.1:80/nyh",
                    "root",
                    "dudgns12");
            
            if( con != null ) {
                System.out.println("DB 접속 성공");
            }
            
        } catch (ClassNotFoundException e) { 
            System.out.println("드라이버 로드 실패");
        } catch (SQLException e) {
            System.out.println("DB 접속 실패");
            e.printStackTrace();
        }
    }
    
    public void insertWifiForm(WifiDto wifiObj, int start, int length) {    	
    	String newLine = System.lineSeparator();
    	String insertFormat = "REPLACE INTO WIFI_BOARD("
    			+ "id, mgr_no, wrdofc, wifi_name, street_address, detail_address, "
    			+ "install_floor, install_type, install_agency, service_type,"
    			+ " net_type, install_year, inout_type, connect_environ, lat, "
    			+ "lnt, work_date)\nVALUES";
    	StringBuilder sb = new StringBuilder(insertFormat); 
    	List<WifiList> row = wifiObj.getTbPublicWifiInfo().getRow();
    	
		for(int i = 0; i< length; i++) {
			int id = start + i;
			String 관리번호 =  row.get(i).getX_SWIFI_MGR_NO();
			String 자치구 =  row.get(i).getX_SWIFI_WRDOFC();
			String 와이파이명 = row.get(i).getX_SWIFI_MAIN_NM();
			String 도로명주소 = row.get(i).getX_SWIFI_ADRES1();
			String 상세주소 = row.get(i).getX_SWIFI_ADRES2();
			// NULL 가능
			String 층설치위치 = row.get(i).getX_SWIFI_INSTL_FLOOR();
			String 설치유형 = row.get(i).getX_SWIFI_INSTL_TY();
			String 설치기관 = row.get(i).getX_SWIFI_INSTL_MBY();
			String 서비스구분 = row.get(i).getX_SWIFI_SVC_SE();
			String 망종류 = row.get(i).getX_SWIFI_CMCWR();
			String 설치년도 = row.get(i).getX_SWIFI_CNSTC_YEAR();
			String 실내외구분 = row.get(i).getX_SWIFI_INOUT_DOOR();
			// NULL 가능
			String wifi접속환경 = row.get(i).getX_SWIFI_REMARS3();
			String x좌표 = row.get(i).getLNT();
			String y좌표 = row.get(i).getLAT();
			String 작업일자 = row.get(i).getWORK_DTTM();
			
			if (층설치위치.equals("")) {
				층설치위치 = "-";
			}
			
			if (wifi접속환경.equals("")) {
				wifi접속환경 = "-";
			}
			
			String insertValue = String.format("(\"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\", \"%s\")",
					id, 관리번호, 자치구, 와이파이명, 도로명주소, 상세주소, 층설치위치, 설치유형, 설치기관
					, 서비스구분, 망종류, 설치년도, 실내외구분, wifi접속환경, x좌표, y좌표, 작업일자);
			sb.append(newLine);
			sb.append(insertValue + ",\n");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append(";");
		String query = sb.toString();
		try {
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void insertWifiData() {
    	WifiDto wifiObj;
    	int start = 1;
		int end = 0;
		
		try {
			wifiObj = new WifiDto().createWifiObj(1, 1);
			int totalPage = new WifiDto().getTotalPage(wifiObj);
			List<Integer> list = new WifiDto().getPageList(totalPage);
			for(Integer val : list) {
				end += val;
				wifiObj = new WifiDto().createWifiObj(start, end);
				new WifiDao().insertWifiForm(wifiObj, start, val);
				start += val;
			}
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
    }
    
    public ResultSet selectNearWifiData(double lat, double lnt) throws SQLException {
    	
    	String query = String.format("SELECT *,	(6371*acos(cos(radians(%s))*cos(radians(lat))*cos(radians(lnt)\r\n"
    			+ "-radians(%s))+sin(radians(%s))*sin(radians(lat))))\r\n"
    			+ "AS distance\r\n"
    			+ "FROM WIFI_BOARD\r\n"
    			+ "ORDER BY distance\r\n"
    			+ "LIMIT 0,20;", lat, lnt, lat);
		stmt = con.prepareStatement(query);
		rs = stmt.executeQuery();
		
		return rs;
    }
       
}
