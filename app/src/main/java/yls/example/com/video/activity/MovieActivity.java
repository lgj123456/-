package yls.example.com.video.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;
import yls.example.com.video.R;
import yls.example.com.video.model.MovieDetailBean;

public class MovieActivity extends AppCompatActivity {
    //2968fe7385f8fadc5b8668810dc17efa
    public static final String MOVIE_ID = "MOVIE_ID";
    //http://v.juhe.cn/movie/query?key=2968fe7385f8fadc5b8668810dc17efa&movieid=246564
    private Toolbar mToolbar;
    private CollapsingToolbarLayout mCollapsingToolbarLayout;
    private ImageView movieImageView;
    private TextView movieContent;
    private String movieId = "";
    private String movieUrl = "http://v.juhe.cn/movie/query";
    private MovieDetailBean.ResultBean mResultBean;
    private Gson mGson = new Gson();
    private TextView movieIntroduction;
    private NestedScrollView mNestedScrollView;
    private Handler mHandler;
    private static final int CHANGE_BACKGROUND = 1001;
    private int images[] = {R.mipmap.poster1, R.mipmap.poster2, R.mipmap.poster3, R.mipmap.poster4, R.mipmap.poster5};
    private int index = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie);
        initHandler();
        getMovieId();
        initViews();
        initMovieData();
    }

    private void initHandler() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == CHANGE_BACKGROUND) {
                    mNestedScrollView.setBackgroundResource(images[index % images.length]);
                    index++;
                }
                return true;
            }
        });
    }

    private void initMovieData() {
        OkHttpUtils.get().url(movieUrl)
                .addParams("key", "2968fe7385f8fadc5b8668810dc17efa")
                .addParams("movieid", movieId)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Toast.makeText(MovieActivity.this, "" + e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(String response, int id) {
                        Log.e("aaaaaaaaaaa", "onResponse: " + response.toString());
                        mResultBean = mGson.fromJson(response.toString(), MovieDetailBean.class).getResult();
                        // Toast.makeText(MovieActivity.this, ""+mResultBean.getDirectors(), Toast.LENGTH_SHORT).show();
                        if (mResultBean == null) {
                            Toast.makeText(MovieActivity.this, "抱歉，暂无相关记录！！！", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                        mCollapsingToolbarLayout.setTitle(mResultBean.getTitle());
                        Glide.with(MovieActivity.this).load(mResultBean.getPoster()).into(movieImageView);
                        movieContent.setText("    " + mResultBean.getPlot_simple());
                        movieIntroduction.setText("影片名称：  " + mResultBean.getTitle() + "\n"
                                + "影片演员：  " + mResultBean.getActors() + "\n"
                                + "分类：  " + mResultBean.getGenres() + "\n"
                                + "导演：  " + mResultBean.getDirectors() + "\n"
                                + "时间：  " + mResultBean.getYear()
                        );
                    }
                });
    }

    private void getMovieId() {
        Intent intent = getIntent();
        if (intent != null) {
            movieId = intent.getStringExtra(MOVIE_ID);
            Log.e("aaaaaaaaaaaaaa", "getMovieId: " + movieId);
        }
    }

    private void initViews() {

        movieIntroduction = (TextView) findViewById(R.id.tv_movie_introduction);
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mCollapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.colapsing_toolbar);
        movieImageView = (ImageView) findViewById(R.id.movie_img_view);
        movieContent = (TextView) findViewById(R.id.tv_movie_content);
        mNestedScrollView = (NestedScrollView) findViewById(R.id.nestedScrollview);
        setSupportActionBar(mToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        changeBackground();
    }

    private void changeBackground() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    mHandler.sendEmptyMessage(CHANGE_BACKGROUND);
                }
            }
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
