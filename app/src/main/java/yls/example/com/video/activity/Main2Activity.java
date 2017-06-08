package yls.example.com.video.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;
import com.amap.api.location.AMapLocationListener;
import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.google.gson.Gson;
import com.hjm.bottomtabbar.BottomTabBar;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import okhttp3.Call;
import yls.example.com.video.R;
import yls.example.com.video.adapter.TodayMovieAdapter;
import yls.example.com.video.fragment.Main2Fragment;
import yls.example.com.video.fragment.MainFragment;
import yls.example.com.video.fragment.MovieFragment;
import yls.example.com.video.fragment.TVFragment;
import yls.example.com.video.model.TodayMovieBean;
import yls.example.com.video.utils.SPUtil;

public class Main2Activity extends AppCompatActivity {
    private RecyclerView mMovieView;
    private String cityId = "5";
    private List<TodayMovieBean.ResultBean> mResultBeen = new ArrayList<>();
    private final String TODAY_MOVIE = "http://v.juhe.cn/movie/movies.today";
    //声明AMapLocationClient类对象
    private AMapLocationClient mLocationClient = null;
    //声明定位回调监听器
    //声明AMapLocationClientOption对象
    private AMapLocationClientOption mLocationOption = null;
    private String cityName = "";
    private Gson mGson = new Gson();
    private TodayMovieAdapter mTodayMovieAdapter;
    private BottomTabBar mBottomTabBar;
    private Toolbar mToolbar;
    private ResideMenu resideMenu;
    private boolean isNight;
    private String[] items = {"超大号字体", "大号字体", "正常字体", "小号字体", "超小号字体"};
    private int textSize;
private BottomNavigationBar mBottomNavigationBar;
    private Main2Fragment mMain2Fragment;
    private MainFragment mMainFragment;
    private MovieFragment mMovieFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        applyPermissions();
        initLocation();

        //initResideMenu();
        initViews();
//        getTodayMovieData();
//        initAdapter();
        // initResideMenu();
    }

    private void initAdapter() {
        mTodayMovieAdapter = new TodayMovieAdapter(mResultBeen);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(Main2Activity.this, 2);
        mMovieView.setLayoutManager(gridLayoutManager);
        mMovieView.setAdapter(mTodayMovieAdapter);
        mTodayMovieAdapter.notifyDataSetChanged();
    }
