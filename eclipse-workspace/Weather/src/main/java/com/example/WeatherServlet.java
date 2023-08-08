package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

@WebServlet("/WeatherServlet")
public class WeatherServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private static final String OPEN_WEATHER_MAP_API_KEY = "90833b33cef48cca50a4b6842caad336";
    private static final String OPEN_WEATHER_MAP_BASE_URL = "https://api.openweathermap.org/data/2.5/forecast";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	
    	response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        String pincode = request.getParameter("pincode");

        if (pincode == null || pincode.isEmpty()) {
            out.println("<h1>Please enter a valid pincode</h1>");
            return;
        }

        try {
            URL url = new URL(OPEN_WEATHER_MAP_BASE_URL + "?zip=" + pincode + ",IN&appid=" + OPEN_WEATHER_MAP_API_KEY);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            int responseCode = conn.getResponseCode();

            if (responseCode == 200) {
                Scanner sc = new Scanner(url.openStream());
                StringBuilder sb = new StringBuilder();
                while (sc.hasNext()) {
                    sb.append(sc.nextLine());
                }
                sc.close();

                String weatherData = sb.toString();
                JSONParser converter = new JSONParser();
                JSONObject weather = (JSONObject) converter.parse(weatherData);
                JSONArray listJson = (JSONArray) weather.get("list");
                JSONObject cityJson = (JSONObject) weather.get("city");

                JSONObject weatherInfoJson = (JSONObject) listJson.get(0);

                JSONObject mainJson = (JSONObject) weatherInfoJson.get("main");
                JSONObject weatherJson= (JSONObject) ((JSONArray) weatherInfoJson.get("weather")).get(0);
                JSONObject windJson =(JSONObject) weatherInfoJson.get("wind");

             
              request.setAttribute("temperatureK", mainJson.get("temp"));
              request.setAttribute("pressure", mainJson.get("pressure"));
              request.setAttribute("humidity", mainJson.get("humidity"));
              request.setAttribute("feels_likeK", mainJson.get("feels_like"));
              request.setAttribute("temp_maxK", mainJson.get("temp_max"));
              request.setAttribute("temp_minK", mainJson.get("temp_min"));
              request.setAttribute("description", weatherJson.get("description"));
              request.setAttribute("speed", windJson.get("speed"));
              request.setAttribute("name", cityJson.get("name"));
             


              request.getRequestDispatcher("weather.jsp").forward(request, response);
            } else {
                out.println("<h1>Failed to fetch weather data</h1>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h1>Something went wrong!</h1>");
        }
    }
}
