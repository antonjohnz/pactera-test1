package com.pactera.test.model;

public enum Unit {
	OF, GRAMS, ML, SLICES, of, grams, ml, slices;
	public static Unit getValue(String value){
		if(value.equalsIgnoreCase(Unit.OF.toString())){
			return Unit.OF;
		}else if(value.equalsIgnoreCase(Unit.GRAMS.toString())){
			return Unit.GRAMS;
		}else if(value.equalsIgnoreCase(Unit.ML.toString())){
			return Unit.ML;
		}else if(value.equalsIgnoreCase(Unit.SLICES.toString())){
			return Unit.SLICES;
		}else{
			return null;
		}
	}
}
