package br.com.wig.view.factory;

import javax.microedition.lcdui.Form;

import br.com.wig.exception.ViewNotFoundException;
import br.com.wig.view.form.CategoryVW;
import br.com.wig.view.form.EntryTypeVW;
import br.com.wig.view.form.EntryVW;
import br.com.wig.view.form.IndexVW;
import br.com.wig.view.form.View;
import br.com.wig.view.main.Main;
import br.com.wig.view.nav.NavigationType;

/**
 * Responsible for returning a view by Navigation Type Code.
 * @author thiago
 * 
 */
public class ViewFactory {

	public static Form getForm(Integer navigationTypeCode) {	
		View view = ViewFactory.getView(navigationTypeCode);
		if (view != null) {
			return view.getForm();
		} else {
			String navigationDescription = NavigationType.getNavigationDescription(Main.navigationType);
			throw new ViewNotFoundException(navigationDescription);
		}
	}

	public static View getView(Integer navigationTypeCode) {
		if (navigationTypeCode.equals(NavigationType.INDEX)) {
			return new IndexVW();
		} else if (navigationTypeCode.equals(NavigationType.CATEGORY)) {
			return new CategoryVW();
		} else if (navigationTypeCode.equals(NavigationType.ENTRY_TYPE)) {
			return new EntryTypeVW();
		} else if (navigationTypeCode.equals(NavigationType.ENTRY)) {
			return new EntryVW();
		}
		return null;
	}
}
