package clwang.chunyu.me.wcl_espresso_dagger_demo.di;

import android.content.Context;

import com.squareup.okhttp.OkHttpClient;

import clwang.chunyu.me.wcl_espresso_dagger_demo.R;
import clwang.chunyu.me.wcl_espresso_dagger_demo.networks.ApiKeyInterceptor;
import clwang.chunyu.me.wcl_espresso_dagger_demo.networks.WeatherApiClient;
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
@Module
public class AppModule {
    private final Context mContext;

    public AppModule(Context context) {
        mContext = context;
    }

    @Provides @AppScope public Context provideAppContext() {
        return mContext;
    }

    // Retrofit 2.0的请求
    @Provides public WeatherApiClient provideWeatherApiClient() {

        OkHttpClient client = new OkHttpClient();
        client.interceptors().add(
                new ApiKeyInterceptor(mContext.getString(R.string.open_weather_api_key)));

        return new Retrofit.Builder()
                .baseUrl(WeatherApiClient.END_POINT)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(WeatherApiClient.class);
    }

}
