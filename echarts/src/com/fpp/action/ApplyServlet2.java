package com.fpp.action;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.util.JAXBSource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fpp.util.JsonUtils;
import com.fpp.util.MyDB;

public class ApplyServlet2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
//		String[] categories = {"鞋", "衬衫", "外套", "牛仔裤"};  
//	    Integer[] values = {80, 50, 75, 100};  
	    
//	    String[] hours = {"12a", "1a"};
//	    String[] days = {"Saturday", "Friday", "Thursday"};

		String code=req.getParameter("code");
		
		JSONArray hours=new JSONArray();
		
        JSONArray days=new JSONArray();		
        JSONArray data=new JSONArray();
        
        
        MyDB md=new MyDB();
		try {
		Connection conn=md.getConnection();
		Statement stmt;
		stmt = conn.createStatement();
		//用map存储日期和编号对应关系，以便后期进行用日期替换成坐标
		HashMap<String, Integer> mapdate = new HashMap<String, Integer>();
		HashMap<String, Integer> mapcode = new HashMap<String, Integer>();
		String sql1="select  distinct dbms_lob.substr(a.\"date\") \"date\" from myrzrqye a where  dbms_lob.substr(a.\"stockCode\")='"+code+"' order by 1 asc";
		ResultSet rstdate=stmt.executeQuery(sql1);
		int i =0;
		while(rstdate.next())
		{
		 
			String date=rstdate.getString("date");	 
			mapdate.put(date,i);	
			hours.add(date);		
			i++;
		}
		
			
		mapcode.put(code,0);
		days.add(code);
//		ResultSet rstcode=stmt.executeQuery("select  distinct dbms_lob.substr(a.\"stockCode\")  \"stockCode\" from myrzrqye a where dbms_lob.substr(a.\"stockCode\")='"+code+"' order by 1 asc");
//		int j =0;
//		while(rstcode.next())
//		{
// 
//		    String code=rstcode.getString("stockCode");
//			mapcode.put(code,j);
//			days.add(code);
//			j++;
//		}
//		
		
		
		ResultSet rst=stmt.executeQuery("select dbms_lob.substr(a.\"date\") \"date\", dbms_lob.substr(a.\"stockCode\") \"stockCode\",cast(dbms_lob.substr(a.\"MACD\")as integer) \"MACD\" from myrzrqye a where  dbms_lob.substr(a.\"date\") >'2018-09-01' and  dbms_lob.substr(a.\"stockCode\")='"+code+"'");
		while(rst.next())
		{
		 String date=rst.getString("date");
		 code=rst.getString("stockCode");
		 Integer macd=Integer.parseInt(rst.getString("MACD"))/1000000;

		 
		 JSONArray item=new JSONArray();
		 item.add(mapcode.get(code));
		 item.add(mapdate.get(date));
		 item.add(macd);
		 data.add(item); 	 
		 
		}
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
     
//        hours.add("12a");
//        hours.add("1a");
//        hours.add("12a");
//        
//
//        days.add("Saturday");
//        days.add("Friday");
//        days.add("Thursday");
//      
//        JSONArray data=new JSONArray();
//        JSONArray item=new JSONArray();
//        item.add(0);
//        item.add(0);
//        item.add(5); 
//        data.add(item);
//        
//        item=new JSONArray();
//        item.add(0);
//        item.add(0);
//        item.add(5);
//        data.add(item);
//
//        item=new JSONArray();
//        item.add(0);
//        item.add(1);
//        item.add(1);
//        data.add(item);
//        
//
//        item=new JSONArray();
//        item.add(1);
//        item.add(0);
//        item.add(2);
//        data.add(item);
//        
//
//        item=new JSONArray();
//        item.add(1);
//        item.add(1);
//        item.add(5);
//        data.add(item);
//        
//
//        item=new JSONArray();
//        item.add(2);
//        item.add(0);
//        item.add(5);
//        data.add(item);
//        
//
//        item=new JSONArray();
//        item.add(2);
//        item.add(1);
//        item.add(5);
//        data.add(item);
        
        
        
//        int data = [[0,0,5],[0,1,1],[1,0,2],[1,1,3],[2,0,1],[2,1,6]]
	    		
	    Map<String, Object> json = new HashMap<String, Object>();  
	    json.put("hours", hours);  
	    json.put("days", days);  
	    json.put("data", data);
	    JsonUtils.writeJson(json, req, resp);  
	}

}
