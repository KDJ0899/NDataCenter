<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>Insert title here</title>
<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
</head>
<body>
<c:forEach items = "${result.results }" var = "results" >
	<h1>title: ${results.title}</h1>
	<h3>keyword: 
	<c:forEach items = "${results.keywords }" var = "keyword">
		${keyword}, 
	</c:forEach>
	</h3>
	<c:forEach items ="${results.data }" var = "obj">
		<p>Date: ${obj.period }  Ratio: ${obj.ratio }</p>
	</c:forEach>
</c:forEach>


<div id="chart_div"></div>


<script type="text/javascript">
	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);
	
	
	function drawChart(){
		
		var arr = [[],[]];
		
		var chart_options = {
				title : '그때 그시절 그것',
				width : 700,
				height : 400,
				bar : {
					groupWidth : '50%' // 예제에서 이 값을 수정
				},
				seriesType : 'bars',
				series : {3 : {type : 'line'}}, // 데이터에서 라인그래프로 만들값을 지정, 3은 순서를 의미하며 0부터 시작
				isStacked : true // 그래프 쌓기(스택), 기본값은 false
			};
		
		var tatas = '${result.results[0].data }';
		
		var size = tatas.length;
		arr = new Array(size);
		<c:forEach items = "${result.results }" var = "results"  varStatus="status1">
			arr[0] = new Array(size);
			var i = ${status1.index}+1;
			arr[0][i] = '${results.title}';
			
			
			<c:forEach items = "${results.data }" var = "data"  varStatus="status2">
				var j = ${status2.index}+1;
				arr[j][i] = ${data.ratio};
				arr[j][0] = "${data.period}";
			</c:forEach>
			
		</c:forEach>
		var data = new google.visualization.arrayToDataTable(arr);
		var chart = new google.visualization.ComboChart(document.getElementById('chart_div'));
		chart.draw(data, chart_options);
	}


</script>
</body>
</html>