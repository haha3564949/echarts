package com.fpp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fpp.util.JsonUtils;

public class PieServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String[] label = {"搜索引擎","直接访问","邮件营销","联盟广告","视频广告"};
		String[] value = {"700","300","500","300","200"};  
	    Map<String, Object> json = new HashMap<String, Object>();  
	    json.put("label", label);
	    json.put("value", value);  
	    JsonUtils.writeJson(json, req, resp);  
	}

}
