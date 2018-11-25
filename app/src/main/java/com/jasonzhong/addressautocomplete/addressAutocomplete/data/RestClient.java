package com.jasonzhong.addressautocomplete.addressAutocomplete.data;

import android.support.annotation.NonNull;
import okhttp3.*;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.moshi.MoshiConverterFactory;

import java.io.IOException;

public enum RestClient {
    INSTANCE;

    private static final String BASE_URL = "https://maps.googleapis.com/maps/api/place/";
    //private static final String API_KEY = "AIzaSyDCd_JYnWdyEUQjfrl5VRys56sxAsrr-xE"; // feel free to use this key. It just map's key that should be private
    private final GooglePlacesClient googlePlacesClient;

    RestClient() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //.addInterceptor(new GoogleApiInterceptor())
                .build();
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .baseUrl(BASE_URL)
                .build();
        googlePlacesClient = retrofit.create(GooglePlacesClient.class);
    }

    public GooglePlacesClient getGooglePlacesClient() {
        return googlePlacesClient;

    }

    /*private static class GoogleApiInterceptor implements Interceptor {
        @Override
        public Response intercept(@NonNull Chain chain) throws IOException {
            Request request = chain.request();
            HttpUrl url = request.url().newBuilder().addQueryParameter("key", API_KEY).build();
            request = request.newBuilder().url(url).build();
            return chain.proceed(request);
        }
    }*/
}
