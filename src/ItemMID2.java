/*
Java para Dispositivos Móveis - Desenvolvendo aplicações com J2ME
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
import javax.microedition.lcdui.StringItem;

public class ItemMID2 extends MIDlet implements CommandListener, ItemStateListener {
	private Display display;
	private List opcoesItens;
	private Command sair;
	private Command voltar;

	public ItemMID2(){
		opcoesItens = new List("Lista de tipos de Itens", Choice.IMPLICIT);
		sair = new Command("Sair", Command.EXIT,0);
		voltar = new Command("Voltar", Command.OK,1);
	}

	protected void startApp() {
		display = Display.getDisplay(this);
		opcoesItens.append("TextField",null);
		opcoesItens.append("StringItem",null);
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
		form.append(new TextField("Código de área", "091", 3, TextField.NUMERIC));
		form.append(new TextField("Telefone", "88881111", 9, TextField.PHONENUMBER));
		form.append(new TextField("Senha", null, 8, TextField.PASSWORD|TextField.NUMERIC));
		form.addCommand(sair);
		form.addCommand(voltar);
		form.setCommandListener(this);
		form.setItemStateListener(this);
		display.setCurrent(form);
	}
	
	private void exemploStringItem(){
		Form form = new Form("StringItem");  
		form.append(new StringItem("Uso de StringItem", "Sem limite"));
		StringItem testeString = new StringItem("Testando tamanho", "Com limite");
		testeString.setPreferredSize(50,30);
		form.append(testeString);
		form.append(new StringItem("Com label", "Com texto\n"));
		form.append(new StringItem(null, "Sem Label\n"));
		form.append(new StringItem(null, "Pulando Linha\nbreak"));
		form.append(new StringItem("Com Label 1", "Texto 1"));
		form.append(new StringItem("Com Label 2", "Texto 2"));
		form.addCommand(sair);
		form.addCommand(voltar);
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
			}else if (selection == "StringItem"){
				exemploStringItem();
			}	
		}
	}

	public void itemStateChanged(Item item) {        
		if (item instanceof TextField) {
			System.out.println("Conteúdo do TextField = <" 
				+ ((TextField)item).getString() + ">");
		}
	}    
}
