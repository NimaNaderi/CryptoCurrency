package com.example.cryptocurrency.model;

public class CurrencyItem{
	private double reward;
	private double networkHashrate;
	private String type;
	private double rewardBlock;
	private long difficulty;
	private double volume;
	private double price;
	private String name;
	private String id;
	private String rewardUnit;
	private int updated;
	private String coin;
	private String algorithm;

	public double getReward(){
		return reward;
	}

	public double getNetworkHashrate(){
		return networkHashrate;
	}

	public String getType(){
		return type;
	}

	public double getRewardBlock(){
		return rewardBlock;
	}

	public long getDifficulty(){
		return difficulty;
	}

	public double getVolume(){
		return volume;
	}

	public double getPrice(){
		return price;
	}

	public String getName(){
		return name;
	}

	public String getId(){
		return id;
	}

	public String getRewardUnit(){
		return rewardUnit;
	}

	public int getUpdated(){
		return updated;
	}

	public String getCoin(){
		return coin;
	}

	public String getAlgorithm(){
		return algorithm;
	}
}
