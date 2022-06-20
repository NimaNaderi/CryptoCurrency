package com.example.cryptocurrency;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.cryptocurrency.model.Currency;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements ApiService.CurrencyCallback {
    private ArrayList<Currency> currencies;
    private static final String TAG = "MainActivity";
    private CurrencyAdapter currencyAdapter;
    private EditText searchEt;
    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private ImageView retryBtn;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ApiService apiService = new ApiService();
        apiService.getCurrencies(this);

        searchEt = findViewById(R.id.et_search);
        progressBar = findViewById(R.id.progress_main);
        retryBtn = findViewById(R.id.btn_main_retry);
        recyclerView = findViewById(R.id.rv_main);

        retryBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retryBtn.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                new ApiService().getCurrencies(MainActivity.this);
            }
        });

        searchEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ArrayList<Currency> currencyArrayList = new ArrayList<>();
                for (Currency currency : currencies
                ) {
                    if (currency.getName().toLowerCase(Locale.ROOT).contains(s.toString().toLowerCase(Locale.ROOT))) {
                        currencyArrayList.add(currency);
                    }
                }
                currencyAdapter.searchCoin(currencyArrayList);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        swipeRefreshLayout = findViewById(R.id.swipeRefresh_main);
        swipeRefreshLayout.setEnabled(false);
        swipeRefreshLayout.setColorSchemeColors(swipeRefreshLayout.getResources().getColor(R.color.primary, null));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ApiService().getCurrencies(MainActivity.this);
                searchEt.setText("");
            }
        });
    }

    @Override
    public void onSuccess(ArrayList<Currency> currencies) {

        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);

        swipeRefreshLayout.setEnabled(true);

        if (retryBtn.getVisibility() == View.VISIBLE)
            retryBtn.setVisibility(View.GONE);

        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        searchEt.setVisibility(View.VISIBLE);
        this.currencies = currencies;
        currencyAdapter = new CurrencyAdapter(currencies);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(currencyAdapter);

    }

    @Override
    public void onError(Exception e) {
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);

        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        retryBtn.setVisibility(View.VISIBLE);
        searchEt.setVisibility(View.GONE);

        Toast.makeText(this, "Network Error Occurred !", Toast.LENGTH_SHORT).show();
    }
}