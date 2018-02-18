package org.wangchenlong.wcl_espresso_dagger_demo.di;

import android.content.Context;

import org.wangchenlong.wcl_espresso_dagger_demo.networks.WeatherApiClient;
import org.wangchenlong.wcl_espresso_dagger_demo.rest.MockWeatherApiClient;

import dagger.Module;
import dagger.Provides;

/**
 * 测试App的Module, 提供AppContext, WeatherApiClient的模拟数据.
 * <p>
 * Created by wangchenlong on 16/1/16.
 */
@Module
public class TestAppModule {
    private final Context mContext;

    public TestAppModule(Context context) {
        mContext = context.getApplicationContext();
    }

    @AppScope @Provides public Context provideAppContext() {
        return mContext;
    }

    @Provides public WeatherApiClient provideWeatherApiClient() {
        return new MockWeatherApiClient();
    }
}
