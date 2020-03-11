package com.example.a12785.activitylifecycletest;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12785 on 2018/2/1.
 */

public class ActivityCollector {
    public static List<Activity> activities =new ArrayList<>();
    public static void addActivity(Activity activity){
        activities.add(activity);
    }
    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }
    public static void finishAll(){
        for (Activity activity : activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }
}
