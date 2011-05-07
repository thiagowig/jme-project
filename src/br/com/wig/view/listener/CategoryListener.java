package br.com.wig.view.listener;

import java.io.IOException;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

import br.com.wig.model.rms.CategoryDAO;
import br.com.wig.view.main.Main;
import br.com.wig.view.nav.NavigationType;
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
			try {
				this.saveCategory();	
				this.enableIndexView();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
	
	private void saveCategory() throws IOException {
		dao = new CategoryDAO();
		dao.saveCategory();
	}
	
	private void enableIndexView() {
		Main.navigationType = NavigationType.INDEX;
		Main.enableViewByType();
	}

}
