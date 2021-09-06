<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html>
<body>
   <div id="forecast_box" class="container">
      <div id="forecast_title" style="text-align: center; height:50px;">
         <h1>FORECAST</h1>
      </div>
      <div id="forecast_graph" class="forecast row">
         <div id="pm10Chart" class="forecast_chart_container col-md-4 col-xs-4" style="height: 46% !important;">
            </div>
         <div id="pm25Chart" class="forecast_chart_container col-md-4 col-xs-4" style="height: 46% !important;">
            </div>
         <div id="ptyChart" class="forecast_chart_container col-md-4 col-xs-4" style="height: 46% !important;">
            </div>
      </div>
      <div class="nonbox" style="height: 60px;"></div>

      <div id="forecast_graph2" class="forecast row">
         <div class="forecast_chart_container col-md-6 col-xs-12" style="margin-bottom:30px">
            <canvas id="tmxChart"></canvas>
         </div>
         <div class="forecast_chart_container col-md-6 col-xs-12" style="margin-bottom:60px">
            <canvas id="tmnChart"></canvas>
         </div>
      </div>
      
   </div>

   <script src="//cdn.amcharts.com/lib/4/core.js"></script>
   <script src="//cdn.amcharts.com/lib/4/charts.js"></script>
   <script src="//cdn.amcharts.com/lib/4/themes/animated.js"></script>
   <script src="https://cdn.amcharts.com/lib/4/themes/frozen.js"></script>
   <script src="//cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
   <script>
      
      let ptydata = [{weather:"Sunny", value:${sunny}}, {weather:"Rainy", value:${rainy}}, {weather:"Snow", value:${snow}}, {weather:"Shower", value:${shower}}]
      let pm10data = [{pm10:"great", value:${pm10_great}}, {pm10:"good", value:${pm10_good}}, {pm10:"bad", value:${pm10_bad}}, {pm10:"worst", value:${pm10_worst}}]
      let pm25data = [{pm25:"great", value:${pm25_great}}, {pm25:"good", value:${pm25_good}}, {pm25:"bad", value:${pm25_bad}}, {pm25:"worst", value:${pm25_worst}}]
      
      let tmxdata =[${tmx_data9}, ${tmx_data8}, ${tmx_data7}, ${tmx_data6},${tmx_data5},${tmx_data4}, ${tmx_data3}, ${tmx_data2}, ${tmx_data1},${tmx_data0}]
      let tmndata =[${tmn_data9}, ${tmn_data8}, ${tmn_data7}, ${tmn_data6},${tmn_data5}, ${tmn_data4}, ${tmn_data3}, ${tmn_data2}, ${tmn_data1},${tmn_data0}]
      let daydata =[${day_data9}, ${day_data8}, ${day_data7}, ${day_data6},${day_data5}, ${day_data4}, ${day_data3}, ${day_data2}, ${day_data1},${day_data0}]
      
      var tmx = document.getElementById("tmxChart").getContext('2d');
      var tmn = document.getElementById("tmnChart").getContext('2d');
      
      /** 
      * Doughnut Chart
      */
      
      //---미세먼지 데이터 시각화---
       am4core.ready(function() {
         
         //테마 적용,로고 삭제
         am4core.addLicense("ch-custom-attribution");
         am4core.useTheme(am4themes_animated);

         var chart_pm10 = am4core.create("pm10Chart", am4charts.PieChart);
         chart_pm10.hiddenState.properties.opacity = 0; 
         chart_pm10.fontSize=11;
         chart_pm10.data = pm10data;
         chart_pm10.radius = am4core.percent(70);
         chart_pm10.innerRadius = am4core.percent(50);
         chart_pm10.startAngle = 150; 
         chart_pm10.endAngle = 390;   

         chart_pm10.legend = new am4charts.Legend();
         
         var series_pm10 = chart_pm10.series.push(new am4charts.PieSeries());
         series_pm10.dataFields.value = "value";
         series_pm10.dataFields.category = "pm10";
         series_pm10.labels.template.disabled = true;
         series_pm10.ticks.template.disabled = true;
         
         series_pm10.slices.template.cornerRadius = 10;
         series_pm10.slices.template.innerCornerRadius = 7;
         series_pm10.slices.template.draggable = false;
         series_pm10.slices.template.inert = false;
         series_pm10.alignLabels = false;
         
         series_pm10.hiddenState.properties.startAngle = 90;
         series_pm10.hiddenState.properties.endAngle = 90;
         
         var label_pm10 = chart_pm10.seriesContainer.createChild(am4core.Label);
         label_pm10.textAlign = "middle";
         label_pm10.horizontalCenter = "middle";
         label_pm10.verticalCenter = "middle";
         label_pm10.adapter.add("text", function(text, target){
           return "[font-size:1.1em]한달 간[/]\n[bold font-size:1.3em]미세먼지[/]";
         })
         });

       //---초미세먼지 데이터 시각화---
       am4core.ready(function() {
          
         am4core.unuseAllThemes();

         am4core.addLicense("ch-custom-attribution");
         am4core.useTheme(am4themes_animated);
         am4core.useTheme(am4themes_frozen);

         var chart_pm25 = am4core.create("pm25Chart", am4charts.PieChart);
         chart_pm25.hiddenState.properties.opacity = 0; 
         chart_pm25.fontSize=11;
         chart_pm25.data = pm25data;
         chart_pm25.radius = am4core.percent(70);
         chart_pm25.innerRadius = am4core.percent(50);
         chart_pm25.startAngle = 150; 
         chart_pm25.endAngle = 390;      

         chart_pm25.legend = new am4charts.Legend();
         
         var series_pm25 = chart_pm25.series.push(new am4charts.PieSeries());
         series_pm25.dataFields.value = "value";
         series_pm25.dataFields.category = "pm25";
         series_pm25.labels.template.disabled = true;
         series_pm25.ticks.template.disabled = true;
         
         series_pm25.slices.template.cornerRadius = 10;
         series_pm25.slices.template.innerCornerRadius = 7;
         series_pm25.slices.template.draggable = false;
         series_pm25.slices.template.inert = false;
         series_pm25.alignLabels = false;
         
         series_pm25.hiddenState.properties.startAngle = 90;
         series_pm25.hiddenState.properties.endAngle = 90;
         
         var label_pm25 = chart_pm25.seriesContainer.createChild(am4core.Label);
         label_pm25.textAlign = "middle";
         label_pm25.horizontalCenter = "middle";
         label_pm25.verticalCenter = "middle";
         label_pm25.adapter.add("text", function(text, target){
           return "[font-size:1.1em]한달 간[/]\n[bold font-size:1.3em]초미세먼지[/]";
         })
         }); 
       
       //---강수확률 데이터 시각화---
      am4core.ready(function() {
         
         am4core.unuseAllThemes();
         
         am4core.addLicense("ch-custom-attribution");
         am4core.useTheme(am4themes_animated);

         var chart_pty = am4core.create("ptyChart", am4charts.PieChart);
         chart_pty.hiddenState.properties.opacity = 0; 
         chart_pty.fontSize=11;
         chart_pty.data = ptydata;
         chart_pty.radius = am4core.percent(70);
         chart_pty.innerRadius = am4core.percent(50);
         chart_pty.startAngle = 150; 
         chart_pty.endAngle = 390;      

         chart_pty.legend = new am4charts.Legend();
         
         var series_pty = chart_pty.series.push(new am4charts.PieSeries());
         series_pty.dataFields.value = "value";
         series_pty.dataFields.category = "weather";
         series_pty.labels.template.disabled = true;
         series_pty.ticks.template.disabled = true;
         
         series_pty.slices.template.cornerRadius = 10;
         series_pty.slices.template.innerCornerRadius = 7;
         series_pty.slices.template.draggable = false;
         series_pty.slices.template.inert = false;
         series_pty.alignLabels = false;
         
         series_pty.hiddenState.properties.startAngle = 90;
         series_pty.hiddenState.properties.endAngle = 90;
         
         var label_pty = chart_pty.seriesContainer.createChild(am4core.Label);
         label_pty.textAlign = "middle";
         label_pty.horizontalCenter = "middle";
         label_pty.verticalCenter = "middle";
         label_pty.adapter.add("text", function(text, target){
           return "[font-size:1.1em]한달 간[/]\n[bold font-size:1.3em]강수일수[/]";
         })
         }); 

       
      /** 
      * Line Chart
      */
       
      //---최고기온 데이터 시각화---
      var tmxChart = new Chart(tmx,
            {
               type : 'line',
               data : {
                  labels : daydata,
                  datasets : [{
                     label : '최고기온',
                     data : tmxdata,
                     fill : false,
                     borderColor : 'rgba(255, 56, 96, 0.5)',
                     tension : 0.1
                  }

                  ]
               },
               options : {
                  scales : {
                     yAxes : [ {
                        ticks : {
                           beginAtZero : false,
                           max:40,
                           min:15
                        }
                     } ]
                  }
               }
            });
      
      //---최저기온 데이터 시각화---
      var tmnChart = new Chart(tmn,
            {
               type : 'line',
               data : {
                  labels : daydata,
                  datasets : [ {
                     label : '최저기온',
                     data : tmndata,
                     fill : false,
                     borderColor : 'rgba(50, 115, 220, 0.5)',
                     tension : 0.1
                  }

                  ]
               },
               options : {
                  scales : {
                     yAxes : [ {
                        ticks : {
                           beginAtZero : false,
                           max:40,
                           min:15
                        }
                     } ]
                  }
               }
            });
   </script>
</body>
</html>