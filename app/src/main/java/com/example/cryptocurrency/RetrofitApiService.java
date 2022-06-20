package com.example.cryptocurrency;

import com.example.cryptocurrency.model.Currency;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApiService {

    @GET("coins?list=BTC,BCH,BSV,ETH,DOGE,BBR,BCD,BINANCE%20BTC,SPIDER%20BTC,STC,SUGAR,TAJ,TDC,TON,TRC,TTN,TUBE,AE,RTM,SMART")
    Call<ArrayList<Currency>> getCurrencies();
}
