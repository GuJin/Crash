package tech.gujin.crash.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import com.tencent.bugly.crashreport.CrashReport;

import tech.gujin.crash.handler.CrashHandler;

public class MyApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    public static Context sContext;

    @Override
    public void onCreate() {
        super.onCreate();
        sContext = getApplicationContext();
        // init CrashHandler
        CrashHandler.getInstance().init();
        // init Bugly
        CrashReport.initCrashReport(this, "your Bugly App ID", false);
    }
}
