<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Weather Forecast App</title>
    <link rel="stylesheet" href="weatherstyle.css">
    </head>

<body>
    <h1>Weather Forecast</h1>
    <form action="WeatherServlet" method="GET">
    <table><tr>
    <td>Enter Pincode: </td>
    <td><input type="text" name="pincode"></td>
    </tr></table>
    <br><br>
    <input type="submit" value="Submit">
</form>
    
</body>
</html>

    