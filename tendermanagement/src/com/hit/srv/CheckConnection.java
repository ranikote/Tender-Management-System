package com.hit.srv;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

import com.hit.utility.DBUtil;
@WebServlet("/CheckConnection")
public class CheckConnection {
	public static void main(String args[]){
		Connection con = DBUtil.provideConnection();
		
		if(con==null){
			System.out.println("No Connection available");
		}
		else{
			System.out.println("Connected");
			
			PreparedStatement ps = null;
			ResultSet rs = null;
			try{
            	ps = con.prepareStatement("select * from notice");
            	rs = ps.executeQuery();
            	String noticeTitle = null;
            	String noticeInfo = null;
            	while(rs.next()){
            		noticeTitle = rs.getString("title");
            		noticeInfo = rs.getString("info");
            		System.out.println(noticeTitle+"         "+noticeInfo);
            		System.out.println("Printed");
            	}
            }
            catch(Exception e){
            	e.printStackTrace();
            }
			finally{
				
				DBUtil.closeConnection(ps);
				DBUtil.closeConnection(rs);
			
			}
		}
	}
}
