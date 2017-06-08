package yls.example.com.video.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.amap.api.navi.AMapNavi;
import com.amap.api.navi.AMapNaviListener;
import com.amap.api.navi.AMapNaviView;
import com.amap.api.navi.AMapNaviViewListener;
import com.amap.api.navi.enums.NaviType;
import com.amap.api.navi.model.AMapLaneInfo;
import com.amap.api.navi.model.AMapNaviCameraInfo;
import com.amap.api.navi.model.AMapNaviCross;
import com.amap.api.navi.model.AMapNaviInfo;
import com.amap.api.navi.model.AMapNaviLocation;
import com.amap.api.navi.model.AMapNaviTrafficFacilityInfo;
import com.amap.api.navi.model.AMapServiceAreaInfo;
import com.amap.api.navi.model.AimLessModeCongestionInfo;
import com.amap.api.navi.model.AimLessModeStat;
import com.amap.api.navi.model.NaviInfo;
import com.amap.api.navi.model.NaviLatLng;
import com.autonavi.tbt.TrafficFacilityInfo;

import java.util.List;

import yls.example.com.video.R;
import yls.example.com.video.constants.MapConstant;
import yls.example.com.video.utils.SPUtil;


public class MyGuideActivity extends AppCompatActivity {
    private AMapNaviView mAMapNaviView;
    private AMapNavi mAMapNavi;
    private List<NaviLatLng> sList;
    private List<NaviLatLng> eList;
    //获取AMapNavi实例
    private float mCinemaLongtitude;
    private float mCinemaLatitude;
    private float latitude;
    private float longitude;
    private int mGuideType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_guide);
        getCinemaLocation();
        getGuideType();
        initLocation();
      //  initViews(savedInstanceState);
        mAMapNaviView = (AMapNaviView) findViewById(R.id.navi_view);
        mAMapNaviView.onCreate(savedInstanceState);
        mAMapNaviView.setAMapNaviViewListener(mAMapNaviViewListener);
        mAMapNavi = AMapNavi.getInstance(getApplicationContext());
        //添加监听回调，用于处理算路成功
        mAMapNavi.addAMapNaviListener(mAMapNaviListener);
    }

    private void getGuideType() {
        Intent intent = getIntent();
        if (intent != null) {
            mGuideType = intent.getIntExtra(MapConstant.GUIDE_EXTRA, 1001);
        }
    }

    private void getCinemaLocation() {
        mCinemaLatitude = SPUtil.getCinemaLatitude(MyGuideActivity.this);
        mCinemaLongtitude = SPUtil.getCinemaLongitude(MyGuideActivity.this);
    }

    private void initViews(Bundle savedInstanceState) {

    }

    @Override
    protected void onResume() {
        super.onResume();
        mAMapNaviView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mAMapNaviView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAMapNaviView.onDestroy();
        //since 1.6.0 不再在naviview destroy的时候自动执行AMapNavi.stopNavi();请自行执行
        mAMapNavi.stopNavi();
        mAMapNavi.destroy();
    }

    private void initLocation() {
        latitude = SPUtil.getLatitude(MyGuideActivity.this);
        longitude = SPUtil.getLongitude(MyGuideActivity.this);

        NaviLatLng latLngStart = new NaviLatLng(latitude, longitude);
        NaviLatLng latLngEnd = new NaviLatLng(mCinemaLatitude, mCinemaLongtitude);
//        sList.add(latLngStart);
//        eList.add(latLngEnd);
    }

    AMapNaviViewListener mAMapNaviViewListener = new AMapNaviViewListener() {
        @Override
        public void onNaviSetting() {

        }

        @Override
        public void onNaviCancel() {

        }

        @Override
        public boolean onNaviBackClick() {
            return false;
        }

        @Override
        public void onNaviMapMode(int i) {

        }

        @Override
        public void onNaviTurnClick() {

        }

        @Override
        public void onNextRoadClick() {

        }

        @Override
        public void onScanViewButtonClick() {

        }

        @Override
        public void onLockMap(boolean b) {

        }

        @Override
        public void onNaviViewLoaded() {

        }
    };

    AMapNaviListener mAMapNaviListener = new AMapNaviListener() {
        @Override
        public void onInitNaviFailure() {
            Log.e("aaaaaaaa", "onInitNaviFailure: " + "onInitNaviFailure");
        }

        @Override
        public void onInitNaviSuccess() {
            Log.e("aaaaaaa", "onStartNavi: " + "    onInitNaviSuccess");

//                //步行
//                switch (mGuideType) {
//                    case MapConstant.WALK:
//                        mAMapNavi.calculateWalkRoute(new NaviLatLng(latitude,longitude), new NaviLatLng(mCinemaLatitude,mCinemaLongtitude));
//                        break;
//                    case MapConstant.DRIVE:
//                        int strategy = 0;
//                        try {
//                            strategy = mAMapNavi.strategyConvert(true, false, false, false, false);
//                        } catch (Exception e) {
//                            e.printStackTrace();
//                        }
//                        //驾车
//                        mAMapNavi.calculateDriveRoute(sList, eList, null, strategy);
//                        break;
//                    case MapConstant.RIDE:
//                        //骑行
//                        mAMapNavi.calculateRideRoute(new NaviLatLng(latitude,longitude), new NaviLatLng(mCinemaLatitude,mCinemaLongtitude));
//                        break;
//                }

            mAMapNavi.calculateWalkRoute(new NaviLatLng(39.92, 116.43), new NaviLatLng(39.92, 116.53));
        }

        @Override
        public void onStartNavi(int i) {

        }

        @Override
        public void onTrafficStatusUpdate() {

        }

        @Override
        public void onLocationChange(AMapNaviLocation aMapNaviLocation) {
            Log.e("aaaaaaa", "onStartNavi: " + "    onLocationChange");
        }

        @Override
        public void onGetNavigationText(int i, String s) {

        }

        @Override
        public void onEndEmulatorNavi() {

        }

        @Override
        public void onArriveDestination() {

        }

        @Override
        public void onCalculateRouteSuccess() {
            Log.e("aaaaaaa", "onStartNavi: " + "    onCalculateRouteSuccess");
            mAMapNavi.startNavi(NaviType.GPS);
        }

        @Override
        public void onCalculateRouteFailure(int i) {
            Log.e("aaaaaaa", "onStartNavi: " + "    onCalculateRouteFailure");
        }

        @Override
        public void onReCalculateRouteForYaw() {

        }

        @Override
        public void onReCalculateRouteForTrafficJam() {

        }

        @Override
        public void onArrivedWayPoint(int i) {

        }

        @Override
        public void onGpsOpenStatus(boolean b) {

        }

        @Override
        public void onNaviInfoUpdate(NaviInfo naviInfo) {

        }

        @Override
        public void onNaviInfoUpdated(AMapNaviInfo aMapNaviInfo) {

        }

        @Override
        public void updateCameraInfo(AMapNaviCameraInfo[] aMapNaviCameraInfos) {

        }

        @Override
        public void onServiceAreaUpdate(AMapServiceAreaInfo[] aMapServiceAreaInfos) {

        }

        @Override
        public void showCross(AMapNaviCross aMapNaviCross) {

        }

        @Override
        public void hideCross() {

        }

        @Override
        public void showLaneInfo(AMapLaneInfo[] aMapLaneInfos, byte[] bytes, byte[] bytes1) {

        }

        @Override
        public void hideLaneInfo() {

        }

        @Override
        public void onCalculateMultipleRoutesSuccess(int[] ints) {

        }

        @Override
        public void notifyParallelRoad(int i) {

        }

        @Override
        public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo aMapNaviTrafficFacilityInfo) {

        }

        @Override
        public void OnUpdateTrafficFacility(AMapNaviTrafficFacilityInfo[] aMapNaviTrafficFacilityInfos) {

        }

        @Override
        public void OnUpdateTrafficFacility(TrafficFacilityInfo trafficFacilityInfo) {

        }

        @Override
        public void updateAimlessModeStatistics(AimLessModeStat aimLessModeStat) {

        }

        @Override
        public void updateAimlessModeCongestionInfo(AimLessModeCongestionInfo aimLessModeCongestionInfo) {

        }

        @Override
        public void onPlayRing(int i) {

        }
    };
}
