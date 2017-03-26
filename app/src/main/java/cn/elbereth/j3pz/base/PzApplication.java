package cn.elbereth.j3pz.base;

import android.app.Application;
import android.content.SharedPreferences;

/**
 * @author Kang S.
 * @author cloudpoet@163.com
 * Created on 2017/1/19.
 */

public class PzApplication extends Application {
    private static PzApplication INSTANCE;

    @Override
    public void onCreate() {
        super.onCreate();
        INSTANCE = this;
    }

    public static PzApplication getInstance() {
        return INSTANCE;
    }

    public SharedPreferences getDefaultPref() {
        return getSharedPreferences("data", MODE_PRIVATE);
    }
}
