package yls.example.com.video.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import yls.example.com.video.R;
import yls.example.com.video.adapter.TvProgramAdapter;
import yls.example.com.video.model.TvProgramBean;

public class TvProgramActivity extends AppCompatActivity {
    //http://api.avatardata.cn/TVTime/TVlist?key=[您申请的APPKEY]&code=cctv5&date=
    //5ef0a3190d04492b9a55b52906590e11
    private RecyclerView mRecyclerView;
    private TvProgramAdapter mTvProgramAdapter;
    private List<TvProgramBean.ResultBean> mResultBeen = new ArrayList<>();
    private String code;
    private Gson mGson = new Gson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_program);

        initViews();
        initCode();
        initProgram();
    }

    private void initCode() {
        Intent intent = getIntent();
        if (intent != null) {
            code = intent.getStringExtra("channel");
        }
    }

    private void initProgram() {
        OkHttpUtils.post()
                .url("http://api.avatardata.cn/TVTime/TVlist")
                .addParams("key", "5ef0a3190d04492b9a55b52906590e11")
                .addParams("code", code)
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mResultBeen = mGson.fromJson(response.toString(), TvProgramBean.class).getResult();
                        mTvProgramAdapter.changeData(mResultBeen);
                        Toast.makeText(TvProgramActivity.this, "" + response.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void initViews() {
        mRecyclerView = (RecyclerView) findViewById(R.id.tvProgramView);
        mTvProgramAdapter = new TvProgramAdapter(mResultBeen);
        mRecyclerView.setAdapter(mTvProgramAdapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(TvProgramActivity.this));
    }
}
