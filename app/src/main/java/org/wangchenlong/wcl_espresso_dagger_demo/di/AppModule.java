package org.wangchenlong.wcl_espresso_dagger_demo.di;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.logging.HttpLoggingInterceptor;

import org.wangchenlong.wcl_espresso_dagger_demo.R;
import org.wangchenlong.wcl_espresso_dagger_demo.networks.ApiKeyInterceptor;
import org.wangchenlong.wcl_espresso_dagger_demo.networks.WeatherApiClient;

import dagger.Module;
import dagger.Provides;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * 模块
 * <p>
 * Created by wangchenlong on 16/1/12.
 */
@Module public class AppModule {
    private final Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides @AppScope public Context provideAppContext() {
        return mContext;
    }

    @Provides public WeatherApiClient provideWeatherApiClient() {
        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(
                new ApiKeyInterceptor(mContext.getString(R.string.open_weather_api_key)));
        client.interceptors().add(new HttpLoggingInterceptor());
        return new Retrofit.Builder()
                .baseUrl(WeatherApiClient.END_POINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(WeatherApiClient.class);
    }
}
