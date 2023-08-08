<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>Weather Forecast</title>
        <link rel="stylesheet" href="weatherstyle2.css">
    
</head>
<body>
    <h2>Weather Forecast for the selected pincode</h2>

    
    <% String weatherData = (String) request.getAttribute("weatherData");
    	double temperatureK = (double) request.getAttribute("temperatureK");
    	long pressure = (long) request.getAttribute("pressure");
    	long humidity = (long) request.getAttribute("humidity");
    	double feels_likeK = (double) request.getAttribute("feels_likeK");
    	double temp_maxK = (double) request.getAttribute("temp_maxK");
    	double temp_minK = (double) request.getAttribute("temp_minK");
    	String description=(String) request.getAttribute("description");
    	double speed = (double) request.getAttribute("speed");
    	String name = (String) request.getAttribute("name");

    	double temperatureC = temperatureK - 273.15;
        String TemperatureC = String.format("%.2f", temperatureC);
        
        double feels_likeC = feels_likeK - 273.15;
        String Feels_LikeC = String.format("%.2f", feels_likeC);
        double temp_maxC = temp_maxK - 273.15;
        String Temp_maxC = String.format("%.2f", temp_maxC);
        
        double temp_minC = temp_minK - 273.15;
        String Temp_minC = String.format("%.2f", temp_minC);
        
        


    	
    	%>
    <table>
        <tr>
            <th>Temperature</th>
            <th>Pressure</th>
            <th>Humidity</th>
            <th>Feels like</th>
            <th>Max Temperature</th>
            <th>Min Temperature</th>
            <th>Description</th>
            <th>Wind Speed</th>
            <th>Location </th>
            
                  
            
        </tr>
        <tr>                           
            <td><%= TemperatureC %>&#8451;</td>
            <td><%= pressure %> hPa</td>
            <td><%= humidity %> %</td>
            <td><%= Feels_LikeC %>&#8451;</td>
            <td><%= Temp_maxC %>&#8451;</td>
            <td><%= Temp_minC %>&#8451;</td>
            <td><%= description %></td>
            <td><%= speed %> m/s</td>
            <td><%= name %></td>    
            
        </tr>
    </table>
</body>
</html>
