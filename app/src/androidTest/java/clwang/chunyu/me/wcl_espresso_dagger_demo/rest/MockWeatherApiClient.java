/*
 * Copyright 2015 Egor Andreevici
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package clwang.chunyu.me.wcl_espresso_dagger_demo.rest;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import clwang.chunyu.me.wcl_espresso_dagger_demo.data.TestData;
import clwang.chunyu.me.wcl_espresso_dagger_demo.data.WeatherData;
import clwang.chunyu.me.wcl_espresso_dagger_demo.networks.WeatherApiClient;
import rx.Observable;

/**
 * 模拟天气Api客户端
 */
public class MockWeatherApiClient implements WeatherApiClient {
    @Override public Observable<WeatherData> getWeatherForCity(String cityName) {
        // 获得模拟数据
        WeatherData weatherData = new Gson().fromJson(TestData.MUNICH_WEATHER_DATA_JSON, WeatherData.class);
        return Observable.just(weatherData).delay(1, TimeUnit.SECONDS); // 延迟时间
    }
}
