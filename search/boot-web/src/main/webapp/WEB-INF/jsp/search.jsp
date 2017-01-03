<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<meta charset="UTF-8">
<title>Hello</title>
</head>
<html>
<head>
<script type="text/javascript"
	src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
	google.charts.load('current', {
		'packages' : [ 'scatter' ]
	});
	google.charts.setOnLoadCallback(drawChart);
	
	function drawChart() {

		var list = new Array();

		var data1 = new google.visualization.DataTable();
		data1.addColumn('number', 'LONGITUDE');
		data1.addColumn('number', 'LATITUDE');
		data1.addRows(Number('${poiList.size()}'));
		<c:forEach items="${poiList}" var="list" varStatus="loop">
			data1.setCell((Number('${loop.index}')), 0,Number('${list.longitude}'));
			data1.setCell(Number('${loop.index}'), 1, Number('${list.latitude}'));
		</c:forEach>

		var options = {
			title : '     Longitude : ${long1} ~ ${long2} \nLatitude : ${lat1} ~ ${lat2 }',
			hAxis : {
				title : 'Longitude',
				format : '#.#####'
			},
			vAxis : {
				title : 'Latitude',
				format : '#.#####'
			},

			legend : 'none',
		};

		var chart2 = new google.charts.Scatter(document.getElementById('scatterchart_material'));
		chart2.draw(data1, google.charts.Scatter.convertOptions(options));
	}
</script>
</head>
<body>
	<c:forEach items="${poiList}" var="list">
		<!--  ${list.name} -->
	</c:forEach>

	<!-- <div id="chart_div" style="width: 900px; height: 500px;"></div> -->
	<div id="scatterchart_material" style="width: 900px; height: 500px;"></div>
	<br><br>
	<table  border="5" cellpadding="2" width="900px">
		<c:forEach items="${poiList}" var="list1" varStatus="loop">
		<tr>
			<th>${loop.count}</th><td> ${list1.name}</td><td> ${list1.longitude}</td><td>${list1.latitude}</td>
				</tr>
		</c:forEach>
	</table>

</body>
</html>