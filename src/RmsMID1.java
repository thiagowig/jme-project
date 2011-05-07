/*
Java para Dispositivos Móveis - Desenvolvendo aplicações com J2ME
por Thienne M. Johnson 
Novatec Editora LTDa
ISBN: 978-85-7522-143-3
*/

import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.Form;
import javax.microedition.rms.*;

public class RmsMID1 extends MIDlet implements CommandListener{
	private Display display;
	private RecordStore teste = null;
	private Command abreRS;
	private Command sair;
	private Form telaInicial;
	private boolean aberto = false;
	
	public RmsMID1() {
		telaInicial = new Form("Testando o RMS");
		sair = new Command ("Sair", Command.EXIT,0);
		abreRS = new Command ("Abrir RS", Command.OK,1);
	}
	
	protected void destroyApp(boolean arg0){
		notifyDestroyed();
	}

	protected void pauseApp() {
		}

	protected void startApp(){
		display = Display.getDisplay(this);
		telaInicial.addCommand(abreRS);
		telaInicial.addCommand(sair);
		telaInicial.setCommandListener(this);
		display.setCurrent(telaInicial);
	}

	private void abrirRS(){
		try{
			teste = RecordStore.openRecordStore("RSteste",true);
			aberto = true;
			System.out.println("RecordStore aberto");
		}
		catch (Exception e  ){
			System.out.println(e);
		}
	}

	public void commandAction (Command c, Displayable d){
		if(c == sair) {
			if (aberto==true){
	    			try {
	    				teste.closeRecordStore();
	    			}
	    			catch (Exception e  ){
	    				System.out.println(e);
	    			}
	    		}
	    		destroyApp(true);
	    	} else if (c == abreRS){
	    		if (aberto==false){
    		    		abrirRS();	
	    		}else{
	    			System.out.println("RS já está aberto");
	    		}
		}
	}	
}
