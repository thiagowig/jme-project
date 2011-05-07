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
import java.util.Vector;

public class RmsMID5 extends MIDlet implements CommandListener, ItemStateListener{
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
	private Form listagemReg;
	private ChoiceGroup listaRegistros;
	private Command listar, apagar, ordenar, filtrar;
	boolean deletedFlags[] = null; 
	private Vector vecNomes;

  	public RmsMID5(){
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

		apagar = new Command("Apagar Registros", Command.SCREEN, 1);
		listar = new Command("Listar Registros", Command.SCREEN, 1);
		ordenar = new Command("Ordenar Registros", Command.SCREEN, 1);
		filtrar = new Command("Filtrar Registros", Command.SCREEN, 1);
		listaRegistros = new ChoiceGroup("Registros",ChoiceGroup.MULTIPLE);
		vecNomes = new Vector();

		telaInicial.addCommand(listar);
		telaInicial.addCommand(ordenar); 
		telaInicial.addCommand(filtrar); 
		telaInicial.addCommand(zerar); 
		telaInicial.setCommandListener(this);

		listagemReg = new Form("");
		listagemReg.append(listaRegistros);
		listagemReg.addCommand(apagar);
		listagemReg.addCommand(voltar);
		listagemReg.setItemStateListener(this);
		listagemReg.setCommandListener(this);
  	}
  
	public void startApp() {
      		try {
          		rs = RecordStore.openRecordStore("RSTeste", true );
          		rs.addRecordListener(new testeRecordListener());
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
		if (item instanceof ChoiceGroup){
			deletedFlags = new boolean[listaRegistros.size()];
			listaRegistros.getSelectedFlags(deletedFlags);
		}
  	}
	
	private void gerarTela(Vector nomes, int totalElementos){
		for (int i = listaRegistros.size(); i > 0; i--)
			listaRegistros.delete(i - 1);
		for (int i = 0; i < nomes.size(); i++){
			Nomes item = (Nomes) vecNomes.elementAt(i);
			String nome = item.getNome();
			String telefone = item.getTelefone();
			listaRegistros.append(nome+" "+telefone, null);
		}
		display.setCurrent(listagemReg);	
	}
	
	private void listarRegistros(FiltroNome f, ComparadorNome c){
		RecordEnumeration rEnum = null;
		vecNomes.removeAllElements();
		try{
			rEnum = rs.enumerateRecords(f,c,false);
			int posicao = 0;
			while(rEnum.hasNextElement()){
				int id = rEnum.nextRecordId();
				byte[] dados = rs.getRecord(id);
				ByteArrayInputStream BAIS = new ByteArrayInputStream(dados);
				DataInputStream DIS = new DataInputStream(BAIS);
				String nomeLido = DIS.readUTF();
				String telefoneLido = DIS.readUTF();
				int tipo = DIS.readInt(); 
				Nomes itemAgenda = new Nomes(nomeLido, telefoneLido, tipo, id, posicao);
				vecNomes.addElement(itemAgenda);
				posicao++;
			}
			gerarTela(vecNomes,rEnum.numRecords() );
		}
		catch (Exception exc) {
			mostrarAlerta("Erro na listagem",exc.toString());
		}
		finally {
			rEnum.destroy();
		}
	}
	
	private void apagarRegistros(){
		try {
			for (int i=0; i<deletedFlags.length;i++){
				if (deletedFlags[i]==true){
					Nomes itemAgenda = (Nomes)vecNomes.elementAt(i);
					try{
						rs.deleteRecord(itemAgenda.getRecordId());
					}
					catch (Exception exc){}
				}
			}
			display.setCurrent(telaInicial);
		}
		catch (Exception exc) {
			Alert erro = new Alert ("Nenhum registro foi selecionado","Refaça a operação ou volte à tela inicial", null, AlertType.ERROR);
			erro.setTimeout(Alert.FOREVER);
			display.setCurrent(erro);
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
  		}else if (command == apagar) {
			apagarRegistros();
		}else if (command == listar) {
			listagemReg.setTitle("Listagem Geral");
			listaRegistros.deleteAll();
			listarRegistros(null,null);
		}else if (command == ordenar) {
			listagemReg.setTitle("Registros Ordenados");
			listaRegistros.deleteAll();
			ComparadorNome comparador = new ComparadorNome();
			listarRegistros(null,comparador);
		}else if (command == filtrar) {
			listagemReg.setTitle("Registros Filtrados");
			listaRegistros.deleteAll();
			FiltroNome filtro = new FiltroNome("Maria");
			ComparadorNome comparador = new ComparadorNome();
			listarRegistros(filtro,comparador);
		}
  	}
}


class FiltroNome implements RecordFilter {
	String nome;
	public FiltroNome(String nomeFiltrado) {
		nome = nomeFiltrado;
	}
	public boolean matches(byte [] registro) {
		try{
			ByteArrayInputStream BAIS = new ByteArrayInputStream(registro);
			DataInputStream DIS = new DataInputStream(BAIS);
			String nomeLido = DIS.readUTF();
			DIS.close();
			BAIS.close();
			if (nome.compareTo(nomeLido) == 0 )
				return true;
		}
		catch (Exception exc) {
			System.out.println("Exceção: " + exc.getMessage());
		}
		return false;
	}
}


class ComparadorNome implements RecordComparator {
	public int compare(byte [] reg1, byte [] reg2) {
		try{
			ByteArrayInputStream BAIS = new ByteArrayInputStream(reg1);
			DataInputStream DIS = new DataInputStream(BAIS);
			String nome1 = DIS.readUTF();
			DIS.close();
			BAIS.close();
			ByteArrayInputStream BAIS2 = new ByteArrayInputStream(reg2);
			DataInputStream DIS2 = new DataInputStream(BAIS2);
			String nome2 = DIS2.readUTF();
			DIS2.close();
			BAIS2.close();
			if (nome1.compareTo(nome2) < 0 ){
				return PRECEDES;
			}if (nome1.compareTo(nome2) > 0 ){
				return FOLLOWS;
			}
		}
		catch (Exception exc) {
			System.out.println("Exceção: " + exc.getMessage());
		}
		return EQUIVALENT;
	}
}

class Nomes {
	private String nome, telefone;
	private int tipo, recordId;

	public Nomes(String nome, String telefone,int tipo, int recordId, int posicao){
		this.nome = nome;
		this.telefone = telefone;
		this.tipo = tipo;
		this.recordId = recordId;
	}
	public String getNome(){
		return nome;
	}
	public String getTelefone(){
		return telefone;
	}
	public void setNome(String nome){
		this.nome = nome;
	}
	public void setTelefone(String telefone){
		this.telefone = telefone;
	}
	public int getRecordId(){
		return recordId;
	}
}

class testeRecordListener implements RecordListener {
	public void recordAdded(RecordStore recordStore, int recordId){
		//executado ao adicionar um registro
		System.out.println("Registro adicionado!");
	}
	public void recordDeleted(RecordStore recordStore, int recordId){
		//executado ao apagar um registro
		System.out.println("Registro apagado!");
	}
	public void recordChanged(RecordStore recordStore, int recordId){
		//executado ao modificar um registro
		System.out.println("Registro modificado!");
	}
}
