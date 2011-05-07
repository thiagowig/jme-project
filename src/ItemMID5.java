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
import javax.microedition.lcdui.StringItem;
import java.io.IOException;
import javax.microedition.lcdui.Image; //classe usada para acessar os arquivos de imagens
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.DateField;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class ItemMID5 extends MIDlet implements CommandListener, ItemStateListener {
	private Display display;
	private List opcoesItens;
	private Command sair;
	private Command voltar;

	public ItemMID5(){
		opcoesItens = new List("Lista de tipos de Itens", Choice.IMPLICIT);
		sair = new Command("Sair", Command.EXIT,0);
		voltar = new Command("Voltar", Command.OK,1);
	}

	protected void startApp() {
		display = Display.getDisplay(this);
		opcoesItens.append("TextField",null);
		opcoesItens.append("StringItem",null);
	        opcoesItens.append("ImageItem",null);
        	opcoesItens.append("ChoiceGroup",null);
    		opcoesItens.append("DateField",null);
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
		form.setCommandListener(this);
		display.setCurrent(form);	
	}
    
	private void exemploImageItem(){
	        Form form = new Form("ImageItem");
        	try { //devemos verificar se as imagens existem
             		Image cima = Image.createImage("/resources/cima.png");
             		Image baixo = Image.createImage("/resources/baixo.png");
             		Image esquerda = Image.createImage("/resources/esquerda.png");
             		Image direita = Image.createImage("/resources/direita.png");
			form.append(new ImageItem("Center", cima, ImageItem.LAYOUT_CENTER, null));
             		form.append(new ImageItem("Left", esquerda, ImageItem.LAYOUT_LEFT, null));
             		form.append(new ImageItem("Right", direita, ImageItem.LAYOUT_RIGHT, null));
             		form.append(new ImageItem("Default", baixo, ImageItem.LAYOUT_DEFAULT, null));
         	} catch (IOException ex) {
             		form.append("Erro ao abrir imagens");
         	}
         	form.addCommand(sair);
         	form.addCommand(voltar);
 		form.setCommandListener(this);
		display.setCurrent(form);
	}

	private void exemploChoiceGroup(){
        	Form form = new Form("ChoiceGroup");
        	// ChoiceGroup tipo Exclusive
        	String[] strings = new String[] { "Op��o Exclusive 1", "Op��o Exclusive 2", "Op��o  Exclusive 3", "Op��o  Exclusive 4" };
        	ChoiceGroup modoExclusive = new ChoiceGroup("Escolha uma op��o", ChoiceGroup.EXCLUSIVE,strings, null);
		form.append(modoExclusive);
        	// ChoiceGroup tipo Multiple
        	ChoiceGroup modoMulti = new ChoiceGroup("Escolha os itens desejados", ChoiceGroup.MULTIPLE);
        	form.append(modoMulti);
        	modoMulti.append("Op��o Multiple 1", null);
        	modoMulti.append("Op��o Multiple 2", null);
        	modoMulti.append("Op��o Multiple 3", null);
        	modoMulti.append("Op��o Multiple 4", null);
        	form.addCommand(sair);
        	form.addCommand(voltar);
        	form.setCommandListener(this);
        	form.setItemStateListener(this);
        	display.setCurrent(form);
    	}
	
	private void exemploDateField() {
		Form form = new Form("DateField");
		//Sem uso de Calendar
		//Campo em branco para uso de entrada de dados
		DateField campoDataTempo = new DateField("Data", DateField.DATE_TIME);
		form.append(campoDataTempo); 
		//Uso de Calendar - Inicializaremos Calendar para obter dados atuais
		Calendar baseCal = Calendar.getInstance(); 
		Date baseDate = new Date();  // Inicializa com a data e hora atuais
		Date horaZero = new Date(0); //Inicializa com a data e hora "zero" do sistema
		baseCal.setTime(baseDate);   //o Calend�rio ter� a hora atual
		// criacao dos DateFields Com Timezone de Brasilia
		DateField soHora = new DateField("Hora", DateField.TIME, TimeZone.getTimeZone("GMT-03:00"));
		DateField soData = new DateField("Data", DateField.DATE, TimeZone.getTimeZone("GMT-03:00"));
		//Sem Timezone
		DateField dataHora = new DateField("Hora e Data", DateField.DATE_TIME);
		soHora.setDate(baseCal.getTime());// inicializacao da hora e data no dateField
		soData.setDate(baseDate);
		dataHora.setDate(horaZero);
		form.append(soHora);
		form.append(soData);
		form.append(dataHora);
		form.addCommand(sair);
		form.addCommand(voltar);
		form.setCommandListener(this);
		form.setItemStateListener(this);
		display.setCurrent(form);
	}

    	private void handleChoiceSelection(Choice choice) {
        	int count = choice.size();
            	boolean[] states = new boolean[count];
            	int selCount = choice.getSelectedFlags(states);
            	if (selCount > 0) {
                	System.out.println("Itens selecionados:");
                	for (int i = 0; i < count; i++) {
                    		if (states[i]) {
                        		System.out.println("\t" + choice.getString(i));
                    		}
                	}
            	} else {
                	System.out.println("Nenhum item selecionado.");
            	}
            	int selectedIndex = choice.getSelectedIndex();
            	System.out.println("�ndice selecionado: " + selectedIndex);        
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
			}else if (selection == "ImageItem"){
		    	   	exemploImageItem();
			}else if (selection == "ChoiceGroup"){
        			exemploChoiceGroup();
			}else if (selection == "DateField"){
				exemploDateField();
			}	
		}
	}

	public void itemStateChanged(Item item) {        
		if (item instanceof TextField) {
			System.out.println("Conte�do do TextField = <" 
				+ ((TextField)item).getString() + ">");
		} else if (item instanceof ChoiceGroup) {
      		 	handleChoiceSelection((Choice)item);
    		}else if (item instanceof DateField) {
    			DateField campoDateField = (DateField)item;
    			Date data = campoDateField.getDate();
    			if (data != null) {
    				Calendar cal = Calendar.getInstance();
    				cal.setTime(data);
    				System.out.println("Data mudou para " + data);                
    			} else {
    				System.out.println("Date zerada");
    			}
    		}
	}    
}