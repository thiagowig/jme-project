/*
Java para Dispositivos Móveis - Desenvolvendo aplicações com J2ME
por Thienne M. Johnson 
Novatec Editora LTDa
ISBN: 978-85-7522-143-3
*/

import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;

public class TextboxMID2 extends MIDlet implements CommandListener{
	protected TextBox textBox;
	protected Display display;
	private  Command sair, troca;
	protected int numtrocas;
	protected boolean iniciado;
	
	public TextboxMID2() {
		textBox = new TextBox("TextBox Exemplo","Teste String Inicial", 30, TextField.ANY);
		sair = new Command("Sair", Command.EXIT, 0);
		troca = new Command ("Troca", Command.SCREEN, 1);
		textBox.setTitle("TextBox Inicial");
	}
	
	protected void startApp() {
		display = Display.getDisplay(this);
		textBox.addCommand(sair);
		textBox.addCommand(troca);
		textBox.setCommandListener(this);
		display.setCurrent(textBox);
		if (!iniciado) {
			numtrocas=0;
		}
		iniciado = true;
	}
	
	protected void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}
	
	protected void pauseApp() {
	}
	
	public void commandAction(Command c, Displayable d) {
		if(c == sair) {
			destroyApp(true);
		} else if(c==troca) {
    			textBox.setString("Trocou "+(numtrocas)+" vezes");
    			numtrocas ++;
    			textBox.setTitle("TextBox Alterado");
   	 	}
    	} 
}
