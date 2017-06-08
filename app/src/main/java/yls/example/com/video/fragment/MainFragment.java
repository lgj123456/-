package yls.example.com.video.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.youth.banner.Banner;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import yls.example.com.video.R;
import yls.example.com.video.adapter.TodayMovieAdapter;
import yls.example.com.video.model.TodayMovieBean;

/**
 * Created by yhdj on 2017/5/25.
 */

public class MainFragment extends Fragment {
    //2968fe7385f8fadc5b8668810dc17efa
    private RecyclerView mMovieView;
    private String cityId = "5";
    private List<TodayMovieBean.ResultBean> mResultBeen = new ArrayList<>();
    private final String TODAY_MOVIE = "http://v.juhe.cn/movie/movies.today";
    private String cityName = "";
    private Gson mGson = new Gson();
    private TodayMovieAdapter mTodayMovieAdapter;
    private Handler mHandler;
    private final int TODAY_MOVIE_MSG = 1001;
    private Banner mBanner;
    private String[] images = {"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496934896829&di=5657ed91aa20fc62f24ef521e6581a48&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F01789956c6ea3b6ac7252ce6e7cbb5.jpg"
            ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496934896828&di=e0291d733a06e6a8c0ffb7bbded34687&imgtype=0&src=http%3A%2F%2Fimage.tianjimedia.com%2FuploadImages%2Fupload%2F20151213%2Fbps2c1wqkpyjpg.jpg"
            ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496934896826&di=5ade645ed19e909eeb83935b84c88e04&imgtype=0&src=http%3A%2F%2Fpic2.52pk.com%2Ffiles%2F160503%2F1288592_170713_1_lit.jpg"
            ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496934896824&di=c3de04b599c1a84adacacbbddc557e70&imgtype=0&src=http%3A%2F%2Fimg5.cache.netease.com%2Fent%2F2015%2F9%2F11%2F201509111043341e186_550.jpg"
            ,"https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1496934896823&di=db230e9b885ec26cb8b2d99173f19aee&imgtype=0&src=http%3A%2F%2Fpic.58pic.com%2F58pic%2F17%2F13%2F56%2F35258PICMnk_1024.jpg"
    };
    private String[] titles = {"1","2","3","4","5"};


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initHandler();
        getTodayMovieData();
    }



    private void initHandler() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == TODAY_MOVIE_MSG) {
                    mTodayMovieAdapter.changeData(mResultBeen);
                }
                return true;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        mMovieView = (RecyclerView) view.findViewById(R.id.movieView);
        mBanner = (Banner) view.findViewById(R.id.banner);

        mBanner.setBannerStyle(Banner.CIRCLE_INDICATOR_TITLE);
        mBanner.setIndicatorGravity(Banner.CENTER);
        mBanner.setBannerTitle(titles);
        mBanner.isAutoPlay(true) ;
        mBanner.setDelayTime(3000);
        mBanner.setImages(images, new Banner.OnLoadImageListener() {
            @Override
            public void OnLoadImage(ImageView view, Object url) {
                Glide.with(getContext()).load(url).into(view);
            }
        });

        initAdapter();
        return view;
    }


    private void getTodayMovieData() {
        OkHttpUtils.get()//
                .url(TODAY_MOVIE)//
                .addParams("key", "2968fe7385f8fadc5b8668810dc17efa")//
                .addParams("cityid", cityId)//
                .build()//
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.e("aaaaaa", "onError: " + e.toString());
                    }

                    //?key=2968fe7385f8fadc5b8668810dc17efa&"+cityId
                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaaaa", "onResponse: " + response.toString());
                        mResultBeen = mGson.fromJson(response.toString(), TodayMovieBean.class).getResult();
                        mHandler.sendEmptyMessage(TODAY_MOVIE_MSG);
                    }
                });
    }

    private void initAdapter() {
        mTodayMovieAdapter = new TodayMovieAdapter(mResultBeen);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2);
        mMovieView.setLayoutManager(gridLayoutManager);
        mMovieView.setAdapter(mTodayMovieAdapter);
    }

}
