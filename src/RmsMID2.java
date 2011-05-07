/*
Java para Dispositivos M�veis - Desenvolvendo aplica��es com J2ME
por Thienne M. Johnson 
Novatec Editora LTDa
ISBN: 978-85-7522-143-3
*/

import javax.microedition.rms.*;
import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.*; // todas as classes para facilitar visualiza��o do c�digo

public class RmsMID2 extends MIDlet implements CommandListener {
	private Display display;
	private Alert alerta;
	private Form form; 
	private Command sair; 
	private Command iniciar;
	private Command adicionar;
	private RecordStore rs = null;
	private boolean iniciado = false;
	String tipo, mensagem;
	int id = 0;
	
	public RmsMID2(){
		display = Display.getDisplay(this);
		sair = new Command("Sair", Command.EXIT, 0);
		iniciar = new Command("Iniciar", Command.SCREEN, 1);
		adicionar = new Command("Adicionar Registros", Command.SCREEN, 1);
		form = new Form("Manipula��o de Registros");
		form.addCommand(sair);
		form.addCommand(iniciar);
		form.setCommandListener(this);
	}
	
	public void startApp() {
		display.setCurrent(form);
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
	
	public void commandAction(Command command, Displayable displayable)  {
		if (command == sair) {
			if (iniciado == true){
				try{
					rs.closeRecordStore();
				}
				catch (Exception error){
					tipo = "Erro fechando o RecordStore";
					mensagem = error.toString();
					mostrarAlerta(tipo,mensagem);
				}
			if (RecordStore.listRecordStores() != null){
				try{
					RecordStore.deleteRecordStore("RSTeste");
				}
				catch (Exception error){
					tipo = "Erro removendo o RecordStore";
					mensagem = error.toString();
					mostrarAlerta(tipo,mensagem);
				}
			}
		}
		destroyApp(true);
	}else if (command == adicionar) {
		try {//adicionando registro
			String dadosSaida = "Novo Registro, ID="+id;
			byte[] byteOutputData = dadosSaida.getBytes();
			rs.addRecord(byteOutputData, 0, byteOutputData.length); //salvou o registro
			id++;
		}
		catch ( Exception error) {
			tipo = "Erro de cria��o de registro";
			mensagem = error.toString();
			mostrarAlerta(tipo, mensagem);
		}
		try {// leitura de registro
			byte[] byteInputData = new byte[1];
			int length = 0;
			
			// No la�o a seguir, estamos navegando por todos os registros at� chegar ao �ltimo adicionado
			for (int x = 1; x <= rs.getNumRecords(); x++){

				/* Faremos um teste para verificar se o tamanho do registro (no caso, a chave prim�ria
				 de acesso � x) � maior do que o tamanho da vari�vel byteInputData, isto �, se h� algo
				 no registro al�m da chave. Em caso positivo, a vari�vel armazena o conte�do do registro
				 convertido em byte. O tamanho do registro � armazenado na vari�vel length, para podermos
				 convert�-la depois em String (precisamos saber quantos bytes ser�o lido) 
				*/
				if (rs.getRecordSize(x) > byteInputData.length) {
					byteInputData = new byte[rs.getRecordSize(x)];
				}
				length = rs.getRecord(x, byteInputData, 0);
			}
			//mostra o �ltimo registro armazenado no Alert
			mensagem =  new String(byteInputData, 0,length);
			tipo = "Adicionou com sucesso";
			mostrarAlerta(tipo, mensagem);
		}
		catch (Exception error){
			tipo = "Erro de leitura";
			mensagem = error.toString();
			mostrarAlerta(tipo,mensagem);
		}
	}else if (command == iniciar) {
		try {//abre o RecordStore
			rs = RecordStore.openRecordStore("RSTeste", true );
			form.removeCommand(iniciar); //retira comando de inicializacao
			form.addCommand(adicionar); //novo comando na tela
		}
		catch (Exception error){
			tipo = "Erro ao abrir RecordStore";
			mensagem = error.toString();
			mostrarAlerta(tipo,mensagem);
		}
	}
  }
}
