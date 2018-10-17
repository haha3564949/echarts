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
 		var url = '<%=request.getContextPath()%>/ApplyServlet3';
 		var code='<%=request.getParameter("code")%>';
		var id = 'main';
		setChartBar(url,code);
 	});
 	var upColor = '#00da3c';
 	var downColor = '#ec0000';

	//设置ajax访问后台填充柱图
	
	function calculateMA(dayCount, data) {
    var result = [];
    for (var i = 0, len = data.values.length; i < len; i++) {
        if (i < dayCount) {
            result.push('-');
            continue;
        }
        var sum = 0;
        for (var j = 0; j < dayCount; j++) {
            sum += data.values[i - j][1];
        }
        result.push(+(sum / dayCount).toFixed(3));
    }
    return result;
}
	
	 function setChartBar(url,code){
		
		 var Chart=echarts.init(document.getElementById("main"));
		 Chart.showLoading(
				 {text: 'Loding...'  }
		);
 
		 $.ajax({
	       	url:url,
	       	dataType:"json",
	       	data:{"code":code},
	       	type:'post',
	       	success:function(json){
	       	
	       		categoryData = json.categoryData,
	       		values = json.values; 
	       		volumes =json.volumes;       
		       	var option = {


		       	        backgroundColor: '#fff',
		       	        animation: false,
		       	        legend: {
		       	            bottom: 10,
		       	            left: 'center',
		       	            data: ['Dow-Jones index', 'MA5', 'MA10', 'MA20', 'MA30']
		       	        },
		       	        tooltip: {
		       	            trigger: 'axis',
		       	            axisPointer: {
		       	                type: 'cross'
		       	            },
		       	            backgroundColor: 'rgba(245, 245, 245, 0.8)',
		       	            borderWidth: 1,
		       	            borderColor: '#ccc',
		       	            padding: 10,
		       	            textStyle: {
		       	                color: '#000'
		       	            },
		       	            position: function (pos, params, el, elRect, size) {
		       	                var obj = {top: 10};
		       	                obj[['left', 'right'][+(pos[0] < size.viewSize[0] / 2)]] = 30;
		       	                return obj;
		       	            }
		       	            // extraCssText: 'width: 170px'
		       	        },
		       	        axisPointer: {
		       	            link: {xAxisIndex: 'all'},
		       	            label: {
		       	                backgroundColor: '#777'
		       	            }
		       	        },
		       	        toolbox: {
		       	            feature: {
		       	                dataZoom: {
		       	                    yAxisIndex: false
		       	                },
		       	                brush: {
		       	                    type: ['lineX', 'clear']
		       	                }
		       	            }
		       	        },
		       	        brush: {
		       	            xAxisIndex: 'all',
		       	            brushLink: 'all',
		       	            outOfBrush: {
		       	                colorAlpha: 0.1
		       	            }
		       	        },
		       	        visualMap: {
		       	            show: false,
		       	            seriesIndex: 5,
		       	            dimension: 2,
		       	            pieces: [{
		       	                value: 1,
		       	                color: downColor
		       	            }, {
		       	                value: -1,
		       	                color: upColor
		       	            }]
		       	        },
		       	        grid: [
		       	            {
		       	                left: '10%',
		       	                right: '8%',
		       	                height: '50%'
		       	            },
		       	            {
		       	                left: '10%',
		       	                right: '8%',
		       	                top: '63%',
		       	                height: '16%'
		       	            }
		       	        ],
		       	        xAxis: [
		       	            {
		       	                type: 'category',
		       	                data: json.categoryData,
		       	                scale: true,
		       	                boundaryGap : false,
		       	                axisLine: {onZero: false},
		       	                splitLine: {show: false},
		       	                splitNumber: 20,
		       	                min: 'dataMin',
		       	                max: 'dataMax',
		       	                axisPointer: {
		       	                    z: 100
		       	                }
		       	            },
		       	            {
		       	                type: 'category',
		       	                gridIndex: 1,
		       	                data: json.categoryData,
		       	                scale: true,
		       	                boundaryGap : false,
		       	                axisLine: {onZero: false},
		       	                axisTick: {show: false},
		       	                splitLine: {show: false},
		       	                axisLabel: {show: false},
		       	                splitNumber: 20,
		       	                min: 'dataMin',
		       	                max: 'dataMax'
		       	                // axisPointer: {
		       	                //     label: {
		       	                //         formatter: function (params) {
		       	                //             var seriesValue = (params.seriesData[0] || {}).value;
		       	                //             return params.value
		       	                //             + (seriesValue != null
		       	                //                 ? '\n' + echarts.format.addCommas(seriesValue)
		       	                //                 : ''
		       	                //             );
		       	                //         }
		       	                //     }
		       	                // }
		       	            }
		       	        ],
		       	        yAxis: [
		       	            {
		       	                scale: true,
		       	                splitArea: {
		       	                    show: true
		       	                }
		       	            },
		       	            {
		       	                scale: true,
		       	                gridIndex: 1,
		       	                splitNumber: 2,
		       	                axisLabel: {show: false},
		       	                axisLine: {show: false},
		       	                axisTick: {show: false},
		       	                splitLine: {show: false}
		       	            }
		       	        ],
		       	        dataZoom: [
		       	            {
		       	                type: 'inside',
		       	                xAxisIndex: [0, 1],
		       	                start: 98,
		       	                end: 100
		       	            },
		       	            {
		       	                show: true,
		       	                xAxisIndex: [0, 1],
		       	                type: 'slider',
		       	                top: '85%',
		       	                start: 98,
		       	                end: 100
		       	            }
		       	        ],
		       	        series: [
		       	            {
		       	                name: 'Dow-Jones index',
		       	                type: 'candlestick',
		       	                data: json.values,
		       	                itemStyle: {
		       	                    normal: {
		       	                        color: upColor,
		       	                        color0: downColor,
		       	                        borderColor: null,
		       	                        borderColor0: null
		       	                    }
		       	                },
		       	                tooltip: {
		       	                    formatter: function (param) {
		       	                        param = param[0];
		       	                        return [
		       	                            'Date: ' + param.name + '<hr size=1 style="margin: 3px 0">',
		       	                            'Open: ' + param.data[0] + '<br/>',
		       	                            'Close: ' + param.data[1] + '<br/>',
		       	                            'Lowest: ' + param.data[2] + '<br/>',
		       	                            'Highest: ' + param.data[3] + '<br/>'
		       	                        ].join('');
		       	                    }
		       	                }
		       	            },
		       	            {
		       	                name: 'MA5',
		       	                type: 'line',
		       	                data: calculateMA(5, json),
		       	                smooth: true,
		       	                lineStyle: {
		       	                    normal: {opacity: 0.5}
		       	                }
		       	            },
		       	            {
		       	                name: 'MA10',
		       	                type: 'line',
		       	                data: calculateMA(10, json),
		       	                smooth: true,
		       	                lineStyle: {
		       	                    normal: {opacity: 0.5}
		       	                }
		       	            },
		       	            {
		       	                name: 'MA20',
		       	                type: 'line',
		       	                data: calculateMA(20, json),
		       	                smooth: true,
		       	                lineStyle: {
		       	                    normal: {opacity: 0.5}
		       	                }
		       	            },
		       	            {
		       	                name: 'MA30',
		       	                type: 'line',
		       	                data: calculateMA(30, json),
		       	                smooth: true,
		       	                lineStyle: {
		       	                    normal: {opacity: 0.5}
		       	                }
		       	            },
		       	            {
		       	                name: 'Volume',
		       	                type: 'bar',
		       	                xAxisIndex: 1,
		       	                yAxisIndex: 1,
		       	                data: json.volumes
		       	            }
		       	        ]
		       	    
		       			
		       			
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
<form name="form1" method="POST" action="index2.jsp">   
  <input type="text" name="code">   
  <input type="submit" value="提交">   
  <input type="reset" value="重置">   
</form>  
</div>
<div id="main" style="	width:1200px;height:200px"></div>
</body>
</html>