package br.com.wig.view.listener;

import java.util.Vector;

import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;

import br.com.wig.commons.Strings;
import br.com.wig.model.rms.CategoryDAO;
import br.com.wig.model.rms.EntryTypeDAO;
import br.com.wig.view.form.EntryVW;
import br.com.wig.view.main.Main;

public class EntryItemListener implements ItemStateListener {

	public void itemStateChanged(Item item) {
		try {
			this.executeItemChanged(item);
		} catch (Exception e) {
			e.printStackTrace();
			Main.enableErrorAndViewByType(Strings.ENTRY);
		}
		
	}
	
	private void executeItemChanged(Item item) throws Exception {
		if (item.getLabel().equals(Strings.CATEGORY)) {
			int categoryId = this.getCategoryId();
			this.populateEntryTypeChoice(categoryId);
		}
	}
	
	private int getCategoryId() throws Exception {
		String categoryName = EntryVW.categoriesChoice.getString(EntryVW.categoriesChoice.getSelectedIndex());
		CategoryDAO categoryDAO = new CategoryDAO();
		int categoryId = categoryDAO.findIdByName(categoryName);
		
		return categoryId;
	}
	
	private void populateEntryTypeChoice(int categoryId) throws Exception {
		EntryTypeDAO entryTypeDAO = new EntryTypeDAO();
		
		Vector entryTypesByCategory = entryTypeDAO.findByCategoryId(categoryId);
		
		if (entryTypesByCategory.size() > 0) {
			for (int i = 0; i < entryTypesByCategory.size(); i++) {
				String category = (String) entryTypesByCategory.elementAt(i);
				EntryVW.entryTypesChoice.append(category, null);			
			}
		} else {
			EntryVW.entryTypesChoice.deleteAll();
		}
		
	}

}
