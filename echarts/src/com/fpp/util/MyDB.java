package com.fpp.util;
    
import java.sql.Connection;
 
import java.sql.SQLException;
 

import javax.naming.Context;
	import javax.naming.InitialContext;
	import javax.naming.NamingException;
	import javax.sql.DataSource;
	public class MyDB {
	 public  static Connection getConnection(){
	  Connection conn=null;
	  try {
	   //初始化容器
	   Context ctx=new InitialContext();
	   //通过容器来查找容器中的数据源，注意，必须按照制定的目录java:comp/env，最后一个zyy随便写
	   DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/oracleds");
	   //从数据源中获取一个空闲的连接
	   conn=ds.getConnection();
	   
	  } catch (NamingException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  } catch (SQLException e) {
	   // TODO Auto-generated catch block
	   e.printStackTrace();
	  }
	  
	  return conn;
	 }

	}