package com.lx.auth.update;

import android.app.Activity;
import android.content.DialogInterface;

import androidx.appcompat.app.AlertDialog;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.lx.base.utils.RetrofitUtils;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;


public class UpdateUtils {

    public static class Info<C extends Activity> {

        public static Class UPDATE_ACTIVITY;
        static String _API_KEY = "d4525b1c14af4a44bba3eb7d3d6518aa";
        static String APP_KEY;
    }

    public static <C extends Activity> void init(String _API_KEY, String APP_KEY, Class<C> activityClass) {
        Info._API_KEY = _API_KEY;
        Info.APP_KEY = APP_KEY;
        Info.UPDATE_ACTIVITY = activityClass;
        detect();
    }

    public static void init(String _API_KEY, String APP_KEY) {
        init(_API_KEY, APP_KEY, null);
    }

    interface ApkSercice {
        @FormUrlEncoded
        @POST("https://www.pgyer.com/apiv2/app/check")
        Call<UpdataBean> detect(@Field("_api_key") String apiKey, @Field("appKey") String appKey);
    }

    private static void detect() {
        RetrofitUtils.create(ApkSercice.class).detect(Info._API_KEY, Info.APP_KEY).enqueue(new Callback<UpdataBean>() {
            @Override
            public void onResponse(Call<UpdataBean> call, Response<UpdataBean> response) {
                if (response.isSuccessful() && response.body() != null) {
                    final UpdataBean.DataBean data = response.body().getData();
                    //需要更新的情况下
                    if (AppUtils.getAppVersionCode() < Integer.parseInt(data.getBuildVersionNo())) {
                        if (Info.UPDATE_ACTIVITY != null && ActivityUtils.getTopActivity().getClass() == Info.UPDATE_ACTIVITY) {
                            showDialog(data);
                        }
                    }
                }


            }

            @Override
            public void onFailure(Call<UpdataBean> call, Throwable t) {

            }
        });

    }

    private static void showDialog(final UpdataBean.DataBean data) {
        AlertDialog dialog = null;
        AlertDialog.Builder builder = new AlertDialog.Builder(ActivityUtils.getTopActivity());
        builder.setTitle("应用升级");
        if (data.getBuildUpdateDescription() != null && !data.getBuildUpdateDescription().equals("")) {
            builder.setMessage(data.getBuildUpdateDescription());
        } else {
            builder.setMessage("发现新版本");
        }
        dialog = builder.setNegativeButton("下次再说", null).setPositiveButton("马上升级", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                downApk(data.getDownloadURL());
                dialog.dismiss();
            }
        }).setCancelable(false).show();

    }

    private static void downApk(String apkUrl) {
        DownUtils.down(apkUrl);
    }

}
