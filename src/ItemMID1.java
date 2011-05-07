/*
Java para Dispositivos M�veis - Desenvolvendo aplica��es com J2ME
por Thienne M. Johnson 
Novatec Editora LTDa
ISBN: 978-85-7522-143-3
*/

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;

public class ItemMID1 extends MIDlet implements CommandListener, ItemStateListener {
	private Display display;
	private List opcoesItens;
	private Command sair;
	private Command voltar;

	public ItemMID1(){
		opcoesItens = new List("Lista de tipos de Itens", Choice.IMPLICIT);
		sair = new Command("Sair", Command.EXIT,0);
		voltar = new Command("Voltar", Command.OK,1);
	}

	protected void startApp() {
		display = Display.getDisplay(this);
		opcoesItens.append("TextField",null);
		opcoesItens.addCommand(sair);
		opcoesItens.setCommandListener(this);
		display.setCurrent(opcoesItens);
	}

	protected void pauseApp() {
	}
	
	protected void destroyApp(boolean unconditional) {
		notifyDestroyed();
	}
	
	public void exemploTextField(){
		Form form = new Form("TextField");  
		form.append(new TextField("Nome", null, 8, TextField.ANY));
		form.append(new TextField("C�digo de �rea", "091", 3, TextField.NUMERIC));
		form.append(new TextField("Telefone", "88881111", 9, TextField.PHONENUMBER));
		form.append(new TextField("Senha", null, 8, TextField.PASSWORD|TextField.NUMERIC));
		form.addCommand(sair);
		form.addCommand(voltar);
		form.setCommandListener(this);
		form.setItemStateListener(this);
		display.setCurrent(form);
	}

	public void commandAction(Command c, Displayable d) {
		if (c == sair) {
			destroyApp(true);
		}else if (c == voltar){
			display.setCurrent(opcoesItens);	
		}else if (c == List.SELECT_COMMAND){
			String selection = opcoesItens.getString(opcoesItens.getSelectedIndex());
			if (selection == "TextField"){ 
				exemploTextField();
			}
		}
	}

	public void itemStateChanged(Item item) {        
		if (item instanceof TextField) {
			System.out.println("Conte�do do TextField = <" 
				+ ((TextField)item).getString() + ">");
		}
	}    
}
