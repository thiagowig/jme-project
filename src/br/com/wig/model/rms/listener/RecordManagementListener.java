package br.com.wig.model.rms.listener;

import javax.microedition.rms.RecordListener;
import javax.microedition.rms.RecordStore;
import javax.microedition.rms.RecordStoreNotOpenException;
/**
 * 
 * @author thiago
 *
 */
public class RecordManagementListener implements RecordListener {

	public void recordAdded(RecordStore recordStore, int recordId){
		try {
			System.out.println("Registro adicionado: " + recordStore.getName() + " " + recordId);
		} catch (RecordStoreNotOpenException e) {
			e.printStackTrace();
		}
	}
	public void recordDeleted(RecordStore recordStore, int recordId){
		try {
			System.out.println("Registro apagado: " + recordStore.getName() + " " + recordId);
		} catch (RecordStoreNotOpenException e) {
			e.printStackTrace();
		}
	}
	public void recordChanged(RecordStore recordStore, int recordId){
		try {
			System.out.println("Registro modificado: " + recordStore.getName() + " " + recordId);
		} catch (RecordStoreNotOpenException e) {
			e.printStackTrace();
		}
	}

}
