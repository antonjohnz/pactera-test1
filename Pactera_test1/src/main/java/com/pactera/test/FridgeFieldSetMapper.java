package com.pactera.test;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.pactera.test.model.Fridge;
public class FridgeFieldSetMapper implements FieldSetMapper<Fridge> {

	@Override
	public Fridge mapFieldSet(FieldSet fieldSet) throws BindException {
		Fridge fridge = new Fridge();
        fridge.setItem( fieldSet.readString( "item" ) );
        fridge.setAmount( fieldSet.readInt( "amount" ) );
        fridge.setUnitString( fieldSet.readString("unit"));
        fridge.setUseByDateString( fieldSet.readString( "useBy" ) );
        return fridge;
	}

}
