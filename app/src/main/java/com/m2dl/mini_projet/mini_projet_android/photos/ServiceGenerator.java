package com.m2dl.mini_projet.mini_projet_android.photos;

import com.squareup.okhttp.OkHttpClient;

import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;

/**
 * Created by kelto on 21/01/16.
 */
public class ServiceGenerator {

    //check if data should be removed


    private static RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(Constants.API_URL)
            .setClient(new OkClient(new OkHttpClient()));

    public static <S> S createService(Class<S> serviceClass) {
        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }
}
