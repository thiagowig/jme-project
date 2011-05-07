package br.com.wig.view.form;

import java.util.Vector;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import br.com.wig.commons.Strings;
import br.com.wig.model.rms.CategoryDAO;
import br.com.wig.view.listener.EntryTypeListener;
/**
 * 
 * @author thiago
 *
 */
public class EntryTypeVW extends AbstractView implements View {

	private Form form;

	public static TextField name;
	
	public static ChoiceGroup categoriesChoice;

	public Form getForm() {
		this.initElements();		
		this.setForm();
		
		return this.form;
	}
	
	private void initElements() {
		this.form = new Form(Strings.ENTRY_TYPE);
		EntryTypeVW.name = new TextField(Strings.NAME, "", 20, TextField.ANY);		
		EntryTypeVW.categoriesChoice = new ChoiceGroup (Strings.CATEGORY, Choice.POPUP);
		
		this.populateCategoriesChoice();
	}
	
	private void setForm() {			
		this.form.append(EntryTypeVW.name);
		this.form.append(EntryTypeVW.categoriesChoice);
		this.form.addCommand(super.getSaveCommand());
		this.form.addCommand(super.getBackCommand());
		this.form.addCommand(super.getListAllCommnad());
		this.form.addCommand(super.getResetBaseCommand());
		this.form.setCommandListener(new EntryTypeListener());
	}	
	
	private void populateCategoriesChoice() {
		CategoryDAO categoryDAO = new CategoryDAO();
		Vector categories = categoryDAO.findAllRecords();
		
		for (int i = 0; i < categories.size(); i++) {
			String category = (String) categories.elementAt(i);
			EntryTypeVW.categoriesChoice.append(category, null);			
		}
	}

}
