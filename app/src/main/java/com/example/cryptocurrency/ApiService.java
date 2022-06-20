package com.example.cryptocurrency;

import android.util.Log;

import com.example.cryptocurrency.model.Currency;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiService {
    private RetrofitApiService retrofitApiService;
    private static final String TAG = "ApiService";

    public ApiService() {

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://api.minerstat.com/v2/")
                .addConverterFactory(GsonConverterFactory.create()).build();

        retrofitApiService = retrofit.create(RetrofitApiService.class);
    }


    public void getCurrencies(CurrencyCallback currencyCallback) {
        retrofitApiService.getCurrencies().enqueue(new Callback<ArrayList<Currency>>() {
            @Override
            public void onResponse(Call<ArrayList<Currency>> call, Response<ArrayList<Currency>> response) {
                currencyCallback.onSuccess(response.body());
            }

            @Override
            public void onFailure(Call<ArrayList<Currency>> call, Throwable t) {
                currencyCallback.onError(new Exception(t));
            }
        });
    }

    public interface CurrencyCallback {
        void onSuccess(ArrayList<Currency> currencies);

        void onError(Exception e);
    }
}
