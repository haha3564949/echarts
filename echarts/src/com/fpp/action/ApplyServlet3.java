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

public class ApplyServlet3 extends HttpServlet {

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
		
		JSONArray categoryData=new JSONArray();
		
        JSONArray values=new JSONArray();		
        JSONArray volumes=new JSONArray();
        
//        categoryData.push(rawData[i].splice(0, 1)[0]);
//        values.push(rawData[i]);
//        volumes.push([i, rawData[i][4], rawData[i][0] > rawData[i][1] ? 1 : -1]);
 
        
        
        MyDB md=new MyDB();
		try {
		Connection conn=md.getConnection();
		Statement stmt=conn.createStatement();		
		ResultSet rst=stmt.executeQuery("select dbms_lob.substr(a.\"date\") \"date\",a.open,a.high,a.close,a.low,a.volume from  testdata a order by 1 asc");
		int i=0;
		while(rst.next())
		{
		 String date=rst.getString("date");
		 String open=rst.getString("open");
		 String high=rst.getString("high");		 
		 String close=rst.getString("close");
		 String low=rst.getString("low");
		 String volume=rst.getString("volume");
		 
		 categoryData.add(date); 
		 
		 JSONArray item=new JSONArray(); 
         item.add(open);
         item.add(high);
         item.add(close);
         item.add(low);
         item.add(volume);   	 
		 values.add(item);
		 
		 
		 item=new JSONArray(); 
		 
		 item.add(i);
		 item.add(volume);
		
		 if (Float.parseFloat(open)>Float.parseFloat(high))
		 {
		     item.add(1);
		 }
		 else
		 {
			 item.add(-1);
		 volumes.add(item); 	 
		 }
		 i++;
		}
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  
     
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%---done---%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
	    		
	    Map<String, Object> json = new HashMap<String, Object>();  
	    json.put("categoryData", categoryData);  
	    json.put("values", values);  
	    json.put("volumes", volumes);
	    JsonUtils.writeJson(json, req, resp);  
	}

}
