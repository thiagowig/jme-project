package br.com.wig.model.rms;

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
	
	public void saveCategory() {
		super.saveRecord(CategoryVW.name.getString());
	}
}
