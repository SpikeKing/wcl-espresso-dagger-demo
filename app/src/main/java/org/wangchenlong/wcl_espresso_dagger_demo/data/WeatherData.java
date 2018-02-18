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

package org.wangchenlong.wcl_espresso_dagger_demo.data;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 天气类
 * 网站: http://openweathermap.org/api
 * http://api.openweathermap.org/data/2.5/weather/?q=Beijing&APPID=916d752c71ffb36d77c32089ba636569
 * 应用于数据绑定
 *
 * @author wangchenlong
 */
@SuppressWarnings("unused")
public class WeatherData {
    public static final String DATE_FORMAT = "EEEE, d MMM";  // 日期格式
    private static final int KELVIN_ZERO = 273;  // 开尔文0度
    private static final String FORMAT_TEMPERATURE_CELSIUS = "%d°";

    private String name; // 城市名称
    private Weather[] weather; // 天气数组
    private Main main; // 主要信息

    public String getCityName() {
        return name;
    }

    public String getWeatherDate() {
        return new SimpleDateFormat(DATE_FORMAT, Locale.getDefault()).format(new Date());
    }

    public String getWeatherState() {
        return weather().main;
    }

    public String getWeatherDescription() {
        return weather().description;
    }

    public String getTemperatureCelsius() {
        return String.format(FORMAT_TEMPERATURE_CELSIUS, (int) main.temp - KELVIN_ZERO);
    }

    public String getHumidity() {
        return String.format("%d%%", main.humidity);
    }

    private Weather weather() { // 返回天气
        return weather[0];
    }

    private static class Weather {  // 天气信息类
        private String main; // 简介
        private String description; // 描述
    }

    private static class Main {  // 主要指标类
        private float temp; // 温度
        private int humidity; // 湿度
    }
}
