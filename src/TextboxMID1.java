/*
Java para Dispositivos Móveis - Desenvolvendo aplicações com J2ME
por Thienne M. Johnson 
Novatec Editora LTDa
ISBN: 978-85-7522-143-3
*/

import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.TextField;
import javax.microedition.lcdui.CommandListener;

public class TextboxMID1 extends MIDlet implements CommandListener{
	public Display display;
	private TextBox textBox;
	private Command cancelar;
    
	public TextboxMID1() {
		textBox = new TextBox("Exemplo de TextBox", " ",20, TextField.ANY);
		cancelar = new Command("Cancelar", Command.CANCEL, 0);
	}

	public void startApp() {
		textBox.addCommand(cancelar);
		textBox.setCommandListener(this);
		display = Display.getDisplay(this);
		display.setCurrent(textBox);
	}

	protected void pauseApp() {
	}

	protected void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	public void commandAction(Command c, Displayable d) {
		if( c == cancelar) {
			destroyApp(true);
		}
	}
}
