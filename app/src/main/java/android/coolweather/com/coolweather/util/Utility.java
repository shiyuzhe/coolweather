package android.coolweather.com.coolweather.util;

import android.coolweather.com.coolweather.db.City;
import android.coolweather.com.coolweather.db.County;
import android.coolweather.com.coolweather.db.Province;
import android.coolweather.com.coolweather.gson.Weather;
import android.text.TextUtils;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2017/5/22.
 */

public class Utility {

    /*
     *     解析和处理服务器返回的省级数据
     */
    public static boolean handleProvinceResponce(String response){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray appProvinces = new JSONArray(response);
                for (int i =0;i<appProvinces.length();i++) {
                    JSONObject provinceObject = appProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return  true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  false;
    }


    /*
    *     解析和处理服务器返回的市级数据
    */
    public static boolean handleCityResponce(String response,int procinceId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray appCities = new JSONArray(response);
                for (int i =0;i<appCities.length();i++) {
                    JSONObject cityOb = appCities.getJSONObject(i);
                   City city = new City();
                    city.setCityName(cityOb.getString("name"));
                    city.setCityCode(cityOb.getInt("id"));
                    city.setProvinceId(procinceId);
                    city.save();
                }
                return  true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  false;
    }

    /*
    *     解析和处理服务器返回的县级数据
    */
    public static boolean handleCountryResponce(String response,int cityId){
        if(!TextUtils.isEmpty(response)){
            try {
                JSONArray appCounties = new JSONArray(response);
                for (int i =0;i<appCounties.length();i++) {
                    JSONObject countyOb = appCounties.getJSONObject(i);
                    County county = new County();
                    county.setCountyName(countyOb.getString("name"));
                    county.setCityId(cityId);
                    county.setWeatherId(countyOb.getString("weather_id"));
                    county.save();
                }
                return  true;
            }catch (JSONException e){
                e.printStackTrace();
            }
        }
        return  false;
    }

    /*
    *将返回的json数据解析成Weather实体类
     */
    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
          JSONArray jsonArray =  jsonObject.getJSONArray("HeWeather");
           String weatherContent =  jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent, Weather.class);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
