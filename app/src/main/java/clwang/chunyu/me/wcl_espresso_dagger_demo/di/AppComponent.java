package clwang.chunyu.me.wcl_espresso_dagger_demo.di;

import android.content.Context;

import clwang.chunyu.me.wcl_espresso_dagger_demo.MainActivity;
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

    @AppScope Context appContex();
}
