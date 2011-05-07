package br.com.wig.model.rms;

import javax.microedition.rms.InvalidRecordIDException;
import javax.microedition.rms.RecordEnumeration;
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
	
	public void openBaseAndListener() {
		try {
			this.openRecordStore();
			this.addListener();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}

	private void openRecordStore() throws RecordStoreException {
		recordStore = RecordStore.openRecordStore(this.getDataBaseName(), true);		
	}
	
	private void deleteRecordStore() throws RecordStoreNotFoundException, RecordStoreException {
		RecordStore.deleteRecordStore(this.getDataBaseName());
	}
	
	protected void closeRecordStore() throws RecordStoreNotOpenException, RecordStoreException {
		this.recordStore.closeRecordStore();
	}
	
	private void addListener() {
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
		this.openBaseAndListener();
		this.recordStore.addRecord(bytesArray, 0, bytesArray.length);
		this.closeRecordStore();
	}
	
	public void restartDataBase() {
		try {
			this.deleteRecordStore();
		} catch (RecordStoreNotFoundException e) {
			e.printStackTrace();
		} catch (RecordStoreException e) {
			e.printStackTrace();
		}
	}
	
	public RecordEnumeration retrieveAllRecordsEnumeration() {
		RecordEnumeration recordEnumeration = null;
		this.openBaseAndListener();		
		try {
			recordEnumeration = this.recordStore.enumerateRecords(null, null, false);
		} catch (RecordStoreNotOpenException e) {
			e.printStackTrace();
		} 
		
		return recordEnumeration;
	}
	
	protected byte[] retrieveById(int recordId) throws RecordStoreNotOpenException, InvalidRecordIDException, RecordStoreException {
		this.openBaseAndListener();
		return this.recordStore.getRecord(recordId);
	}
}
