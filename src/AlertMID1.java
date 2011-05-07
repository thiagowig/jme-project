/*
Java para Dispositivos Móveis - Desenvolvendo aplicações com J2ME
por Thienne M. Johnson 
Novatec Editora LTDa
ISBN: 978-85-7522-143-3
*/

import javax.microedition.lcdui.Alert;
import javax.microedition.lcdui.AlertType;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;

public class AlertMID1 extends MIDlet implements CommandListener {
	private Display display;
	private TextBox tlogin;
	private TextBox tlogado;
	private Alert alertalogin;
	private Alert alertaerro;
	private Command cancelar;
	private Command login;

	public AlertMID1() {
		tlogin = new TextBox("Login do Aplicativo", "", 20, TextField.ANY);
		tlogado = new TextBox("Usuario Validado", "", 20, TextField.ANY);
		cancelar = new Command("Cancelar", Command.EXIT, 0);
		login = new Command("OK", Command.OK, 1);
		alertaerro = new Alert("Login Incorreto", "Tente Novamente", null, AlertType.ERROR);
	}
	
	public void startApp() {
		display = Display.getDisplay(this);
		tlogin.addCommand(cancelar);
		tlogin.addCommand(login);
		tlogin.setCommandListener(this);
		display.setCurrent(tlogin);
		tlogado.addCommand(cancelar);
	}
	
	public void pauseApp() {
	}
	
	public void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}
	
	public void tenteNovamente() {
		alertaerro.setTimeout(Alert.FOREVER);
		tlogin.setString(" ");
		display.setCurrent(alertaerro, tlogin);
	}

	public void validaUsuario(String name) {
		if (name.equals("thienne")) { //usuario cadastrado
			alertalogin = new Alert ("Login","Usuário validado!",null, AlertType.CONFIRMATION);		
			alertalogin.setTimeout(2000);
       			tlogado.setString("Bem vindo(a)!");
       	 		tlogado.setCommandListener(this);
			display.setCurrent(alertalogin,tlogado);
         	} else { //usuario não encontrado
        	 	tenteNovamente();
         	}
  	 }
  
	public void commandAction(Command c, Displayable d) {
      		if (c == cancelar) {
		    	  destroyApp(true); // fecha a aplicação
      		}else if (c == login) {
    	  		validaUsuario(tlogin.getString());  // validação de usuário     	 
		}
   	}
}
