package br.com.wig.view.listener;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

import br.com.wig.model.rms.CategoryDAO;
/**
 * 
 * @author thiago
 *
 */
public class CategoryListener implements CommandListener {
	
	private final String SAVE_LAVEL = "Salvar";
	
	private CategoryDAO dao;

	public void commandAction(Command command, Displayable displayable) {
		if (command.getLabel().equals(this.SAVE_LAVEL)) {
			this.saveCategory();
		}

	}
	
	public void saveCategory() {
		dao = new CategoryDAO();
		dao.saveCategory();
	}

}
