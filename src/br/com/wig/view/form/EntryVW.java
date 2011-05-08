package br.com.wig.view.form;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.Vector;

import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.TextField;

import br.com.wig.commons.Strings;
import br.com.wig.model.rms.CategoryDAO;
import br.com.wig.view.listener.EntryItemListener;
import br.com.wig.view.listener.EntryListener;
/**
 * 
 * @author thiago
 *
 */
public class EntryVW extends AbstractView implements View {

	private Form form;

	public static TextField value;
	
	public static ChoiceGroup categoriesChoice;
	
	public static ChoiceGroup entryTypesChoice;
	
	public static DateField date;

	public Form getForm() {
		this.initElements();		
		this.setForm();
		
		return this.form;
	}
	
	private void initElements() {
		this.form = new Form(Strings.ENTRY);
		EntryVW.value = new TextField(Strings.VALUE, "", 20, TextField.NUMERIC);		
		EntryVW.categoriesChoice = new ChoiceGroup (Strings.CATEGORY, Choice.POPUP);
		EntryVW.entryTypesChoice = new ChoiceGroup (Strings.ENTRY_TYPE, Choice.POPUP);
		EntryVW.date = new DateField(Strings.DATE, DateField.DATE, TimeZone.getTimeZone("GMT-03:00"));
		
		this.setActualDate();
		
		this.populateCategoriesChoice();
	}
	
	private void setActualDate() {
		Calendar actualDate = Calendar.getInstance();
		EntryVW.date.setDate(actualDate.getTime());
	}
	
	private void setForm() {			
		this.form.append(EntryVW.value);
		this.form.append(EntryVW.categoriesChoice);
		this.form.append(EntryVW.entryTypesChoice);
		this.form.append(EntryVW.date);
		
		this.form.addCommand(super.getSaveCommand());
		this.form.addCommand(super.getBackCommand());
		this.form.addCommand(super.getListAllCommnad());
		this.form.addCommand(super.getResetBaseCommand());
		
		this.form.setCommandListener(new EntryListener());
		this.form.setItemStateListener(new EntryItemListener());
	}	
	
	private void populateCategoriesChoice() {
		CategoryDAO categoryDAO = new CategoryDAO();
		Vector categories = categoryDAO.findAllRecords();
		
		for (int i = 0; i < categories.size(); i++) {
			String category = (String) categories.elementAt(i);
			EntryVW.categoriesChoice.append(category, null);			
		}
	}

}
