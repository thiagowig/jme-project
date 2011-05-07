package br.com.wig.view.listener;

import br.com.wig.model.rms.RecordManagement;
import br.com.wig.view.main.Main;
import br.com.wig.view.nav.NavigationType;
/**
 * 
 * @author thiago
 *
 */
public abstract class AbstractListener {
	
	public abstract RecordManagement getDAO();

	protected void enableIndexView() {
		Main.navigationType = NavigationType.INDEX;
		Main.enableViewByType();
	}
	
	protected void restartDataBase() {
		this.getDAO().restartDataBase();
	}
}
