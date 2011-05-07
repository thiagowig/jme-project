package br.com.wig.view.listener;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

import br.com.wig.view.main.Main;
import br.com.wig.view.nav.NavigationType;
/**
 * 
 * @author thiago
 *
 */
public class IndexListenner implements CommandListener {
	
	private final String CATEGORY_LABEL = "Categoria";
	
	private final String ENTRY_TYPE_LABEL = "Tipo de Lan\u00e7amento";

	public void commandAction(Command command, Displayable displayable) {
		if (command.getLabel().equals(this.CATEGORY_LABEL)) {
			this.enableCategory();
		} else if (command.getLabel().equals(this.ENTRY_TYPE_LABEL)) {
			this.enableEntryType();
		}

	}
	
	private void enableCategory() {
		Main.navigationType = NavigationType.CATEGORY;
		Main.enableViewByType();
	}
	
	private void enableEntryType() {
		Main.navigationType = NavigationType.ENTRY_TYPE;
		Main.enableViewByType();
	}

}
