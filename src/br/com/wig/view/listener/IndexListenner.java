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

	public void commandAction(Command command, Displayable displayable) {
		if (command.getLabel().equals(this.CATEGORY_LABEL)) {
			this.enableCategory();
		}
		System.out.println("Entrou");

	}
	
	private void enableCategory() {
		Main.navigationType = NavigationType.CATEGORY;
		Main.enableViewByType();
	}

}
