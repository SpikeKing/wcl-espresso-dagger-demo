package clwang.chunyu.me.wcl_espresso_dagger_demo;

import android.databinding.DataBindingUtil;
import android.graphics.PorterDuff;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;

import clwang.chunyu.me.wcl_espresso_dagger_demo.data.WeatherData;
import clwang.chunyu.me.wcl_espresso_dagger_demo.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mBinding; // 数据绑定
    private MenuItem mSearchItem; // 菜单项
    private WeatherData mWeatherData; // 天气数据

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
//        mBinding.setWeatherData(mWeatherData);
    }


    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu); // 加载目录资源
        mSearchItem = menu.findItem(R.id.menu_action_search);
        tintSearchMenuItem();
        initSearchView();
        return true;
    }

    // 搜索项着色
    private void tintSearchMenuItem() {
        int color = ContextCompat.getColor(this, android.R.color.white); // 白色
        mSearchItem.getIcon().setColorFilter(color, PorterDuff.Mode.SRC_IN); // 交集
    }

    // 搜索项初始化
    private void initSearchView() {
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(mSearchItem);
    }
}
