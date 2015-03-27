package com.pactera.test.model;

import java.io.Serializable;

public class Ingredient implements Serializable{
	private String item;
	private int amount;
	private Unit unit;
	public String getItem() {
		return item;
	}
	public void setItem(String item) {
		this.item = item;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	@Override
	public String toString() {
		return "Ingredient [item=" + item + ", amount=" + amount + ", unit="
				+ unit + "]";
	}
}
