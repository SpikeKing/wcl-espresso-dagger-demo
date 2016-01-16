package clwang.chunyu.me.wcl_espresso_dagger_demo;

import clwang.chunyu.me.wcl_espresso_dagger_demo.di.DaggerTestAppComponent;
import clwang.chunyu.me.wcl_espresso_dagger_demo.di.TestAppComponent;
import clwang.chunyu.me.wcl_espresso_dagger_demo.di.TestAppModule;
import clwang.chunyu.me.wcl_espresso_dagger_demo.di.WeatherApplication;

/**
 * 测试天气应用
 * <p>
 * Created by wangchenlong on 16/1/16.
 */
public class TestWeatherApplication extends WeatherApplication {
    private TestAppComponent mTestAppComponent;

    @Override public void onCreate() {
        super.onCreate();
        mTestAppComponent = DaggerTestAppComponent.builder()
                .testAppModule(new TestAppModule(this))
                .build();
    }

    // 组件
    @Override
    public TestAppComponent getAppComponent() {
        return mTestAppComponent;
    }
}

