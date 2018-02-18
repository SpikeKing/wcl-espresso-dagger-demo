package org.wangchenlong.wcl_espresso_dagger_demo;

import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.util.Log;
import android.view.KeyEvent;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;

import org.wangchenlong.wcl_espresso_dagger_demo.data.WeatherData;
import org.wangchenlong.wcl_espresso_dagger_demo.networks.WeatherApiClient;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.pressKey;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * 测试的Activity
 * <p>
 * Created by wangchenlong on 16/1/16.
 */
@LargeTest @RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private static final String CITY_NAME = "Beijing"; // 因为我们使用测试接口, 设置任何都可以.
    @Rule public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);
    @Inject WeatherApiClient weatherApiClient;  // 测试服务

    @Before public void setUp() {
        Log.e("WCL-TEST", "setUp");
        ((TestWeatherApplication) activityTestRule.getActivity().getApplication())
                .getAppComponent().inject(this);
    }

    @Test public void correctWeatherDataDisplayed() {
        Log.e("WCL-TEST", "correctWeatherDataDisplayed");
        // 执行操作
        onView(ViewMatchers.withId(R.id.menu_action_search)).perform(click());
        onView(withId(android.support.v7.appcompat.R.id.search_src_text)).perform(replaceText(CITY_NAME));
        onView(withId(android.support.v7.appcompat.R.id.search_src_text)).perform(pressKey(KeyEvent.KEYCODE_ENTER));

        // 验证逻辑
        WeatherData weatherData = weatherApiClient.getWeatherForCity(CITY_NAME).toBlocking().first();
        onView(withId(R.id.city_name)).check(matches(withText(weatherData.getCityName())));
        onView(withId(R.id.weather_date)).check(matches(withText(weatherData.getWeatherDate())));
        onView(withId(R.id.weather_state)).check(matches(withText(weatherData.getWeatherState())));
        onView(withId(R.id.weather_description)).check(matches(withText(weatherData.getWeatherDescription())));
        onView(withId(R.id.temperature)).check(matches(withText(weatherData.getTemperatureCelsius())));
        onView(withId(R.id.humidity)).check(matches(withText(weatherData.getHumidity())));
    }
}
