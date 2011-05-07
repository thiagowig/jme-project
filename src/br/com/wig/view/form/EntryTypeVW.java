package br.com.wig.view.form;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import br.com.wig.view.listener.EntryTypeListener;
/**
 * 
 * @author thiago
 *
 */
public class EntryTypeVW implements View {

	private Form form;

	public static TextField name;

	private Command save;
	
	private String[] testArray = {"Amigo", "Trabalho", "Familia"};
	
	private ChoiceGroup choiceTest;

	public Form getForm() {
		this.form = new Form("Tipo de Lan\u00e7amento");
		EntryTypeVW.name = new TextField("Nome", "", 20, TextField.ANY);
		this.save = new Command("Salvar", Command.OK, 1);
		this.choiceTest = new ChoiceGroup ("Tipo do contato", Choice.POPUP, testArray, null);
		
		this.form.append(EntryTypeVW.name);
		this.form.append(this.choiceTest);
		this.form.addCommand(this.save);
		this.form.setCommandListener(new EntryTypeListener());
		
		return this.form;
	}

}
