package br.com.wig.view.main;

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

import br.com.wig.commons.Strings;
import br.com.wig.view.factory.ViewFactory;
import br.com.wig.view.nav.NavigationType;
/**
 * 
 * @author thiago
 *
 */
public class Main extends MIDlet {
	
	public static Integer navigationType;
	
	public static Display display;
	
	public static Alert errorAlert;

	protected void startApp() throws MIDletStateChangeException {
		Main.display = Display.getDisplay(this);
		Main.navigationType = NavigationType.INDEX;
		Main.enableViewByType();
	}
	
	
	protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
		super.notifyDestroyed();		
	}

	protected void pauseApp() {
	}

	public static void enableViewByType() {		
		Main.display.setCurrent(ViewFactory.getForm(Main.navigationType));
	}
	
	public static void enableErrorAndViewByType(String functionalityName) {
		Main.enableErrorAndViewByType(functionalityName, null);
	}
	
	public static void enableErrorAndViewByType(String functionalityName, String message) {
		if (message == null) {
			message = Strings.DEFAULT_ERROR_MESSAGE;
		}		
		Main.enableAlert(functionalityName, message, AlertType.ERROR);
	}
	
	private static void enableAlert(String functionalityName, String message, AlertType alertType) {
		Main.errorAlert = new Alert(functionalityName, message, null, alertType);
		Main.errorAlert.setTimeout(Alert.FOREVER);
		Main.display.setCurrent(Main.errorAlert, ViewFactory.getForm(Main.navigationType));
	}
	
	public static void successAlert (String functionalityName, String message) {
		Main.enableAlert(functionalityName, message, AlertType.CONFIRMATION);
	}

}
