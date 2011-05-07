package br.com.wig.view.form;

import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import br.com.wig.commons.Strings;
import br.com.wig.view.listener.CategoryListener;

/**
 * 
 * @author thiago
 *
 */
public class CategoryVW extends AbstractView implements View {
	
	private Form form;
	
	public static TextField name;

	public Form getForm() {
		this.form = new Form(Strings.CATEGORY);
		CategoryVW.name = new TextField(Strings.NAME, "", 20, TextField.ANY);
		
		this.form.append(CategoryVW.name);
		this.form.addCommand(super.getSaveCommand());
		this.form.addCommand(super.getResetBaseCommand());
		this.form.addCommand(super.getBackCommand());
		this.form.setCommandListener(new CategoryListener());
		
		return this.form;
	}

}
