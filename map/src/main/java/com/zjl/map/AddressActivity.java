package com.zjl.map;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import com.amap.api.maps.AMap;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;

/**
 * 隐式跳转url:iconch://www.meituan.com/map?latitude=39.906901&longitude=116.397972&title=xxx&name=xxx
 */
public class AddressActivity extends Activity {

    public static final String URI_PARAM_KEY_LATITUDE = "latitude";
    public static final String URI_PARAM_KEY_LONGITUDE = "longitude";
    public static final String URI_PARAM_KEY_NAME = "name";
    public static final String URI_PARAM_KEY_TITLE = "title";

    private MapView mMapView = null;
    private AMap mAMap;

    private MapMarkerInfoView mInfoView;

    private String mLatitude;
    private String mLongitude;
    private String mName;
    private String mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        //获取地图控件引用
        mMapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mMapView.onCreate(savedInstanceState);

        //初始化地图控制器对象
        mAMap = mMapView.getMap();

        resolveIntentData();

        drawMarker();
    }

    private void resolveIntentData() {
        Uri uri = getIntent().getData();
        mLatitude = uri.getQueryParameter(URI_PARAM_KEY_LATITUDE);
        mLongitude = uri.getQueryParameter(URI_PARAM_KEY_LONGITUDE);
        mName = uri.getQueryParameter(URI_PARAM_KEY_NAME);
        mTitle = uri.getQueryParameter(URI_PARAM_KEY_TITLE);
    }

    private void drawMarker() {
        double lat = 0;
        double lnt = 0;
        try {
            lat = Double.valueOf(mLatitude);
            lnt = Double.valueOf(mLongitude);
        } catch (Exception e) {
            return;
        }

        LatLng latLng = new LatLng(lat, lnt);
        MarkerOptions options = new MarkerOptions()
                .position(latLng)
                .title("")//NOTE: title和snippet都不设置会导致InfoWindow不显示
                .snippet("")
                .draggable(false)
                .visible(true)
                .setInfoWindowOffset(0, getResources().getDimensionPixelSize(R.dimen.map_infoview_offset_y))
                .icon(BitmapDescriptorFactory.fromBitmap(
                        Bitmap.createScaledBitmap(
                                BitmapFactory.decodeResource(getResources(), R.drawable.map_marker),
                                getResources().getDimensionPixelSize(R.dimen.map_marker_width),
                                getResources().getDimensionPixelSize(R.dimen.map_marker_height),
                                false
                        )
                ));
        Marker marker = mAMap.addMarker(options);

        mAMap.setInfoWindowAdapter(new AMap.InfoWindowAdapter() {
            @Override
            public View getInfoWindow(Marker marker) {
                if (mInfoView == null) {
                    mInfoView = new MapMarkerInfoView(AddressActivity.this);
                    //必须要给InfoView设置background，否则高德sdk会为InfoView添加边框
                    mInfoView.setBackgroundColor(Color.TRANSPARENT);
                    mInfoView.setOnNavClickListener(new MapMarkerInfoView.OnNavClickListener() {
                        @Override
                        public void onNavClick(MapMarkerInfoView view) {
                            Uri geoLocation = Uri.parse("geo:" + mLatitude + "," + mLongitude);
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(geoLocation);
                            if (intent.resolveActivity(getPackageManager()) != null) {
                                startActivity(intent);
                            }
                        }
                    });
                }

                mInfoView.setInfo(mName);

                return mInfoView;
            }

            @Override
            public View getInfoContents(Marker marker) {
                return null;
            }
        });
        marker.showInfoWindow();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mMapView.onDestroy();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mMapView.onPause();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mMapView.onSaveInstanceState(outState);
    }
}
