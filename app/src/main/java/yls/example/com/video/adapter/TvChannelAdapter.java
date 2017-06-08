package yls.example.com.video.adapter;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yls.example.com.video.R;
import yls.example.com.video.activity.TvProgramActivity;
import yls.example.com.video.model.TvChannelBean;

/**
 * Created by yhdj on 2017/5/25.
 */

public class TvChannelAdapter extends RecyclerView.Adapter<TvChannelAdapter.ViewHolder> {
    private List<TvChannelBean.ResultBean> mResultBeen = new ArrayList<>();

    public TvChannelAdapter(List<TvChannelBean.ResultBean> mResultBeen) {
        this.mResultBeen = mResultBeen;
    }

    @Override
    public TvChannelAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_channel_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TvChannelAdapter.ViewHolder holder, int position) {
        final TvChannelBean.ResultBean resultBean = mResultBeen.get(position);
        holder.mTextView.setText(resultBean.getChannelName());
        holder.mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.mTextView.getContext(), TvProgramActivity.class);
                intent.putExtra("channel",resultBean.getRel());
                holder.mTextView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResultBeen.size();
    }

    public void changeData(List<TvChannelBean.ResultBean> resultBeen) {
        this.mResultBeen = resultBeen;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.tvChannel);
        }
    }
}
