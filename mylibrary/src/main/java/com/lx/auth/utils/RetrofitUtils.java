package com.lx.auth.utils;


import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;


public class RetrofitUtils {
    protected final String TAG = getClass().getSimpleName();

    private static RetrofitUtils sRetrofitUtils = new RetrofitUtils();
    private static Retrofit sRetrofit;

    public static RetrofitUtils getInstance() {
        return sRetrofitUtils;
    }

    private OkHttpClient getOkhttpClient() {
        long timeOut = 1000 * 60 * 3;
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .writeTimeout(timeOut, TimeUnit.MILLISECONDS)
                .readTimeout(timeOut, TimeUnit.MILLISECONDS)
                .callTimeout(timeOut, TimeUnit.MILLISECONDS);
        return builder.build();

    }

    List<Interceptor> mInterceptorList = new ArrayList<>();

    public void setOkHttpInterceptor(Interceptor interceptor) {
        mInterceptorList.add(interceptor);
    }

    private void init() {
        sRetrofit = new Retrofit.Builder()
                .baseUrl("http://www.lx.cc")
                .client(getOkhttpClient())
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .build();
    }

    public static <T> T create(Class<T> tClass) {
        if (sRetrofit == null) {
            getInstance().init();
        }
        return sRetrofit.create(tClass);
    }

}
