package yls.example.com.video.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import yls.example.com.video.R;
import yls.example.com.video.adapter.CinemaAdapter;
import yls.example.com.video.model.CinemaBean;
import yls.example.com.video.utils.SPUtil;

/**
 * Created by yhdj on 2017/5/25.
 */

public class MovieFragment extends Fragment {
    //http://v.juhe.cn/movie/citys?key=您申请的key
    //2968fe7385f8fadc5b8668810dc17efa
//    private String cinemaUrl = "http://m.maoyan.com/cinemas.json";
//    private List<CityBean.ResultBean> mResultBeen = new ArrayList<>();
//    private Handler mHandler;
//    private RecyclerView mRecyclerView;
//    private CityAdapter mCityAdapter;
//    private final int GET_CITY = 1001;
//    private Gson mGson = new Gson();


    private float latitude;
    private float longitude;
    private RecyclerView mRecyclerView;
    private List<CinemaBean.ResultBean> mResultBeen = new ArrayList<>();
    private Gson mGson = new Gson();
    private CinemaAdapter mCinemaAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        initHandler();
//        getCities();

        initLocation();
        initCinemaData();
    }

//    private void getCities() {
//        OkHttpUtils.get().url(cinemaUrl)
//                .build()
//                .execute(new StringCallback() {
//                    @Override
//                    public void onError(Call call, Exception e, int id) {
//
//                    }
//
//                    @Override
//                    public void onResponse(String response, int id) {
//                        mResultBeen = mGson.fromJson(response.toString(), CityBean.class).getResult();
//                        mHandler.sendEmptyMessage(GET_CITY);
//                    }
//                });
//    }

//    private void initHandler() {
//        mHandler = new Handler(new Handler.Callback() {
//            @Override
//            public boolean handleMessage(Message msg) {
//                if (msg.what == GET_CITY) {
//                    mCityAdapter.changeData(mResultBeen);
//                }
//                return true;
//            }
//        });
//    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_cinema, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.cinema_view);
        mCinemaAdapter = new CinemaAdapter(mResultBeen);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mCinemaAdapter);
        return view;
    }


    private void initCinemaData() {
        OkHttpUtils.get()
                .url("http://v.juhe.cn/movie/cinemas.local")
                .addParams("key", "2968fe7385f8fadc5b8668810dc17efa")
                .addParams("lat", latitude + "")
                .addParams("lon", longitude + "")
                .addParams("radius", "5000")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mResultBeen = mGson.fromJson(response.toString(), CinemaBean.class).getResult();
                        mCinemaAdapter.changeData(mResultBeen);
                    }
                });
    }



    private void initLocation() {
        latitude = SPUtil.getLatitude(getContext());
        longitude = SPUtil.getLongitude(getContext());
    }
}
