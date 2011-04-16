package br.com.wig.view.main;

import javax.microedition.lcdui.Display;
import javax.microedition.midlet.MIDlet;
import javax.microedition.midlet.MIDletStateChangeException;

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

	protected void startApp() throws MIDletStateChangeException {
		Main.display = Display.getDisplay(this);
		Main.navigationType = NavigationType.INDEX;
		Main.enableViewByType();
	}
	
	
	protected void destroyApp(boolean unconditional) throws MIDletStateChangeException {
		notifyDestroyed();
		
	}

	protected void pauseApp() {
		// TODO Auto-generated method stub
		
	}

	public static void enableViewByType() {
		Main.display.setCurrent(ViewFactory.getForm(Main.navigationType));
	}

}
