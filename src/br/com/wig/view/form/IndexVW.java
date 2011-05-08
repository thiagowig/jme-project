package br.com.wig.view.form;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;

import br.com.wig.commons.Strings;
import br.com.wig.view.listener.IndexListenner;
/**
 * 
 * @author thiago
 *
 */
public class IndexVW extends AbstractView implements View {

	private Form form;
	
	private Command categoryCommand;
	
	private Command entryTypeCommand;
	
	private Command entryCommand;

	public Form getForm() {
		this.form = new Form("Index");
		this.categoryCommand = new Command(Strings.CATEGORY, Command.SCREEN, 1);
		this.entryTypeCommand = new Command(Strings.ENTRY_TYPE, Command.SCREEN, 1);
		this.entryCommand = new Command(Strings.ENTRY, Command.SCREEN, 1);

		this.form.setCommandListener(new IndexListenner());
		
		this.form.addCommand(this.categoryCommand);
		this.form.addCommand(this.entryTypeCommand);		
		this.form.addCommand(this.entryCommand);
		
		return this.form;
	}
	
}
