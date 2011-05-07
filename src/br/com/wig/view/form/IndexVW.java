package br.com.wig.view.form;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;

import br.com.wig.view.listener.IndexListenner;
/**
 * 
 * @author thiago
 *
 */
public class IndexVW implements View {

	private Form form;
	
	private Command categoryCommand;
	
	private Command entryTypeCommand;

	public Form getForm() {
		this.form = new Form("Index");
		this.categoryCommand = new Command("Categoria", Command.SCREEN, 1);
		this.entryTypeCommand = new Command("Tipo de Lan\u00e7amento", Command.SCREEN, 1);

		this.form.setCommandListener(new IndexListenner());
		this.form.addCommand(this.categoryCommand);
		this.form.addCommand(this.entryTypeCommand);
		
		return this.form;
	}
	
}
