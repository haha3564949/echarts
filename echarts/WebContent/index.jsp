<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!-- 移动设备优先（让项目友好的支持移动设备） -->
<meta name="viewport"
	content="width=device-width, initial-scale=1.0,maximum-scale=1.0,user-scalable=no">
<title>echarts动态数据交互</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/bootstrap.min.css">
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/jquery-2.1.4.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/bootstrap.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath() %>/js/echarts.js"></script>
<script type="text/javascript">

 	$(function(){
 		var url = '<%=request.getContextPath()%>/ApplyServlet';
		var id = 'main';
		setChartBar(url);
 	});
	
	//设置ajax访问后台填充柱图
	 function setChartBar(url){
		
		 var Chart=echarts.init(document.getElementById("main"));
		 Chart.showLoading(
				 {text: 'Loding...'  }
		);
		 var categories=[];
		 var values=[];
		 $.ajax({
	       	url:url,
	       	dataType:"json",
	       	type:'post',
	       	success:function(json){
	       	
	       		hours = json.hours;  
	            days = json.days; 
                data =json.data;
                data = data.map(function (item) {
                    return [item[1], item[0], item[2] || '-'];
                });
		       	var option = {

		       		    tooltip: {
		       		        position: 'top',
		       		        formatter: function (data) {
		       		        var res = data[0].name + '<br/>'
		       		        for (var i = 0, length = data.length; i < length; i++) {
		       		           res += datas[i] + '：' 
		       		               + datas[i] + '<br/>'
		       		         }
		       		         return res
		       		       }
		     
		       		    },
		       		    animation: false,
		       		    grid: {
		       		     height: '50%',
		       	        y: '10%'
		       		    },
		       		    xAxis: {
		       		    	axisLabel: {
		       		            rotate: 90,
		       		        },
		       		       position:'top',
		       		        min:100,
		       		        type: 'category',
		       		        data: hours,
		       		        splitArea: {
		       		            show: true
		       		        }
		       		    },
		       		 dataZoom: [{
		                 show:true,
		                 type: 'inside',
		                 start: 0,
		                 end: 50
		             }, {
		                 show:true,
		                 start: 0,
		                 end: 50,
		                 top:'1%',
		          
		             },
		             {
		            	  
		                     type: 'slider',
		                     show: true,
		                     yAxisIndex: [0],
		                     start:0,
		                     end:5,
		                     left: '0%',
		                  
		             }
		             
		             ],
		       		    yAxis: {
		       		        type: 'category',
		       		        data: days,
		       		        splitArea: {
		       		            show: true
		       		        }
		       		    },
		       		 
		       		    visualMap: {
		       		        min: 0,
		       		        max: 10,
		       		        calculable: true,
		       		        orient: 'horizontal',
		       		        left: 'center',
		       		        bottom: '15%'
		       		    },
		       		    series: [{
		       		        name: 'Punch 1Card',
		       		        type: 'heatmap',
		       		        width:10,
		       		        data: data,
		       		        label: {
		       		            normal: {
		       		                show: true
		       		            }
		       		        },
		       		       showAllSymbol:true,	
		       		        itemStyle: {
		       		            emphasis: {
		       		                shadowBlur: 10,
		       		                shadowColor: 'rgba(0, 0, 0, 0.5)',
		       		             borderWidth:10,
		       		            }
		       		        }
		       		    }]

		       			
		       			
		       	};
		        Chart.hideLoading();
		       	Chart.setOption(option);  
		       	}
	       	});
	 } 
	
</script>
</head>
<body>
<div id="code">
<form name="form1"method="POST" action="index2.jsp">   
  <input type="text" name="code">   
  <input type="submit" value="提交">   
  <input type="reset" value="重置">   
</form>  
</div>
<div id="main" style=" overflow:scroll;width: 1200px; height: 1200px;"></div>
</body>
</html>