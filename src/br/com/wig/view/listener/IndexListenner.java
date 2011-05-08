package br.com.wig.view.listener;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Displayable;

import br.com.wig.commons.Strings;
import br.com.wig.view.main.Main;
import br.com.wig.view.nav.NavigationType;
/**
 * 
 * @author thiago
 *
 */
public class IndexListenner implements CommandListener {

	public void commandAction(Command command, Displayable displayable) {
		if (command.getLabel().equals(Strings.CATEGORY)) {
			this.enableCategory();
		} else if (command.getLabel().equals(Strings.ENTRY_TYPE)) {
			this.enableEntryType();
		} else if (command.getLabel().equals(Strings.ENTRY)) {
			this.enableEntry();
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
	
	private void enableEntry() {
		Main.navigationType = NavigationType.ENTRY;
		Main.enableViewByType();
	}

}
