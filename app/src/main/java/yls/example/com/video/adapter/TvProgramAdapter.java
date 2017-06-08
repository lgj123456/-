package yls.example.com.video.adapter;

import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import yls.example.com.video.R;
import yls.example.com.video.activity.BroweTvActivity;
import yls.example.com.video.model.TvProgramBean;

/**
 * Created by yhdj on 2017/5/25.
 */

public class TvProgramAdapter extends RecyclerView.Adapter<TvProgramAdapter.ViewHolder> {
    private List<TvProgramBean.ResultBean> mResultBeen = new ArrayList<>();

    public TvProgramAdapter(List<TvProgramBean.ResultBean> mResultBeen) {
        this.mResultBeen = mResultBeen;
    }

    @Override
    public TvProgramAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.tv_program_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TvProgramAdapter.ViewHolder holder, int position) {
        final TvProgramBean.ResultBean resultBean = mResultBeen.get(position);
        holder.mTvProgramTime.setText(resultBean.getTime());
        holder.mTvProgramName.setText(resultBean.getPName());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.mCardView.getContext(), BroweTvActivity.class);
                intent.putExtra("url",resultBean.getPUrl());
                holder.mCardView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if(mResultBeen != null){
            return mResultBeen.size();
        }else{
            return 0;
        }

    }

    public void changeData(List<TvProgramBean.ResultBean> resultBeen) {
        this.mResultBeen = resultBeen;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvProgramName;
        private TextView mTvProgramTime;
        private CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvProgramName = (TextView) itemView.findViewById(R.id.programName);
            mTvProgramTime = (TextView) itemView.findViewById(R.id.programTime);
            mCardView = (CardView) itemView.findViewById(R.id.layout_program);
        }
    }
}
