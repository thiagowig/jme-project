package br.com.wig.model.rms.listener;

import javax.microedition.rms.RecordListener;
import javax.microedition.rms.RecordStore;
/**
 * 
 * @author thiago
 *
 */
public class RecordManagementListener implements RecordListener {

	public void recordAdded(RecordStore recordStore, int recordId){
		System.out.println("Registro adicionado: " + recordId);
	}
	public void recordDeleted(RecordStore recordStore, int recordId){
		System.out.println("Registro apagado: " + recordId);
	}
	public void recordChanged(RecordStore recordStore, int recordId){
		System.out.println("Registro modificado: " + recordId);
	}

}
