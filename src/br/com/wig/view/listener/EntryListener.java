package br.com.wig.view.listener;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

import br.com.wig.commons.Strings;
import br.com.wig.exception.DuplicatedValueException;
import br.com.wig.exception.EmptyDataException;
import br.com.wig.exception.ValueNotFoundException;
import br.com.wig.model.rms.EntryDAO;
import br.com.wig.model.rms.RecordManagement;
import br.com.wig.view.main.Main;
/**
 * 
 * @author thiago
 *
 */
public class EntryListener extends AbstractListener implements CommandListener {
	
	private EntryDAO dao = new EntryDAO();

	public void commandAction(Command command, Displayable displayable) {
		try {
			this.executeCommand(command);
		} catch (EmptyDataException e) {
			Main.enableErrorAndViewByType(Strings.ENTRY_TYPE, e.getMessage());
		} catch (DuplicatedValueException e) {
			Main.enableErrorAndViewByType(Strings.ENTRY_TYPE, e.getMessage());
		} catch (ValueNotFoundException e) {
			Main.enableErrorAndViewByType(Strings.ENTRY_TYPE, e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
			Main.enableErrorAndViewByType(Strings.ENTRY_TYPE);
		}
	}
	
	private void executeCommand(Command command) throws Exception {
		if (command.getLabel().equals(Strings.BACK)) {
			super.enableIndexView();
		} else if (command.getLabel().equals(Strings.SAVE)) {
			this.saveEntryType();
			Main.successAlert(Strings.ENTRY_TYPE, Strings.SUCCESS);
		} else if (command.getLabel().equals(Strings.RESET_BASE)) {
			super.restartDataBase();
		} 
	}
	
	private void saveEntryType() throws Exception {
		this.dao.saveEntryType();
	}

	public RecordManagement getDAO() {
		return this.dao;
	}

}
