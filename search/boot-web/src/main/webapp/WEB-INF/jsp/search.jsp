<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Hello</title>
</head>
<html>
  <head>
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">
      google.charts.load('current', {'packages':['scatter']});
      google.charts.setOnLoadCallback(drawChart);
   //   <c:forEach items="${poiList}" var="list1">     
   //   document.write('${poiList.size()}${list1.name}' +'||'+'${list1.longitude}');
   // </c:forEach>
      document.write('===========================');
        	  
document.write(list.name);
      function drawChart() {
    	  //var poiList = new ArrayList();
    	 // var poiList = ${poiList};
    	  //document.write(poiList);
    	  //document.write(poiList[0]);
    	  
    	    var list = new Array(); 
   
    	  var data1 = new google.visualization.DataTable();
    	  data1.addColumn('number','LONGITUDE');
    	  data1.addColumn('number','LATITUDE');
    	//  data1.addRows([[100,30],[200,30]]);
    	//document.wirte(list.length+">>>>>>>>>>>");
    	  data1.addRows(Number('${poiList.size()}'));
    	  //data1.addRows(1);
    	//  data1.setCell(0,0,300);
    	  //data1.setCell(0,1,300);
    	<c:forEach items="${poiList}" var="list" varStatus="loop">     
          //document.write('${poiList.size()}${list.name}' +'||'+'${list.longitude}');
           data1.setCell((Number('${loop.index}')),0,Number('${list.longitude}'));
           data1.setCell(Number('${loop.index}'),1,Number('${list.latitude}'));
        </c:forEach>
        
    	  //data1.setcell(0,0, 100)
       /* var data = google.visualization.arrayToDataTable([
          ['Age', 'Weight'],
          [ 8,      12],
          [ 4,      5.5],
          [ 11,     14],
          [ 4,      5],
          [ 3,      3.5],
          [ 6.5,    7]
        ]);
*/
        var options = {
          title: '-',
          hAxis: {title: 'Longitude', format:'#.#####'},
          vAxis: {title: 'Latitude', format:'#.#####'},
          
          legend: 'none',
        };

        
        var chart2= new google.charts.Scatter(document.getElementById('scatterchart_material'));

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
     ${long1}
     ${long2}
    ${lat1 }
    ${lat2 }
  </body>
</html>