package com.fpp.action;

import java.io.IOException;
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

public class ApplyServlet extends HttpServlet {

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
        JSONArray hours=new JSONArray();
        hours.add("12a");
        hours.add("1a");
        
        JSONArray days=new JSONArray();
        days.add("Saturday");
        days.add("Friday");
        days.add("Thursday");
      
        JSONArray data=new JSONArray();
        JSONArray item=new JSONArray();
        item.add(0);
        item.add(0);
        item.add(5); 
        data.add(item);
        
        item=new JSONArray();
        item.add(0);
        item.add(0);
        item.add(5);
        data.add(item);

        item=new JSONArray();
        item.add(0);
        item.add(1);
        item.add(1);
        data.add(item);
        

        item=new JSONArray();
        item.add(1);
        item.add(0);
        item.add(2);
        data.add(item);
        

        item=new JSONArray();
        item.add(1);
        item.add(1);
        item.add(5);
        data.add(item);
        

        item=new JSONArray();
        item.add(2);
        item.add(0);
        item.add(5);
        data.add(item);
        

        item=new JSONArray();
        item.add(2);
        item.add(1);
        item.add(5);
        data.add(item);
        
        
        
//        int data = [[0,0,5],[0,1,1],[1,0,2],[1,1,3],[2,0,1],[2,1,6]]
	    		
	    Map<String, Object> json = new HashMap<String, Object>();  
	    json.put("hours", hours);  
	    json.put("days", days);  
	    json.put("data", data);
	    JsonUtils.writeJson(json, req, resp);  
	}

}
