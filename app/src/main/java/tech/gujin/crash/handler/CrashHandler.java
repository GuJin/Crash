package tech.gujin.crash.handler;

import android.content.Intent;
import android.database.sqlite.SQLiteFullException;

import tech.gujin.crash.activity.CrashHintActivity;
import tech.gujin.crash.application.MyApplication;

public class CrashHandler implements Thread.UncaughtExceptionHandler {

    private Thread.UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;

    public static CrashHandler getInstance() {
        return CrashHandlerHolder.INSTANCE;
    }

    public void init() {
        /*
        * 弹出解决方案之后把崩溃继续交给系统处理，
        * 所以保存当前UncaughtExceptionHandler用于崩溃发生时使用。
        */
        mDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (ex instanceof SQLiteFullException) {
            showCrashHintActivity(ex);
        }
        // 传递给保存的UncaughtExceptionHandler
        mDefaultUncaughtExceptionHandler.uncaughtException(thread, ex);
    }

    private void showCrashHintActivity(Throwable ex) {
        Intent intent = new Intent(MyApplication.sContext, CrashHintActivity.class);
        intent.putExtra(CrashHintActivity.THROWABLE, ex);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        MyApplication.sContext.startActivity(intent);
    }

    private static class CrashHandlerHolder {
        static final CrashHandler INSTANCE = new CrashHandler();
    }

    private CrashHandler() {
    }
}
