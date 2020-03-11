package cn.trunch.a20180402simpleframe2;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ViewPager viewPager;
    private FirstFragment firstFragment;
    private SecondFragment secondFragment;
    private FragmentManager fragmentManager;
    private List<Fragment> fragmentList;

    private TextView focus;
    private TextView hot;
    private ImageView bar;

    private int move;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar actionBar=getSupportActionBar();
        if (actionBar!=null)
            actionBar.hide();

        initObject();
        initBar();
        initViewPager();
    }
    private void initObject(){
        viewPager=(ViewPager)findViewById(R.id.main_viewPager);
        firstFragment=new FirstFragment();
        secondFragment=new SecondFragment();
        fragmentManager=getSupportFragmentManager();
        fragmentList=new ArrayList<>();

        fragmentList.add(firstFragment);
        fragmentList.add(secondFragment);

        focus=(TextView)findViewById(R.id.header_focus);
        hot=(TextView)findViewById(R.id.header_hot);
        bar=(ImageView)findViewById(R.id.header_bar);
    }
    private void initBar(){
        //测量图片bar的宽度
        int barWidth= BitmapFactory.decodeResource(getResources(),R.drawable.scrollbar).getWidth();
        //测量屏幕的宽度
        DisplayMetrics displayMetrics=new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int screenWidth=displayMetrics.widthPixels;
        //测量“关注”和“热门”的宽度
        int w= View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        int h=View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED);
        focus.measure(w,h);
        int textWidth=focus.getMeasuredWidth();
        //计算bar的移动距离
        move=textWidth;
        //计算bar的偏移距离
        int offset=(screenWidth/2-barWidth/2-textWidth/2);
        //设置bar的显示位置
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        bar.setImageMatrix(matrix);
    }
    private void initViewPager(){
        FragmentPagerAdapter fragmentPagerAdapter=new FragmentPagerAdapter(fragmentManager) {
            @Override
            public Fragment getItem(int position) {
                return fragmentList.get(position);
            }

            @Override
            public int getCount() {
                return fragmentList.size();
            }
        };
        viewPager.setAdapter(fragmentPagerAdapter);
        viewPager.setCurrentItem(1);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                Animation animation = null;
                switch (position) {
                    case 0:
                        animation = new TranslateAnimation(move, 0, 0, 0);
                        /*
                         * TranslateAnimation的四个属性分别为
                         * float fromXDelta 动画开始的点离当前View X坐标上的差值
                         * float toXDelta 动画结束的点离当前View X坐标上的差值
                         * float fromYDelta 动画开始的点离当前View Y坐标上的差值
                         * float toYDelta 动画开始的点离当前View Y坐标上的差值
                         */
                        break;
                    case 1:
                        animation = new TranslateAnimation(0, move, 0, 0);
                        break;
                }
                animation.setFillAfter(true);
                animation.setDuration(200);
                bar.startAnimation(animation);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        focus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(0);
            }
        });
        hot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewPager.setCurrentItem(1);
            }
        });
    }
}
