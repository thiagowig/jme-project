/*
Java para Dispositivos Móveis - Desenvolvendo aplicações com J2ME
por Thienne M. Johnson 
Novatec Editora LTDa
ISBN: 978-85-7522-143-3
*/

import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;

public class FormMID1 extends MIDlet implements CommandListener {
	private Display display;
	//Forms
	private Form inicio;
	private Form logado;
	private Form recusado;
	//Comandos
	private Command cancelar;
	private Command login;
	private Command help;
	private Command naousuario;
	private Command voltar;
	private Command info;

	public FormMID1() {
		//Forms
		inicio = new Form("Entrar");
		logado = new Form("Logado");
		recusado = new Form("Não Usuário");
		//Comandos
		cancelar = new Command("Cancelar", Command.CANCEL, 0);
		login = new Command("Login", Command.OK, 1);
		help = new Command("Help", Command.OK, 1);
		info = new Command("Info", Command.OK, 1);
		naousuario = new Command("Nao Usuario", Command.OK, 1);
		voltar = new Command("Voltar", Command.BACK, 0);    
  	}

	public void startApp() {
		display = Display.getDisplay(this);
		//FORM Inicial
		inicio.addCommand(cancelar);
		inicio.addCommand(login);
		inicio.addCommand(help);
		inicio.addCommand(naousuario);
		inicio.setCommandListener(this);
		//Form de usuario logado
		logado.addCommand(info);
		logado.addCommand(voltar);
		logado.setCommandListener(this);
		//Form de não usuario
		recusado.addCommand(voltar);
		recusado.setCommandListener(this);
		display.setCurrent(inicio);
	}
	
	public void pauseApp() {
	}

	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}

	public void commandAction(Command c, Displayable d) {
		if(c == cancelar) {
			destroyApp(true);
		} else if(c == login) {
			display.setCurrent(logado);
		} else if(c == naousuario){
			display.setCurrent(recusado); 
		} else if(c == voltar){
			display.setCurrent(inicio);
		}
  	}
}
