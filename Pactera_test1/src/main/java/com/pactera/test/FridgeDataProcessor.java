package com.pactera.test;

import com.pactera.test.model.Fridge;


public interface FridgeDataProcessor {
	boolean processFridgeItems(Fridge fridge) throws DataProcessException;
}
