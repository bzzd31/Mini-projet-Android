package com.m2dl.mini_projet.mini_projet_android.photos;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by kelto on 21/01/16.
 */
public class ServiceGenerator {

    private static Gson gson = new GsonBuilder()
            .setDateFormat("yyyy-MM-dd'T'HH:mm:ss zzz")
            .create();
    private static RestAdapter.Builder builder = new RestAdapter.Builder()
            .setEndpoint(Constants.API_URL)
            .setClient(new OkClient(new OkHttpClient()))
            .setConverter(new GsonConverter(gson));


    public static <S> S createService(Class<S> serviceClass) {

        RestAdapter adapter = builder.build();
        return adapter.create(serviceClass);
    }
}
