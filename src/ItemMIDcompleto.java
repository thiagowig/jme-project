/*
Java para Dispositivos Móveis - Desenvolvendo aplicações com J2ME
por Thienne M. Johnson 
Novatec Editora LTDa
ISBN: 978-85-7522-143-3
*/

import java.io.IOException;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Gauge;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.Item;
import javax.microedition.lcdui.ItemStateListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.StringItem;
import javax.microedition.lcdui.TextField;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.ImageItem;
import javax.microedition.lcdui.ChoiceGroup;
import javax.microedition.lcdui.DateField;
import javax.microedition.lcdui.Spacer;
import javax.microedition.lcdui.CustomItem;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class ItemMIDcompleto extends MIDlet 
        implements CommandListener, ItemStateListener {

    	private Display display;
    	private List opcoesItens;
    	private Command sair;
    	private Command voltar;
    	private StringItem mStringItem;
    
    	public ItemMIDcompleto(){
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
            	opcoesItens.append("Gauge",null);
            	opcoesItens.append("Spacer",null);
            	opcoesItens.append("CustomItem",null);
            	opcoesItens.addCommand(sair);
            	opcoesItens.setCommandListener(this);
            	display.setCurrent(opcoesItens);
    	}

	protected void pauseApp() {
    	}

	protected void destroyApp(boolean unconditional) {
    		notifyDestroyed();
    	}
    
	private void exemploTextField(){
        	Form formTF = new Form("TextField");  
        	formTF.append(new TextField("Nome", null, 8, TextField.ANY));
        	formTF.append(new TextField("Código de área", "091", 3, TextField.NUMERIC));
        	formTF.append(new TextField("Telefone", "88881111", 9, TextField.PHONENUMBER));
        	formTF.append(new TextField("Senha", null, 8, TextField.PASSWORD | TextField.NUMERIC));
        	formTF.addCommand(sair);
        	formTF.addCommand(voltar);
        	formTF.setCommandListener(this);
        	formTF.setItemStateListener(this);
        	display.setCurrent(formTF);
   	}

    	private void exemploStringItem(){
        	Form formSI = new Form("StringItem");  
        	formSI.append(new StringItem("Uso de StringItem", "Sem limite"));
        	StringItem testeString = new StringItem("Testando tamanho", "Com limite");
        	testeString.setPreferredSize(50,30);
        	formSI.append(testeString);
        	formSI.append(new StringItem("Com label", "Com texto\n"));
        	formSI.append(new StringItem(null, "Sem Label\n"));
        	formSI.append(new StringItem(null, "Pulando Linha\nbreak"));
        	formSI.append(new StringItem("Com Label 1", "Texto 1"));
        	formSI.append(new StringItem("Com Label 2", "Texto 2"));
        	formSI.addCommand(sair);
        	formSI.addCommand(voltar);
        	formSI.setCommandListener(this);
        	formSI.setItemStateListener(this);
        	display.setCurrent(formSI);
    	}

    	private void exemploImageItem(){
       		Form formII = new Form("ImageItem");
        	try { //devemos verificar se as imagens existem
            		Image cima = Image.createImage("/resources/cima.png");
            		Image baixo = Image.createImage("/resources/baixo.png");
            		Image esquerda = Image.createImage("/resources/esquerda.png");
            		Image direita = Image.createImage("/resources/direita.png");
            		formII.append(new ImageItem("Center", cima, ImageItem.LAYOUT_CENTER, null));
            		formII.append(new ImageItem("Left", esquerda, ImageItem.LAYOUT_LEFT, null));
            		formII.append(new ImageItem("Right", direita, ImageItem.LAYOUT_RIGHT, null));
            		formII.append(new ImageItem("Default", baixo, ImageItem.LAYOUT_DEFAULT, null));
        	} catch (IOException ex) {
            		formII.append("Erro ao abrir imagens");
        	}
        	formII.addCommand(sair);
        	formII.addCommand(voltar);
        	formII.setCommandListener(this);
        	formII.setItemStateListener(this);
        	display.setCurrent(formII);
    	}

    	private void exemploChoiceGroup(){
    		Form form = new Form("ChoiceGroup");
    		// ChoiceGroup tipo Exclusive
    		String[] strings = new String[] { "Opção MULTIPLE 1", "Opção MULTIPLE 2", "Opção  MULTIPLE 3", "Opção  MULTIPLE 4" };
    		ChoiceGroup modoExclusive = new ChoiceGroup("Escolha uma opção", ChoiceGroup.EXCLUSIVE,strings, null);
    		form.append(modoExclusive);
    		// ChoiceGroup tipo Multiple
    		ChoiceGroup modoMulti = new ChoiceGroup("Escolha os itens desejados", ChoiceGroup.MULTIPLE);
    		form.append(modoMulti);
    		modoMulti.append("Opção EXCLUSIVE 1", null);
    		modoMulti.append("Opção EXCLUSIVE 2", null);
    		modoMulti.append("Opção EXCLUSIVE 3", null);
    		modoMulti.append("Opção EXCLUSIVE 4", null);
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
        	System.out.println("Índice selecionado: " + selectedIndex);        
    	}

    	private void exemploDateField() {
        	Form form = new Form("DateField");
        	//Sem uso de Calendar
        	//Campo em branco para uso de entrada de dados
        	DateField campoDataTempo = new DateField("Data", DateField.DATE_TIME);
        	form.append(campoDataTempo); 
        	//Uso de Calendar
        	//Inicializaremos Calendar para obter dados atuais
        	Calendar baseCal = Calendar.getInstance(); 
        	Date baseDate = new Date();        // Inicializa com data e hora atuais
        	Date horaZero = new Date(0);        //Inicializa com data e hora "zero" do sistema
        	baseCal.setTime(baseDate);        //o Calendário terá a hora atual
        	// criacao dos DateFields
        	//Com Timezone de Brasilia
        	DateField soHora = new DateField("Hora", DateField.TIME, TimeZone.getTimeZone("GMT-03:00"));
        	DateField soData = new DateField("Data", DateField.DATE, TimeZone.getTimeZone("GMT-03:00"));
        	//Sem Timezone
        	DateField dataHora = new DateField("Hora e Data", DateField.DATE_TIME);
        	// inicializacao da hora e data no dateField
        	soHora.setDate(baseCal.getTime());
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

    	private void exemploGauge() {
        	Form form = new Form("Gauge");
        	Gauge interativo = new Gauge("Interativo", true, 100, 50);
        	mStringItem = new StringItem(null,null);
        	form.append(interativo);
        	form.append(mStringItem);
        	form.append(new Gauge("Não Interativo", false, 100, 50));
        	form.addCommand(sair);
        	form.addCommand(voltar);
        	form.setCommandListener(this);
        	form.setItemStateListener(this);
        	display.setCurrent(form);
    	}

    	private void exemploSpacer() {
        	Form form = new Form("Spacer");
        	StringItem frase = new StringItem("Testando Spacer",null);
        	Spacer testeSpacer = new Spacer(30,30);
        	form.append(testeSpacer);
        	form.append(frase);
        	form.addCommand(sair);
        	form.addCommand(voltar);
        	form.setCommandListener(this);
        	display.setCurrent(form);
    	}

    	private void exemploCustomItem() {
        	Form form = new Form("CustomItem");
        	form.append(new ExemploCustomItem("Exemplo de CustomItem"));
        	form.addCommand(sair);
        	form.addCommand(voltar);
        	form.setCommandListener(this);
        	form.setItemStateListener(this);
        	display.setCurrent(form);
    	}

    	public void commandAction(Command c, Displayable d) {
    		if(c == sair) {
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
           		}else if (selection == "Gauge"){
       	    			exemploGauge();
           		}else if (selection == "Spacer"){
          	    		exemploSpacer();
           		}else if (selection == "CustomItem"){
      	    			exemploCustomItem();
           		} 
        	}	   
    	}
   
    	public void itemStateChanged(Item item) {        
        	if (item instanceof TextField) {
            		System.out.println("Conteúdo do TextField = <" + ((TextField)item).getString() + ">");
        	}else if (item instanceof ChoiceGroup) {
            		handleChoiceSelection((Choice)item);
        	} else if (item instanceof DateField) {
            	DateField campoDateField = (DateField)item;
            	Date data = campoDateField.getDate();
            		if (data != null) {
                		Calendar cal = Calendar.getInstance();
                		cal.setTime(data);
                		System.out.println("Data mudou para " + data); 
            		} else {
               			 System.out.println("Data zerada");
              		}
        	}else if (item instanceof Gauge) {
            		int value = ((Gauge)item).getValue();
            		System.out.println("Gauge value set to " + value);
            		mStringItem.setText("Value = " + ((Gauge)item).getValue());
            	}else if (item instanceof CustomItem) {
        		System.out.println("CustomItem modificado");
        	}
         }
   }

