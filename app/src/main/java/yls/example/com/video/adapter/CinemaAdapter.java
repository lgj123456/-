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
import yls.example.com.video.activity.SelectTypeActivity;
import yls.example.com.video.application.MyApplication;
import yls.example.com.video.model.CinemaBean;
import yls.example.com.video.utils.SPUtil;

/**
 * Created by yhdj on 2017/5/25.
 */

public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.ViewHolder> {
    private List<CinemaBean.ResultBean> mResultBeen = new ArrayList<>();
    private int images[] = {R.drawable.a1, R.drawable.a2, R.drawable.a3, R.drawable.a4};
    private int index = 0;

    public CinemaAdapter(List<CinemaBean.ResultBean> mResultBeen) {
        this.mResultBeen = mResultBeen;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cinema_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CinemaBean.ResultBean resultBean = mResultBeen.get(position);
        holder.mTvCinemaName.setText(resultBean.getCinemaName());
        holder.mTvCinemaRoutes.setText(resultBean.getTrafficRoutes());
        holder.mTvCinemaPhone.setText(resultBean.getTelephone());
        holder.mTvCinemaAddress.setText(resultBean.getAddress());
        holder.mTvCityName.setText(resultBean.getCityName());
        if (resultBean.getDistance() != 0) {
            //  holder.mTvCinemaDistance.setText(resultBean.getDistance());
        }
        holder.mCardView.setBackgroundResource(images[index % images.length]);
        holder.mCardView.setAlpha(0.5f);
        index++;
        holder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SPUtil.setCinemaLatitude(MyApplication.getContext(), Float.valueOf(resultBean.getLatitude()));
                SPUtil.setCinemaLongitude(MyApplication.getContext(), Float.valueOf(resultBean.getLongitude()));
                Intent intent = new Intent(MyApplication.getContext(), SelectTypeActivity.class);
                holder.mCardView.getContext().startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mResultBeen.size();
    }

    public void changeData(List<CinemaBean.ResultBean> resultBeen) {
        this.mResultBeen = resultBeen;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvCityName;
        private TextView mTvCinemaName;
        private TextView mTvCinemaAddress;
        private TextView mTvCinemaPhone;
        private TextView mTvCinemaDistance;
        private TextView mTvCinemaRoutes;
        private CardView mCardView;

        public ViewHolder(View itemView) {
            super(itemView);
            mTvCityName = (TextView) itemView.findViewById(R.id.tv_city);
            mTvCinemaAddress = (TextView) itemView.findViewById(R.id.tv_cinema_adress);
            mTvCinemaDistance = (TextView) itemView.findViewById(R.id.tv_cinema_distance);
            mTvCinemaPhone = (TextView) itemView.findViewById(R.id.tv_cinema_phone);
            mTvCinemaRoutes = (TextView) itemView.findViewById(R.id.tv_cinema_routes);
            mTvCinemaName = (TextView) itemView.findViewById(R.id.tv_cinema_name);
            mCardView = (CardView) itemView.findViewById(R.id.cinemaCardView);
        }
    }
}