//
    private void initLocation() {
        //初始化定位
        mLocationClient = new AMapLocationClient(getApplicationContext());
//设置定位回调监听
        mLocationClient.setLocationListener(mLocationListener);
        mLocationOption = new AMapLocationClientOption();
        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
        //设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
        mLocationOption.setInterval(1000);
        //设置是否返回地址信息（默认返回地址信息）
        mLocationOption.setNeedAddress(true);
        //单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
        mLocationOption.setHttpTimeOut(20000);
        //给定位客户端对象设置定位参数
        mLocationClient.setLocationOption(mLocationOption);


//        mLocationListener = new AMapLocationListener() {
//            @Override
//            public void onLocationChanged(AMapLocation aMapLocation) {
//
//            }
//        };
//        //启动定位
        mLocationClient.startLocation();
        //   mLocationClient.stopLocation();//停止定位后，本地定位服务并不会被销毁
        //  mLocationClient.onDestroy();//销毁定位客户端，同时销毁本地定位服务。
    }

    AMapLocationListener mLocationListener = new AMapLocationListener() {
        @Override
        public void onLocationChanged(AMapLocation aMapLocation) {
            if (aMapLocation != null) {
                if (aMapLocation.getErrorCode() == 0) {
                    Log.i("kkkkkkkkkkk", "aMapLocation.getAdCode() : =  " + aMapLocation.getAdCode() + "\n"
                            + " aMapLocation.getAddress() = " + aMapLocation.getAddress() + "\n"
                            + "aMapLocation.getAoiName()  = " + aMapLocation.getAoiName() + "\n"
                            + "aMapLocation.getBuildingId() = " + aMapLocation.getBuildingId() + "\n"
                            + "aMapLocation.getCity() = " + aMapLocation.getCity() + "\n"
                            + "aMapLocation.getProvince() = " + aMapLocation.getProvince() + "\n"
                            + "aMapLocation.getGpsAccuracyStatus() = " + aMapLocation.getGpsAccuracyStatus() + "\n"
                            + "aMapLocation.getLocationDetail() = " + aMapLocation.getLocationDetail() + "\n"
                            + "aMapLocation.getLatitude() = " + aMapLocation.getLatitude() + "\n"
                            + "aMapLocation.getLongitude() = " + aMapLocation.getLongitude() + "\n"
                    );
                    cityName = aMapLocation.getCity().replace("市", "");
                    SPUtil.setLongitude(Main2Activity.this, (float) (aMapLocation.getLongitude()));
                    SPUtil.setLatitude(Main2Activity.this, (float) aMapLocation.getLatitude());
                    //Toast.makeText(MainActivity.this, cityName, Toast.LENGTH_SHORT).show();
                } else {
                    //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
                    Log.e("AmapError", "location Error, ErrCode:"
                            + aMapLocation.getErrorCode() + ", errInfo:"
                            + aMapLocation.getErrorInfo());
                }
            }


        }
    };

    private void getTodayMovieData() {
        OkHttpUtils.get()//
                .url(TODAY_MOVIE)//
                .addParams("key", "9d9af82cd7efc360fb0960307ba4aa26")//
                .addParams("cityid", cityId)//
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaaaaa", "onError: " + e.toString());
                    }

                    //?key=9d9af82cd7efc360fb0960307ba4aa26&"+cityId
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaaaa", "onResponse: " + response.toString());
                        mResultBeen = mGson.fromJson(response.toString(), TodayMovieBean.class).getResult();
                        mTodayMovieAdapter.changeData(mResultBeen);
                    }
                });
    }

    private void initViews() {

//        mBottomNavigationBar = (BottomNavigationBar) findViewById(R.id.bottom_navigation_bar);
//        mBottomNavigationBar
//                .addItem(new BottomNavigationItem(R.drawable.a1, "今日推荐"))
//                .addItem(new BottomNavigationItem(R.drawable.a2, "影院"))
//                .addItem(new BottomNavigationItem(R.drawable.a3, "新闻"))
//                .addItem(new BottomNavigationItem(R.drawable.a4, "漫画"))
//                .initialise();
//
//        mBottomNavigationBar.setTabSelectedListener(new BottomNavigationBar.OnTabSelectedListener(){
//            @Override
//            public void onTabSelected(int position) {
//                switch (position){
//                    case 0:
//                        if(mMainFragment == null){
//                            mMainFragment = new MainFragment();
//
//                        }
//                        replaceFragment(mMainFragment);
//                        break;
//                    case 1:
//                        if(mMovieFragment == null){
//                            mMovieFragment = new MovieFragment();
//                        }
//                        replaceFragment(mMovieFragment);
//                        break;
//                    case 2:
//                        if(mMain2Fragment == null){
//                            mMain2Fragment = new Main2Fragment();
//                        }
//                        replaceFragment(mMain2Fragment);
//                        break;
//                    case 3:
//                        break;
//
//                }
//            }
//            @Override
//            public void onTabUnselected(int position) {
//            }
//            @Override
//            public void onTabReselected(int position) {
//            }
//        });



        mMovieView = (RecyclerView) findViewById(R.id.movieView);
        mBottomTabBar = (BottomTabBar) findViewById(R.id.bottom_tab_bar);
        mBottomTabBar.init(getSupportFragmentManager())
                .setImgSize(50, 50)
                .setFontSize(8)
                .setTabPadding(4, 6, 10)
                .addTabItem("今日推荐", R.mipmap.ic_launcher, MainFragment.class)
                .addTabItem("影院", R.drawable.a2, MovieFragment.class)
                .addTabItem("新闻", R.drawable.a3, Main2Fragment.class)
                .addTabItem("漫画", R.drawable.a4, TVFragment.class)
                .setTabBarBackgroundColor(Color.WHITE)
                .isShowDivider(false);

        mToolbar = (Toolbar) findViewById(R.id.main_bar);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setHomeAsUpIndicator(R.mipmap.title_bar_menu_on);
        }

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.activity_main,fragment);
        transaction.commit();
    }

    private void applyPermissions() {
        String permissions[] = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA, Manifest.permission.ACCESS_NETWORK_STATE, Manifest.permission.READ_PHONE_STATE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, 1);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_toobar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                //resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
                break;
            case R.id.search:

                break;

        }
        return true;
    }

    private void initResideMenu() {

        resideMenu = new ResideMenu(this);
        resideMenu.setBackground(R.mipmap.bg_love);
        resideMenu.attachToActivity(this);
        final String titles[] = {"个人中心", "收藏", "夜间模式", "设置"};
        int icon[] = {R.mipmap.persion, R.drawable.collect, R.mipmap.night, R.mipmap.setting};

        ResideMenuItem personItem = new ResideMenuItem(this, icon[0], titles[0]);
        personItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (BmobUser.getCurrentUser() == null) {
                    Toast.makeText(Main2Activity.this, "亲，您还没登录，请先登录！！！", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Main2Activity.this, LoginActivity.class);
                    startActivity(intent);
                } else {

                }
            }
        });
        resideMenu.addMenuItem(personItem, ResideMenu.DIRECTION_LEFT);

        ResideMenuItem collectItem = new ResideMenuItem(this, icon[1], titles[1]);
        collectItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main2Activity.this, CollectionActivity.class);
                startActivity(intent);
            }
        });
        resideMenu.addMenuItem(collectItem, ResideMenu.DIRECTION_LEFT);

        ResideMenuItem nightItem = new ResideMenuItem(this, icon[2], titles[2]);
        nightItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isNight = SPUtil.getIsNight(Main2Activity.this);
                Toast.makeText(Main2Activity.this, isNight + "", Toast.LENGTH_SHORT).show();
                if (isNight) {
                    SPUtil.updateIsNight(Main2Activity.this, false);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    //  recreate();
                } else {
                    SPUtil.updateIsNight(Main2Activity.this, true);
                    getDelegate().setLocalNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    //recreate();
                }


            }
        });
        resideMenu.addMenuItem(nightItem, ResideMenu.DIRECTION_LEFT);

        ResideMenuItem settingItem = new ResideMenuItem(this, icon[3], titles[3]);
        settingItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                initChooseTextSize();
            }
        });

        resideMenu.addMenuItem(settingItem, ResideMenu.DIRECTION_LEFT);
        //resideMenu.addIgnoredView(mViewPager);
    }


    private void initChooseTextSize() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Main2Activity.this);
        builder.setTitle("设置字体");

        builder.setSingleChoiceItems(items, SPUtil.getTextSize(Main2Activity.this), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                textSize = which;
            }
        });

        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                SPUtil.setTestSize(Main2Activity.this, textSize);
            }
        });

        builder.setNegativeButton("取消", null);
        builder.show();
    }

}
