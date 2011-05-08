package br.com.wig.model.rms;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.util.Vector;

import br.com.wig.commons.Strings;
import br.com.wig.exception.DuplicatedValueException;
import br.com.wig.exception.EmptyDataException;
import br.com.wig.exception.ValueNotFoundException;
import br.com.wig.view.form.EntryVW;

/**
 * 
 * @author thiago
 *
 */
public class EntryDAO extends RecordManagement {
	
	private final String STORE_NAME = "entry_db";

	public String getDataBaseName() {
		return this.STORE_NAME;
	}
	
	public void saveEntryType() throws Exception {
		CategoryDAO categoryDAO = new CategoryDAO();
		String categoryName;
		String entryTypeName;
		try {
			categoryName = EntryVW.categoriesChoice.getString(EntryVW.categoriesChoice.getSelectedIndex());
			entryTypeName = EntryVW.entryTypesChoice.getString(EntryVW.entryTypesChoice.getSelectedIndex());
		} catch (Exception e) {
			e.printStackTrace();
			throw new ValueNotFoundException(Strings.CATEGORY_REQUIRED);
		}
		int categoryId = categoryDAO.findIdByName(categoryName);
		Vector errorMessages = this.validateSave(categoryId);
		if (errorMessages.size() == 0) {
			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			DataOutputStream dataOutput = new DataOutputStream(byteArray);
			//dataOutput.writeUTF(EntryTypeVW.name.getString());
			dataOutput.writeInt(categoryId);
			super.saveRecord(byteArray.toByteArray());
			byteArray.close();
			dataOutput.close();
		} else {
			StringBuffer messages = new StringBuffer();
			for (int i = 0; i < errorMessages.size(); i++) {
				messages.append("[" + errorMessages.elementAt(i) + "] ");
			}
			throw new EmptyDataException(Strings.REQUIRED_VALUES_NOT_SETTED + ": " + messages.toString());
		}
	}
	
	private Vector validateSave(int categoryId) throws EmptyDataException, Exception, DuplicatedValueException {
		Vector errorMessages = new Vector();
		
		
		
		return errorMessages;
	}
}
