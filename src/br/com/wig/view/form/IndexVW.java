package br.com.wig.view.form;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import br.com.wig.view.listener.IndexListenner;
/**
 * 
 * @author thiago
 *
 */
public class IndexVW implements View {

	private Form form;
	
	private Command categoryCommand;
	
	private TextField helloWorld;

	public Form getForm() {
		this.form = new Form("Index");
		this.helloWorld = new TextField("Bem vindo", "Thiago Fonseca", 100, TextField.ANY);
		this.categoryCommand = new Command("Categoria", Command.SCREEN, 1);
		
		this.form.append(this.helloWorld);
		this.form.setCommandListener(new IndexListenner());
		this.form.addCommand(this.categoryCommand);
		
		return this.form;
	}
	
}
