package clwang.chunyu.me.wcl_espresso_dagger_demo.di;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * 模块
 * <p>
 * Created by wangchenlong on 16/1/12.
 */
@Module
public class AppModule {
    private final Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides @AppScope public Context provideAppContext() {
        return mContext;
    }

}
