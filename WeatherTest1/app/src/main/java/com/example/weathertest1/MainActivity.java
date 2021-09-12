package com.example.weathertest1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.StringReader;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    TextView tvResult, res2;
    ImageView weatherIcon;

    //데이터를 호출할 서비스의 기본 주소
    private final String url = "http://api.openweathermap.org/data/2.5/weather";

    //서비스에서 제공받은 API KEY 등록
    private final String appid = "80b3b408e398e420f91d1e3fe08b5328";
    DecimalFormat decimalFormat = new DecimalFormat("#.##");
    LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvResult = findViewById(R.id.tvResult);
        res2 = findViewById(R.id.res2);
        weatherIcon = findViewById(R.id.weatherIcon);

        //GPS 시스템 활용을 위한 Location Manager 선언
        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    }

    public void getWeatherDetails(View view) {
        //위치에 따른 날씨 정보를 가져오는 함수로서, 먼저 사용자 위치 권한을 허가받기
        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, 0);
        } else {
            //GPS Provider 통해 위도와 경도 값 받기
            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();

            //몇 초, 얼마의 거리마다 정보를 갱신 받을 것인지 지정
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, gpsLocationListener);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, gpsLocationListener);

            //사용자가 JSON 데이터를 받아올 주소 형식 지정
            String tempUrl = "";
            tempUrl = url + "?lat=" + latitude + "&lon=" + longitude + "&appid=" + appid;

            //지정한 주소 형식을 요청하여 리소스를 생성하고 받아오기
            StringRequest stringRequest = new StringRequest(Request.Method.POST, tempUrl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {
                    String output = "";
                    String output2 = "";
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        JSONArray jsonArray = jsonObject.getJSONArray("weather");
                        JSONObject jsonObjectWeather = jsonArray.getJSONObject(0);
                        String description = jsonObjectWeather.getString("description");
                        String icon = jsonObjectWeather.getString("icon");
                        String iconUrl = "http://openweathermap.org/img/w/" + icon + ".png";

                        JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                        double temp = jsonObjectMain.getDouble("temp") - 273.15;
                        double feelsLike = jsonObjectMain.getDouble("feels_like") - 273.15;
                        float pressure = jsonObjectMain.getInt("pressure");
                        int humidity = jsonObjectMain.getInt("humidity");

                        JSONObject jsonObjectWind = jsonObject.getJSONObject("wind");
                        String wind = jsonObjectWind.getString("speed");

                        JSONObject jsonObjectClouds = jsonObject.getJSONObject("clouds");
                        String clouds = jsonObjectClouds.getString("all");

                        JSONObject jsonObjectSys = jsonObject.getJSONObject("sys");
                        String countryName = jsonObjectSys.getString("country");
                        String cityName = jsonObject.getString("name");
                        res2.setTextColor(Color.GREEN);
                        tvResult.setTextColor(Color.RED);
                        output += "Current weather of" + cityName + " (" + countryName + ")"
                                + "\n Temp: " + decimalFormat.format(temp) + " 도"
                                + "\n Feels Like: " + decimalFormat.format(feelsLike) + " 도"
                                + "\n Humidity: " + humidity + "%"
                                + "\n Description: " + description
                                + "\n Wind Speed: " + wind + "m/s"
                                + "\n Cloudiness: " + clouds + "%"
                                + "\n Pressure: " + pressure + " hPa";
                        output2 = "Wind Speed: " + wind + "m/s";
                        res2.setText(output2);
                        tvResult.setText(output);
                        Picasso.get().load(iconUrl).into(weatherIcon);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(), error.toString().trim(), Toast.LENGTH_SHORT).show();
                }
            });
            RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
            requestQueue.add(stringRequest);
        }
    }

    final LocationListener gpsLocationListener = new LocationListener() {
        @Override
        public void onLocationChanged(@NonNull Location location) {
            String provider = location.getProvider();
            double longitude = location.getLongitude();
            double latitude = location.getLatitude();
        }

        public void onStatusChanged(String provider, int status, Bundle extras) { }

        public void onProviderEnabled(String provider) { }

        public void onProviderDisabled(String provider) { }
    };
}