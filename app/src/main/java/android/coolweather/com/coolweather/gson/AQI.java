package android.coolweather.com.coolweather.gson;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Administrator on 2017/5/26.
 */

public class AQI {

    public AQIcity city;

    public class AQIcity{

        public String aqi;
        public String pm25;
    }
}
