package com.example.cryptocurrency;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cryptocurrency.model.Currency;

import java.util.ArrayList;

public class CurrencyAdapter extends RecyclerView.Adapter<CurrencyAdapter.CurrencyViewHolder> {
    private ArrayList<Currency> currencies;
    private static final String TAG = "CurrencyAdapter";

    public CurrencyAdapter(ArrayList<Currency> currencies) {
        this.currencies = currencies;
    }

    public void searchCoin(ArrayList<Currency> searchedCurrencies) {
        this.currencies = searchedCurrencies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CurrencyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CurrencyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_currency, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull CurrencyViewHolder holder, int position) {
        holder.bindCurrency(currencies.get(position));
    }

    @Override
    public int getItemCount() {
        return currencies.size();
    }

    public class CurrencyViewHolder extends RecyclerView.ViewHolder {

        TextView coinTitleTv;
        TextView coinNameTv;
        TextView coinPriceTv;
        TextView coinNumberTv;

        public CurrencyViewHolder(@NonNull View itemView) {
            super(itemView);

            coinTitleTv = itemView.findViewById(R.id.tv_coinTitle);
            coinNameTv = itemView.findViewById(R.id.tv_coinName);
            coinPriceTv = itemView.findViewById(R.id.tv_coinPrice);
            coinNumberTv = itemView.findViewById(R.id.tv_coinNumber);
        }

        public void bindCurrency(Currency currency) {

            String mainPrice = String.valueOf(currency.getPrice()).split("\\.")[0];
            String restPrice = String.valueOf(currency.getPrice()).split("\\.")[1];

            if (restPrice.length() > 2) {
                coinPriceTv.setText(mainPrice + "." + restPrice.substring(0, 2));
            } else {
                coinPriceTv.setText(mainPrice + "." + restPrice);
            }

            coinNameTv.setText(currency.getName());
            coinTitleTv.setText(currency.getCoin());
            coinNumberTv.setText(String.valueOf(currencies.indexOf(currency) + 1));
        }
    }
}
