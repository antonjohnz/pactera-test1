package com.pactera.test.model;

import java.util.Date;

public class Fridge {
	private String item;
	private int amount;
	private String unitString;
	private Unit unit;
	private String useByDateString;
	private Date useBy;
	public Fridge(){
		
	}
	public Fridge(String item, int amount, Unit unit, String useByDateString) {
		super();
		this.item = item;
		this.amount = amount;
		this.unit = unit;
		this.useByDateString = useByDateString;
	}
	
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
	public String getUnitString() {
		return unitString;
	}
	public void setUnitString(String unitString) {
		this.unitString = unitString;
	}
	public Unit getUnit() {
		return unit;
	}
	public void setUnit(Unit unit) {
		this.unit = unit;
	}
	public String getUseByDateString() {
		return useByDateString;
	}
	public void setUseByDateString(String useByDateString) {
		this.useByDateString = useByDateString;
	}
	public Date getUseBy() {
		return useBy;
	}
	public void setUseBy(Date useBy) {
		this.useBy = useBy;
	}
	@Override
	public String toString() {
		return "Fridge [item=" + item + ", amount=" + amount + ", unit=" + unit
				+ ", useByDateString=" + useByDateString + ", useBy=" + useBy
				+ "]";
	}
	
}
