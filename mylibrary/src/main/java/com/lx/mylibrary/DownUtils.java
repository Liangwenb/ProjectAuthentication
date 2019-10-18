package com.lx.mylibrary;

import com.lx.mylibrary.conn.RetrofitUtils;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownUtils {
    public interface JsonListener {
        public void onJsonListener(String json);
    }

    public static void getJson(String url, final JsonListener jsonListener) {
        RetrofitUtils.create(DownloadService.class).download(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String json = null;
                try {
                    if (response.body() == null) {
                        return;
                    }
                    json = response.body().string();
                    if (jsonListener != null) {
                        jsonListener.onJsonListener(json);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }
}
