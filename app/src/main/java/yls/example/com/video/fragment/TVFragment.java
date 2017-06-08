package yls.example.com.video.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import yls.example.com.video.adapter.TvChannelAdapter;
import yls.example.com.video.model.TvChannelBean;

/**
 * Created by yhdj on 2017/5/25.
 */

public class TVFragment extends Fragment {
    //5ef0a3190d04492b9a55b52906590e11
    // http://api.avatardata.cn/TVTime/LookUp?key=[您申请的APPKEY]&pId=1
    private RecyclerView mRecyclerView;
    private TvChannelAdapter mTvChannelAdapter;
    private List<TvChannelBean.ResultBean> mResultBeen = new ArrayList<>();
    private Handler mHandler;
    private final int GET_CHANNEL = 1001;
    private Gson mGson = new Gson();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHandler();
        getChannel();
    }

    private void getChannel() {
        OkHttpUtils.get()
                .url("http://api.avatardata.cn/TVTime/LookUp")
                .addParams("key", "5ef0a3190d04492b9a55b52906590e11")
                .addParams("pId", "2")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onError(Call call, Exception e, int id) {

                    }

                    @Override
                    public void onResponse(String response, int id) {
                        mResultBeen = mGson.fromJson(response.toString(), TvChannelBean.class).getResult();
                        mHandler.sendEmptyMessage(GET_CHANNEL);
                    }
                });
    }

    private void initHandler() {
        mHandler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if (msg.what == GET_CHANNEL) {
                    mTvChannelAdapter.changeData(mResultBeen);
                }
                return true;
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tv_fragment, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.tv_view);
        mTvChannelAdapter = new TvChannelAdapter(mResultBeen);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mTvChannelAdapter);
        return view;
    }
}
