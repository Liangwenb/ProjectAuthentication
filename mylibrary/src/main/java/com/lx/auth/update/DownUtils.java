package com.lx.auth.update;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.LogUtils;
import com.lx.auth.DownloadService;
import com.lx.auth.RetrofitUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownUtils {
    public static void down(String url) {
        RetrofitUtils.create(DownloadService.class).download(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            InputStream is = response.body().byteStream();
                            writeFile(is, response.body().contentLength());
                        }
                    }).start();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private static void writeFile(InputStream is, long totalLength) {
        FileOutputStream fileOutputStream = null;
        try {
            File externalCacheDir = ActivityUtils.getTopActivity().getApplication().getExternalCacheDir();
            LogUtils.d(externalCacheDir.getAbsolutePath());
            File file = new File(externalCacheDir, "app.apk");
            if (file.exists()) {
                file.delete();
            }
            file.createNewFile();
            fileOutputStream = new FileOutputStream(file);
            long currentLength = 0;
            int len;
            byte[] buff = new byte[2048];
            while ((len = is.read(buff)) != -1) {
                fileOutputStream.write(buff, 0, len);
                currentLength += len;
                LogUtils.d((int) (100 * currentLength / totalLength) + " %");
                NotificationUtils.showNotification("正在下载 ： " + (int) (100 * currentLength / totalLength) + " %");
            }
            NotificationUtils.cancel();
            AppUtils.installApp(file);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                fileOutputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }
}
