package cn.trunch.a20180402simpleframe1;

import android.app.ActionBar;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;


public class FourthFragment extends Fragment {
    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view=inflater.inflate(R.layout.fragment_fourth,container,false);
        //首先使用view创建.fragment_first，如上
//        myListAdapter = new ListAdapter();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_1,getData());
        ListView listView = (ListView) view.findViewById(R.id.list_view);
        listView.setOverScrollMode(ListView.OVER_SCROLL_NEVER);//滑到底部顶部不显示蓝色阴影
        listView.setAdapter(arrayAdapter);//导入适配器



        listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    //重写onItemClick方法
                    public void onItemClick(AdapterView<?>adapterView,View view,int position,long l){
                        Intent intent = new Intent(getActivity(), demos[position].demoClass);
                        startActivity(intent);
                    }
                });

        return view;
    }
    private static class DemoInfo{
        private final Class<? extends android.app.Activity> demoClass;
        public DemoInfo(Class<? extends android.app.Activity> demoClass) {
            this.demoClass = demoClass;
        }
    }
    //把每个Activity转换成一个类，下面函数中，每含有一个类。其中就会赋给一个按钮点击事件。
    private static final DemoInfo[] demos = {
            new DemoInfo(MainActivity.class),//按钮0的点击事件activity
            new DemoInfo(test.class),//按钮1的点击事件activity
            new DemoInfo(test.class),
            new DemoInfo(test.class),
            new DemoInfo(test.class),
    };

    //没有onstart如何开始调用函数？
    private List<String> getData(){
        List<String> data = new ArrayList<String>();
        for(int i = 0;i<5;i++){
            data.add("选项"+i);
        }
        return data;
    }
}
