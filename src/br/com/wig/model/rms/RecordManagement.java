package br.com.wig.model.rms;

import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreNotFoundException;
import javax.microedition.rms.RecordStoreNotOpenException;

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
		recordStore = RecordStore.openRecordStore(this.getDataBaseName(), true);		
	}
	
	public void deleteRecordStore() throws RecordStoreNotFoundException, RecordStoreException {
		RecordStore.deleteRecordStore(this.getDataBaseName());
	}
	
	public void closeRecordStore() throws RecordStoreNotOpenException, RecordStoreException {
		this.recordStore.closeRecordStore();
	}
	
	public void addListener() {
		recordStore.addRecordListener(new RecordManagementListener());
	}
	
	protected void saveRecord(byte[] bytesArray) {		
		try {
			this.save(bytesArray);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void save(byte[] bytesArray) throws Exception {
		this.recordStore.addRecord(bytesArray, 0, bytesArray.length);
	}
	
	public void restartDataBase() {
		try {
			this.closeRecordStore();
			this.deleteRecordStore();
			this.openBaseAndListener();
		} catch (RecordStoreNotFoundException e) {
			e.printStackTrace();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}
}
