package com.example.cryptocurrency.model;

import java.util.List;

public class Currency{
//	private List<CurrencyItem> currency;
//
//	public List<CurrencyItem> getCurrency(){
//		return currency;
//	}

	private double price;
	private String name;
	private String id;
	private String coin;

	public double getPrice(){
		return price;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public String getCoin(){
		return coin;
	}

}