package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HistoryDao {
	String driver = "org.mariadb.jdbc.Driver";
    Connection con;
    PreparedStatement stmt;
    ResultSet rs;

    public HistoryDao() {
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
    
    public ResultSet selectHistoryData() throws SQLException {
    	
    	String query = String.format("select * from search_history;");
		stmt = con.prepareStatement(query);
		rs = stmt.executeQuery();
		return rs;
    }
    
    public void deleteHistory(int id) throws SQLException {
    	String query = String.format("DELETE FROM search_history\r\n"
    			+ "\r\n"
    			+ "WHERE id = '%d';\r\n"
    			+ "\r\n"
    			+ " ", id);
    	stmt = con.prepareStatement(query);
		rs = stmt.executeQuery();
    }
    
    public void insertHistoryData(double lat, double lnt) {
    	String query = String.format(
    			"INSERT INTO SEARCH_HISTORY(lat, lnt, search_date)\r\n"
    			+ "VALUES(%f, %f, CURRENT_TIMESTAMP);"
    					, lat, lnt);
		try {
			stmt = con.prepareStatement(query);
			stmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    }
}
