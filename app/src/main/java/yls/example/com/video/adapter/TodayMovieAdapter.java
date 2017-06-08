package yls.example.com.video.adapter;

import android.animation.Animator;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewAnimationUtils;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import yls.example.com.video.R;
import yls.example.com.video.activity.MovieActivity;
import yls.example.com.video.application.MyApplication;
import yls.example.com.video.model.TodayMovieBean;

/**
 * Created by yhdj on 2017/5/25.
 */

public class TodayMovieAdapter extends RecyclerView.Adapter<TodayMovieAdapter.ViewHolder> {

    private List<TodayMovieBean.ResultBean> mResultBeen = new ArrayList<>();
    private Handler mHandler = new Handler();

    public TodayMovieAdapter(List<TodayMovieBean.ResultBean> mResultBeen) {
        this.mResultBeen = mResultBeen;
    }

    @Override
    public TodayMovieAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.today_movie_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final TodayMovieAdapter.ViewHolder holder, int position) {
        final TodayMovieBean.ResultBean resultBean = mResultBeen.get(position);
        Glide.with(holder.movieImg.getContext()).load(resultBean.getPic_url()).into(holder.movieImg);
        holder.tvMovieName.setText(resultBean.getMovieName());
        Log.e("aaa", "onBindViewHolder: " + "bbb");
        holder.movieImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String movieId = resultBean.getMovieId();
                Log.e("aaaaaaaa", "onClick: " + movieId);
                final Intent intent = new Intent(holder.movieImg.getContext(), MovieActivity.class);
                intent.putExtra(MovieActivity.MOVIE_ID, movieId);

                startAnimation(holder.movieImg);
                mHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        holder.movieImg.getContext().startActivity(intent);
                    }
                }, 1500);

            }
        });
    }

    private void startAnimation(View view) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            int centerX = (view.getLeft() + view.getRight()) / 2;
            int centerY = (view.getTop() + view.getBottom()) / 2;
            float finalRadius = (float) Math.hypot((double) centerX, (double) centerY);
            Animator mCircularReveal = ViewAnimationUtils.createCircularReveal(
                    view, centerX, centerY, 0, finalRadius);
            mCircularReveal.setDuration(2000).start();
//            int cicular_R = view.getHeight() / 2 > view.getWidth() / 2 ? view.getHeight() / 2 : view.getWidth() / 2;
//            Animator animator = ViewAnimationUtils.createCircularReveal(view, 1000, 800, 0, cicular_R);
//            animator.setDuration(1000);
//            animator.start();
        } else {
            Toast.makeText(MyApplication.getContext(), "SDK版本太低,请升级", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public int getItemCount() {
        return mResultBeen.size();
    }

    public void changeData(List<TodayMovieBean.ResultBean> resultBeen) {
        this.mResultBeen = resultBeen;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView movieImg;
        private TextView tvMovieName;

        public ViewHolder(View itemView) {
            super(itemView);
            movieImg = (ImageView) itemView.findViewById(R.id.todayMovieImg);
            tvMovieName = (TextView) itemView.findViewById(R.id.movieName);
        }
    }
}
