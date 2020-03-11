package com.example.a12785.ditu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.amap.api.location.AMapLocation;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;

import com.amap.api.location.AMapLocationListener;
import com.amap.api.maps.AMap;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.MapView;
import com.amap.api.maps.UiSettings;
import com.amap.api.maps.model.MyLocationStyle;

public class MainActivity extends AppCompatActivity {
    private AMap aMap;
    private MapView mapView;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {//菜单栏的设置
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;//显示菜单栏
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//屏幕右上角 菜单栏中的地图选择操作，，，，aMap是地图控制器对象。
        switch(item.getItemId()){
            case R.id.normal:
                aMap.setMapType(AMap.MAP_TYPE_NORMAL);//白昼地图，aMap是地图控制器对象。
                break;
            case R.id.yejing:
                aMap.setMapType(AMap.MAP_TYPE_NIGHT);//夜景地图，aMap是地图控制器对象。
                break;
            case R.id.weixing:
                aMap.setMapType(AMap.MAP_TYPE_SATELLITE);// 设置卫星地图模式，aMap是地图控制器对象。
                break;
            case R.id.shikuang:
                aMap.setTrafficEnabled(true);//显示实时路况图层，aMap是地图控制器对象。
               break;
            case R.id.daohang:
                aMap.setMapType(AMap.MAP_TYPE_NAVI);//导航地图，aMap是地图控制器对象。
               default:
        }
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapView = (MapView) findViewById(R.id.map);
        mapView.onCreate(savedInstanceState);// 此方法必须重写
        aMap = mapView.getMap();//高德地图自带的AMap类，地图的总控制器，此处getMap获取AMap的实例


//        //UiSettings 主要是对地图上的控件的管理，比如指南针、logo位置（不能隐藏）.....
        UiSettings settings =  aMap.getUiSettings();//设置UI的界面设置
        settings.setMyLocationButtonEnabled(true);//添加显示定位的标识符，指南针
        //添加指南针
        settings.setCompassEnabled(true);
//
//        //设置了定位的监听,这里要实现LocationSource接口
//        aMap.setLocationSource(this);
//
//        // 是否显示定位按钮（据我所知不能更改，知道的麻烦告我一声）

//
//        aMap.getCameraPosition(); //方法可以获取地图的旋转角度
//
//
//        //管理缩放控件
//        settings.setZoomControlsEnabled(true);
//        //设置logo位置，左下，底部居中，右下（隐藏logo：settings.setLogoLeftMargin(9000)）
//        settings.setLogoPosition(AMapOptions.LOGO_POSITION_BOTTOM_LEFT);
//        //设置显示地图的默认比例尺
//        settings.setScaleControlsEnabled(true);
//        //每像素代表几米
//        //float scale = aMap.getScalePerPixel();
//
//        aMap.setMyLocationEnabled(true);//显示定位层并且可以触发定位,默认是flase



        //下面实现地图上显示蓝点

        MyLocationStyle myLocationStyle;//地图的style
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        //aMap.getUiSettings().setMyLocationButtonEnabled(true);设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。

//        MyLocationStyle showMyLocation(boolean visible)//设置是否显示定位小蓝点，用于满足只想使用定位，不想使用定位小蓝点的场景，设置false以后图面上不再有定位
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转, 并且会跟随设备移动。（1秒1次定位）默认执行此种模式。
        //上面显示配置蓝点


    }
//    //下面为2018.10.9的修改
//    //请在主线程中声明AMapLocationClient类对象，需要传Context类型的参数。推荐用getApplicationContext()方法获取全进程有效的context。
//    //声明AMapLocationClient类对象
//    public AMapLocationClient mLocationClient = null;
//    //声明定位回调监听器
//    public AMapLocationListener mLocationListener = new AMapLocationListener() {
//        @Override
//        public void onLocationChanged(AMapLocation aMapLocation) {
//
//        }
//    };
////初始化定位
//    mLocationClient = new AMapLocationClient(getApplicationContext());
////设置定位回调监听
//        mLocationClient.setLocationListener(mLocationListener)；
//
//    //声明AMapLocationClientOption对象
//    public AMapLocationClientOption mLocationOption = null;
////初始化AMapLocationClientOption对象
//    mLocationOption = new AMapLocationClientOption();
//    AMapLocationClientOption option = new AMapLocationClientOption();
//    /**
//     * 设置定位场景，目前支持三种场景（签到、出行、运动，默认无场景）
//     */
//    option.setLocationPurpose(AMapLocationClientOption.AMapLocationPurpose.SignIn);
//    if(null != locationClient){
//        locationClient.setLocationOption(option);
//        //设置场景模式后最好调用一次stop，再调用start以保证场景模式生效
//        locationClient.stopLocation();
//        locationClient.startLocation();
//        //设置定位模式为AMapLocationMode.Hight_Accuracy，高精度模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        //设置定位模式为AMapLocationMode.Battery_Saving，低功耗模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Battery_Saving);
//        //设置定位模式为AMapLocationMode.Device_Sensors，仅设备模式。
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Device_Sensors);
//        //获取一次定位结果：
////该方法默认为false。
//        mLocationOption.setOnceLocation(true);
//
////获取最近3s内精度最高的一次定位结果：
////设置setOnceLocationLatest(boolean b)接口为true，启动定位时SDK会返回最近3s内精度最高的一次定位结果。如果设置其为true，setOnceLocation(boolean b)接口也会被设置为true，反之不会，默认为false。
//        mLocationOption.setOnceLocationLatest(true);
//
//    }
////设置定位间隔,单位毫秒,默认为2000ms，最低1000ms。
//mLocationOption.setInterval(1000);
//    //设置是否返回地址信息（默认返回地址信息）
//mLocationOption.setNeedAddress(true);
////设置是否允许模拟位置,默认为true，允许模拟位置
//mLocationOption.setMockEnable(true);
////单位是毫秒，默认30000毫秒，建议超时时间不要低于8000毫秒。
//mLocationOption.setHttpTimeOut(20000);
////关闭缓存机制
//mLocationOption.setLocationCacheEnable(false);
////给定位客户端对象设置定位参数
//mLocationClient.setLocationOption(mLocationOption);
////启动定位
//mLocationClient.startLocation();
//    //以下为后者的举例：
//    AMapLocationListener mAMapLocationListener = new AMapLocationListener(){
//        @Override
//        public void onLocationChanged(AMapLocation amapLocation) {
//
//        }
//    }
//    if (amapLocation != null) {
//        if (amapLocation.getErrorCode() == 0) {
////可在其中解析amapLocation获取相应内容。
//        }else {
//            //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
//            Log.e("AmapError","location Error, ErrCode:"
//                    + amapLocation.getErrorCode() + ", errInfo:"
//                    + amapLocation.getErrorInfo());
//        }
//    }
    }


