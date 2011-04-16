package br.com.wig.view.form;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import br.com.wig.view.listener.CategoryListener;


public class CategoryVW implements View {
	
	private Form form;
	
	public static TextField name;
	
	private Command save;

	public Form getForm() {
		this.form = new Form("Categoria");
		CategoryVW.name = new TextField("Nome", "", 20, TextField.ANY);
		this.save = new Command("Salvar", Command.OK, 1);
		
		this.form.append(CategoryVW.name);
		this.form.addCommand(this.save);
		this.form.setCommandListener(new CategoryListener());
		
		return this.form;
	}

}
