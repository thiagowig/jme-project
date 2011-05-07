package br.com.wig.model.rms;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Vector;

import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStoreException;

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
	
	public Vector findAllRecords() {	
		try {
			return this.returnAllElements();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public Vector returnAllElements() throws RecordStoreException, IOException {
		Vector categories = new Vector();
		RecordEnumeration recordEnumeration = super.retrieveAllRecordsEnumeration();
		while(recordEnumeration.hasNextElement()) {
			byte[] categoryRecord = recordEnumeration.nextRecord();
			ByteArrayInputStream byteArray = new ByteArrayInputStream(categoryRecord);
			DataInputStream dataInput = new DataInputStream(byteArray);
			String category = dataInput.readUTF();
			categories.addElement(category);
		}
		
		recordEnumeration.destroy();
		super.closeRecordStore();
		return categories;
	}
	
	public String retrieveNameById(int recordId) {
		try {
			return this.retrieveCategoryNameById(recordId);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private String retrieveCategoryNameById(int recordId) throws Exception {
		String categoryName;
		
		byte[] categoryRecord = super.retrieveById(recordId);
		ByteArrayInputStream byteArray = new ByteArrayInputStream(categoryRecord);
		DataInputStream dataInput = new DataInputStream(byteArray);
		categoryName = dataInput.readUTF();
		
		super.closeRecordStore();
		
		return categoryName;
	}
	
	public int findIdByName(String currentCategoryName) throws Exception {
		int categoryId = 0;
		String categoryName;
		
		RecordEnumeration recordEnumeration = super.retrieveAllRecordsEnumeration();
		while(recordEnumeration.hasNextElement()) {
			categoryId = recordEnumeration.nextRecordId();
			categoryName = this.retrieveNameById(categoryId);
			if (currentCategoryName.equals(categoryName)) {
				return categoryId;
			}
			
		}
		
		return 0;
	}
}
