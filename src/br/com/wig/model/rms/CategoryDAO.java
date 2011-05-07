package br.com.wig.model.rms;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import br.com.wig.view.form.CategoryVW;

/**
 * 
 * @author thiago
 *
 */
public class CategoryDAO extends RecordManagement {
	
	private final String STORE_NAME = "category_db";

	public String getDataBaseName() {
		return this.STORE_NAME;
	}
	
	public void saveCategory() throws IOException {
		ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
		DataOutputStream dataOutput = new DataOutputStream(byteArray);
		dataOutput.writeUTF(CategoryVW.name.getString());
		
		super.saveRecord(byteArray.toByteArray());
		
		byteArray.close();
		dataOutput.close();
	}
}
