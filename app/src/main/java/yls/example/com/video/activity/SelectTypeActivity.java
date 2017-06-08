package yls.example.com.video.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;
import com.hitomi.cmlibrary.OnMenuStatusChangeListener;

import yls.example.com.video.R;
import yls.example.com.video.constants.MapConstant;

public class SelectTypeActivity extends AppCompatActivity {
    private CircleMenu mCircleMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_type);
        initViews();
    }

    private void initViews() {
        mCircleMenu = (CircleMenu) findViewById(R.id.circleMenu);
        mCircleMenu.openMenu();
        mCircleMenu.setMainMenu(Color.parseColor("#CDCDCD"), R.mipmap.icon_menu, R.drawable.icon_cancel)
                .addSubMenu(Color.parseColor("#30A400"), R.drawable.a1)
                .addSubMenu(Color.parseColor("#258CFF"), R.drawable.a2)
                .addSubMenu(Color.parseColor("#FF4B32"), R.drawable.a3)
                .addSubMenu(Color.parseColor("#F5F6F7"), R.drawable.a4)
                .setOnMenuSelectedListener(new OnMenuSelectedListener() {

                    @Override
                    public void onMenuSelected(int index) {
                        waitToActivity(index);

                    }

                }).setOnMenuStatusChangeListener(new OnMenuStatusChangeListener() {

            @Override
            public void onMenuOpened() {

            }

            @Override
            public void onMenuClosed() {

            }

        });
    }

    private void waitToActivity(final int index) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1300);
                    switch (index) {
                        case 1:
                            Intent myLoactionIntent = new Intent(SelectTypeActivity.this, MapActivity.class);
                            startActivity(myLoactionIntent);
                            break;
                        case 2:
                            Intent walkIntent = new Intent(SelectTypeActivity.this, MyGuideActivity.class);
                            walkIntent.putExtra(MapConstant.GUIDE_EXTRA, MapConstant.WALK);
                            startActivity(walkIntent);
                            break;

                        case 3:
                            Intent rideIntent = new Intent(SelectTypeActivity.this, MyGuideActivity.class);
                            rideIntent.putExtra(MapConstant.GUIDE_EXTRA, MapConstant.RIDE);
                            startActivity(rideIntent);
                            break;
                        case 4:
                            Intent driveIntent = new Intent(SelectTypeActivity.this, MyGuideActivity.class);
                            driveIntent.putExtra(MapConstant.GUIDE_EXTRA, MapConstant.DRIVE);
                            startActivity(driveIntent);
                            break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
