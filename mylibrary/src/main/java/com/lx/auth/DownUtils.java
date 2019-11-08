package com.lx.auth;


import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DownUtils {
    public interface JsonListener {
        public void onJsonListener(String json);
    }

    public static void getJson(final String url, final JsonListener jsonListener) {

        RetrofitUtils.create(DownloadService.class).download(url).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, final Response<ResponseBody> response) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
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
                }).start();


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
