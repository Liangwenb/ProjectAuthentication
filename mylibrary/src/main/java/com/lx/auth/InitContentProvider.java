package com.lx.auth;

import android.app.ActivityManager;
import android.app.Application;
import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.blankj.utilcode.util.LogUtils;
import com.lx.auth.update.ActivityLifecycleCallbackIml;

import org.json.JSONObject;

import java.util.List;


public class InitContentProvider extends ContentProvider {
    @Override
    public boolean onCreate() {
        Application application = (Application) getContext().getApplicationContext();
        application.registerActivityLifecycleCallbacks(new ActivityLifecycleCallbackIml());
        DownUtils.getJson("https://github.com/Liangwenb/ProjectAuthentication/releases/download/1.0.1/json.json", new DownUtils.JsonListener() {
            @Override
            public void onJsonListener(String json) {
                try {
                    if (json != null && json.length() > 0) {
                        JSONObject jsonObject = new JSONObject(json);
                        boolean aBoolean = jsonObject.getBoolean(getContext().getPackageName());
                        if (!aBoolean) {
                            killAppProcess();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        return null;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        return 0;
    }

    public void killAppProcess() {
        //注意：不能先杀掉主进程，否则逻辑代码无法继续执行，需先杀掉相关进程最后杀掉主进程
        ActivityManager mActivityManager = (ActivityManager) getContext().getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> mList = mActivityManager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo runningAppProcessInfo : mList) {
            if (runningAppProcessInfo.pid != android.os.Process.myPid()) {
                android.os.Process.killProcess(runningAppProcessInfo.pid);
            }
        }
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(0);
    }


}
