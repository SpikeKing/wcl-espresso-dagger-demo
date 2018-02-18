package org.wangchenlong.wcl_espresso_dagger_demo.di;


import org.wangchenlong.wcl_espresso_dagger_demo.MainActivityTest;
import dagger.Component;

/**
 * 测试组件, 添加TestAppModule
 * <p>
 * Created by wangchenlong on 16/1/16.
 */
@AppScope @Component(modules = TestAppModule.class)
public interface TestAppComponent extends AppComponent {
    void inject(MainActivityTest test);
}
