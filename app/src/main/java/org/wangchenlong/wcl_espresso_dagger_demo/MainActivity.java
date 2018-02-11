package org.wangchenlong.wcl_espresso_dagger_demo;

import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import javax.inject.Inject;


import org.wangchenlong.wcl_espresso_dagger_demo.data.WeatherData;
import org.wangchenlong.wcl_espresso_dagger_demo.databinding.ActivityMainBinding;
import org.wangchenlong.wcl_espresso_dagger_demo.di.WeatherApplication;
import org.wangchenlong.wcl_espresso_dagger_demo.networks.WeatherApiClient;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 实现简单的查询天气的功能.
 *
 * @author wangchenlong
 */
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding; // 数据绑定
    private MenuItem mSearchItem; // 菜单项
    private Subscription mSubscription; // 订阅

    @Inject WeatherApiClient mWeatherApiClient; // 天气客户端

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((WeatherApplication) getApplication()).getAppComponent().inject(this);
        mBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu); // 加载目录资源
        mSearchItem = menu.findItem(R.id.menu_action_search);
        tintSearchMenuItem();
        initSearchView();
        return true;
    }

    // 搜索项着色, 会覆盖基础颜色, 取交集.
    private void tintSearchMenuItem() {
        int color = ContextCompat.getColor(this, android.R.color.white); // 白色
        mSearchItem.getIcon().setColorFilter(color, PorterDuff.Mode.SRC_IN); // 交集
    }

    // 搜索项初始化
    private void initSearchView() {
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(mSearchItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override public boolean onQueryTextSubmit(String query) {
                MenuItemCompat.collapseActionView(mSearchItem);
                loadWeatherData(query); // 加载查询数据
                return true;
            }

            @Override public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    // 加载天气数据
    private void loadWeatherData(String cityName) {
        mBinding.progress.setVisibility(View.VISIBLE);
        mSubscription = mWeatherApiClient
                .getWeatherForCity(cityName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::bindData, this::bindDataError);
    }

    // 绑定天气数据
    private void bindData(WeatherData weatherData) {
        mBinding.progress.setVisibility(View.INVISIBLE);
        mBinding.weatherLayout.setVisibility(View.VISIBLE);
        mBinding.setWeatherData(weatherData);
    }

    // 绑定数据失败
    private void bindDataError(Throwable throwable) {
        mBinding.progress.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onDestroy() {
        if (mSubscription != null) {
            mSubscription.unsubscribe();
        }
        super.onDestroy();
    }
}
