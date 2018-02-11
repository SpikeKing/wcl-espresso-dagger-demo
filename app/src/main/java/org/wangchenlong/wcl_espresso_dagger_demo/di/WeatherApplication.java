package org.wangchenlong.wcl_espresso_dagger_demo.di;

import android.app.Application;

/**
 * 应用
 * <p>
 * Created by wangchenlong on 16/1/12.
 */
public class WeatherApplication extends Application {
    private AppComponent mAppComponent;

    @Override public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
