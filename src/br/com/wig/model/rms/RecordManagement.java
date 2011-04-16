package br.com.wig.model.rms;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;

import br.com.wig.model.rms.listener.RecordManagementListener;
/**
 * 
 * @author thiago
 *
 */
public abstract class RecordManagement {

	public abstract String getDataBaseName();

	private RecordStore recordStore;
	
	public RecordManagement() {
		this.openBaseAndListener();
	}
	
	public void openBaseAndListener() {
		try {
			this.openRecordStore();
			this.addListener();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}

	public void openRecordStore() throws RecordStoreException {
		recordStore = RecordStore.openRecordStore(getDataBaseName(), true);		
	}
	
	public void addListener() {
		recordStore.addRecordListener(new RecordManagementListener());
	}
	
	protected void saveRecord(String valuesConcatenated) {		
		try {
			this.save(valuesConcatenated);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void save(String valuesConcatenated) throws Exception {
		byte[] bytesArray = valuesConcatenated.getBytes();
		this.recordStore.addRecord(bytesArray, 0, bytesArray.length);
	}
}
