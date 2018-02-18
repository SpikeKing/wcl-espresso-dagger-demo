package org.wangchenlong.wcl_espresso_dagger_demo.networks;

import org.wangchenlong.wcl_espresso_dagger_demo.data.WeatherData;

import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * 天气请求接口
 * <p>
 * Created by wangchenlong on 16/1/12.
 */
public interface WeatherApiClient {
    String END_POINT = "http://api.openweathermap.org/data/2.5/";

    // 查询城市天气, 2.0的写法更加严格, 注意"/"的位置.
    @GET("weather") Observable<WeatherData> getWeatherForCity(@Query("q") String cityName);
}
