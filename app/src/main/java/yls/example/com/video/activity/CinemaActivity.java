package yls.example.com.video.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

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

public class CinemaActivity extends AppCompatActivity {
    //http://v.juhe.cn/movie/cinemas.local?key=您申请的key&dtype=json&lat=31.30947&lon=120.6003&radius=200
    //2968fe7385f8fadc5b8668810dc17efa   2968fe7385f8fadc5b8668810dc17efa
    private float latitude;
    private float longitude;
    private RecyclerView mRecyclerView;
    private List<CinemaBean.ResultBean> mResultBeen = new ArrayList<>();
    private Gson mGson = new Gson();
    private CinemaAdapter mCinemaAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);

        initViews();
        initLocation();
        initCinemaData();
    }

    private void initCinemaData() {
        OkHttpUtils.get()
                .url("http://v.juhe.cn/movie/cinemas.local")
                .addParams("key", "2968fe7385f8fadc5b8668810dc17efa")
                .addParams("lat", latitude + "")
                .addParams("lon", longitude + "")
                .addParams("radius", "3000")
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
        latitude = SPUtil.getLatitude(CinemaActivity.this);
        longitude = SPUtil.getLongitude(CinemaActivity.this);
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.cinema_view);
        mCinemaAdapter = new CinemaAdapter(mResultBeen);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(CinemaActivity.this));
        mRecyclerView.setAdapter(mCinemaAdapter);

    }
}
