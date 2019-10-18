package com.lx.mylibrary.conn;



import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


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
                .build();
    }

    public static <T> T create(Class<T> tClass) {
        if (sRetrofit == null) {
            getInstance().init();
        }
        return sRetrofit.create(tClass);
    }

}
