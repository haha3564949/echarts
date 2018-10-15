package com.fpp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpp.util.JsonUtils;

public class ApplyServlet2 extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String[] categories = {"鞋", "衬衫", "外套", "牛仔裤"};  
	    Integer[] values = {80, 50, 75, 100};  
	    Map<String, Object> json = new HashMap<String, Object>();  
	    json.put("categories", categories);  
	    json.put("values", values);  
	    JsonUtils.writeJson(json, req, resp);  
	}

}
