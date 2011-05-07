/*
Java para Dispositivos Móveis - Desenvolvendo aplicações com J2ME
por Thienne M. Johnson 
Novatec Editora LTDa
ISBN: 978-85-7522-143-3
*/

import javax.microedition.midlet.MIDlet;
import java.io.*;
import javax.microedition.rms.*;
import javax.microedition.lcdui.*;

public class RmsMID4 extends MIDlet implements CommandListener, ItemStateListener{
  	private Display display;
  	private Alert alerta;
  	private Form telaInicial, adicionaReg;
  	private TextField nome, telefone;
  	private Command sair, adicionar, voltar, salvar,zerar;
  	private RecordStore rs = null;
  	String  tipo, mensagem;
  	boolean gravacaoOK = false; 
  	private ChoiceGroup tipo_contato;
  	private String[] listaTipo = {"Amigos", "Família", "Trabalho"};
  	private Form telaInformacoes;
  	private Command informacoes;

  	public RmsMID4(){
    		display = Display.getDisplay(this);
    		sair = new Command("Sair", Command.EXIT, 0);
    		adicionar = new Command("Adicionar Registros", Command.SCREEN, 1);
    		zerar = new Command("Zerar Registros", Command.SCREEN, 1);
    		voltar = new Command("Voltar", Command.SCREEN, 1);
    		salvar = new Command("Salvar Registro", Command.SCREEN, 1);
    		nome = new TextField("Nome:","",20, TextField.ANY);
    		telefone = new TextField("Telefone:","",10, TextField.PHONENUMBER);

    		telaInicial = new Form("Manipulação de Registros");
    		telaInicial.addCommand(sair);
    		telaInicial.addCommand(adicionar);
    		telaInicial.addCommand(zerar); 
    		telaInicial.setCommandListener(this);
    
    		adicionaReg = new Form ("Adicione um contato e telefone");
    		tipo_contato = new ChoiceGroup ("Tipo do contato", Choice.POPUP, listaTipo, null);
    		adicionaReg.append(nome);
    		adicionaReg.append(telefone);
    		adicionaReg.append(tipo_contato);
    		adicionaReg.addCommand(salvar);
    		adicionaReg.addCommand(voltar);
    		adicionaReg.setItemStateListener(this);
    		adicionaReg.setCommandListener(this);
    
		informacoes = new Command("Informações Gerais", Command.SCREEN, 1);
		telaInicial.addCommand(informacoes);
		telaInformacoes = new Form ("Informações sobre o sistema");
		telaInformacoes.addCommand(voltar);
		telaInformacoes.setCommandListener(this);
  	}
  
	public void startApp() {
      		try {
          		rs = RecordStore.openRecordStore("RSTeste", true );
      		}
      		catch (Exception exc){
        		mostrarAlerta("Erro ao abrir o RecordStore",exc.toString());
      		}
      		display.setCurrent(telaInicial);
  	}
  
	public void pauseApp(){
  	}
  
	public void destroyApp( boolean unconditional ) {
      		notifyDestroyed();
  	}

	private void mostrarAlerta(String tipoAlerta, String msg){
      		alerta = new Alert(tipoAlerta, msg, null, AlertType.WARNING); 
      		alerta.setTimeout(Alert.FOREVER); 
      		display.setCurrent(alerta);
  	}
	
  	private void mostrarInformacoes(){
		try{
			String nomeRs = rs.getName();
			String numero = Integer.toString(rs.getNumRecords());
			String tamanho = Integer.toString(rs.getSize());
			String disponivel = Integer.toString(rs.getSizeAvailable());
			StringItem nomeSI = new StringItem ("Nome do RecordStore", nomeRs);
			StringItem tamanhoOc = new StringItem ("Tamanho ocupado (em bytes)", tamanho.toString());
			StringItem tamanhoDis = new StringItem ("Tamanho disponível (em bytes)", disponivel);
			StringItem numeroReg = new StringItem ("Número de registros existentes", numero);
			telaInformacoes.append(nomeSI);
			telaInformacoes.append(tamanhoOc);
			telaInformacoes.append(tamanhoDis);
			telaInformacoes.append(numeroReg);
			display.setCurrent(telaInformacoes);
		}
		catch (Exception exc) {
			mostrarAlerta("Erro na listagem",exc.toString());
		}
	}

	private void adicionarRegistro(){
		try{
			ByteArrayOutputStream BAOS = new ByteArrayOutputStream();
			DataOutputStream DOS = new DataOutputStream(BAOS);
			DOS.writeUTF(nome.getString());
			DOS.writeUTF(telefone.getString());
			DOS.writeInt(tipo_contato.getSelectedIndex());
			byte [] bRec = BAOS.toByteArray();
	       		rs.addRecord(bRec,0,bRec.length);
	          	DOS.close();
	          	BAOS.close();
	          	display.setCurrent(telaInicial);
		 }
	    	catch (Exception exc) {
          		mostrarAlerta("Erro em adicionar",exc.toString());
	    	}
  	}
  
	private void zerarRegistros(){
		try {
			rs.closeRecordStore();
		  	RecordStore.deleteRecordStore("RSTeste");
		  	rs = RecordStore.openRecordStore("RSTeste", true );
      		}
	  	catch (Exception exc){
			mostrarAlerta("Erro ao recriar o RecordStore",exc.toString());
      		}  
  		display.setCurrent(telaInicial);
  	}
  
	public void itemStateChanged (Item item){
		if (item instanceof TextField){
			String teste = ((TextField)item).getString();
		  	if (teste != "") {gravacaoOK = true;}
  		}
	}
  
	public void commandAction(Command command, Displayable displayable)  {
    		if (command == sair) {
         		try{
            			rs.closeRecordStore();
          		}
          		catch (Exception exc){
        			mostrarAlerta("Erro fechando o RecordStore",exc.toString());
          		}
          		destroyApp(true);  
   		}else if (command == adicionar) {
	   		nome.setString("");
	   		telefone.setString("");
    			display.setCurrent(adicionaReg);
   		}else if (command == salvar) {
	   		if (gravacaoOK== false){
		   		mostrarAlerta("Registro vazio não será salvo", "Tente novamente");
	   		}else {
		   		adicionarRegistro();
	   		}
   		}else if (command == zerar) {
	   		zerarRegistros();
   		}else if (command == voltar) {
	  		display.setCurrent(telaInicial);
  		}else if (command == informacoes) {
			telaInformacoes.deleteAll();
			mostrarInformacoes();
		}
  	}
}
