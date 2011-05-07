package br.com.wig.view.listener;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

import br.com.wig.commons.Strings;
import br.com.wig.model.rms.CategoryDAO;
import br.com.wig.model.rms.RecordManagement;
import br.com.wig.view.main.Main;
/**
 * 
 * @author thiago
 *
 */
public class CategoryListener extends AbstractListener implements CommandListener {
	
	private CategoryDAO dao = new CategoryDAO();

	public void commandAction(Command command, Displayable displayable) {
		try {
			this.executeCommand(command);
		} catch (Exception e) {
			e.printStackTrace();
			Main.enableErrorAndViewByType(Strings.CATEGORY);
		}
	}
	
	private void executeCommand(Command command) throws Exception {
		if (command.getLabel().equals(Strings.SAVE)) {
			this.saveCategory();	
			Main.successAlert(Strings.CATEGORY, Strings.SUCCESS);
		} else if (command.getLabel().equals(Strings.RESET_BASE)) {
			super.restartDataBase();
		} else if (command.getLabel().equals(Strings.BACK)) {
			super.enableIndexView();
		}
	}
	
	private void saveCategory() throws Exception {
		this.dao.saveCategory();
	}

	public RecordManagement getDAO() {
		return this.dao;
	}
	

}
