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
import yls.example.com.video.activity.CinemaActivity;
import yls.example.com.video.model.CityBean;

/**
 * Created by yhdj on 2017/5/25.
 */

public class CityAdapter extends RecyclerView.Adapter<CityAdapter.ViewHolder> {
    private List<CityBean.ResultBean> mResultBeen = new ArrayList<>();

    public CityAdapter(List<CityBean.ResultBean> mResultBeen) {
        this.mResultBeen = mResultBeen;
    }

    @Override
    public CityAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.city_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CityAdapter.ViewHolder holder, int position) {
        CityBean.ResultBean resultBean = mResultBeen.get(position);
        holder.mTvCity.setText(resultBean.getCity_name());
        holder.mTvCinemaCount.setText(resultBean.getCount());
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(holder.mTvCity.getContext(), CinemaActivity.class);
                holder.mTvCity.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResultBeen.size();
    }

    public void changeData(List<CityBean.ResultBean> resultBeen) {
        this.mResultBeen = resultBeen;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvCity;
        private TextView mTvCinemaCount;
        private CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvCity = (TextView) itemView.findViewById(R.id.tv_city);
            mTvCinemaCount = (TextView) itemView.findViewById(R.id.tv_cinema_count);
            mCardView = (CardView) itemView.findViewById(R.id.layout_city);
        }
    }
}
