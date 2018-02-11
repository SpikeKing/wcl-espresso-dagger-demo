package org.wangchenlong.wcl_espresso_dagger_demo.di;

import org.wangchenlong.wcl_espresso_dagger_demo.MainActivity;
import dagger.Component;

/**
 * 组件
 * <p>
 * Created by wangchenlong on 16/1/12.
 */
@AppScope
@Component(modules = AppModule.class)
public interface AppComponent {
    void inject(MainActivity activity);
}
