package br.com.wig.model.rms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.util.Vector;

import javax.microedition.rms.RecordEnumeration;

import br.com.wig.commons.Strings;
import br.com.wig.exception.EmptyDataException;
import br.com.wig.view.form.EntryTypeVW;

/**
 * 
 * @author thiago
 *
 */
public class EntryTypeDAO extends RecordManagement {
	
	private final String STORE_NAME = "entry_type_db";

	public String getDataBaseName() {
		return this.STORE_NAME;
	}
	
	public void saveEntryType() throws Exception {
		CategoryDAO categoryDAO = new CategoryDAO();
		String categoryName = EntryTypeVW.categoriesChoice.getString(EntryTypeVW.categoriesChoice.getSelectedIndex());
		int categoryId = categoryDAO.findIdByName(categoryName);
		Vector errorMessages = this.validateSave(categoryId);
		if (errorMessages.size() == 0) {
			ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
			DataOutputStream dataOutput = new DataOutputStream(byteArray);
			dataOutput.writeUTF(EntryTypeVW.name.getString());
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
	
	private Vector validateSave(int categoryId) throws EmptyDataException {
		Vector errorMessages = new Vector();
		
		if (Strings.isEmpty(EntryTypeVW.name.getString())) {
			errorMessages.addElement(Strings.NAME);
		}
		
		if (Strings.isEmpty(categoryId)) {
			errorMessages.addElement(Strings.CATEGORY);
		}
		
		return errorMessages;
	}
	
	public Vector returnAllElements() throws Exception {
		Vector categories = new Vector();
		String entryType;
		int categoryTypeId;
		String categoryName;
		CategoryDAO categoryDAO = new CategoryDAO();
		RecordEnumeration recordEnumeration = super.retrieveAllRecordsEnumeration();
		while(recordEnumeration.hasNextElement()) {
			byte[] categoryRecord = recordEnumeration.nextRecord();
			ByteArrayInputStream byteArray = new ByteArrayInputStream(categoryRecord);
			DataInputStream dataInput = new DataInputStream(byteArray);
			entryType = dataInput.readUTF();
			categoryTypeId = dataInput.readInt();
			categoryName = categoryDAO.retrieveNameById(categoryTypeId);
			
			categories.addElement(entryType + " " + categoryName);
			
			byteArray.close();
			dataInput.close();
		}
		
		recordEnumeration.destroy();
		super.closeRecordStore();
		return categories;
	}
}
