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
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.Choice;

public class ListMID1 extends MIDlet implements CommandListener {
	private Display display;
	private Command voltar;
	private Command cancelar;
   	List opcoesEXC = null;
   	List opcoesMUL = null;
   	List opcoesIMP = null;
    	
	public ListMID1() {
		opcoesIMP = new List("Menu:", List.IMPLICIT); 
		cancelar = new Command("Cancelar", Command.CANCEL, 0);
   		voltar = new Command("Voltar", Command.OK, 0);
   		opcoesEXC = new List("Metodo de Pagamento", Choice.EXCLUSIVE);
   		opcoesMUL = new List("Produtos", Choice.MULTIPLE);
   	}

   	public void startApp() {
	       	display = Display.getDisplay(this);
       		opcoesIMP = new List("Menu", List.IMPLICIT); 
       		opcoesIMP.append("Produtos",null);
       		opcoesIMP.append("Pagamentos",null);
       		opcoesIMP.addCommand(cancelar);
       		opcoesIMP.setCommandListener(this);
       		display.setCurrent(opcoesIMP);    
	}
	
	public void pauseApp() {
    	}
	
	public void destroyApp(boolean unconditional) {
  		 notifyDestroyed();
    	}
	
	public void menuPagamentos() {
		opcoesEXC.append("Visa", null);
    		opcoesEXC.append("MasterCard", null);
	      	opcoesEXC.append("Amex", null);
      		opcoesEXC.append("Diners", null);
      		opcoesEXC.addCommand(voltar);
      		opcoesEXC.setCommandListener(this);
      		display.setCurrent(opcoesEXC);
	}

	public void menuProdutos() {
   	 	opcoesMUL.append("Roupa", null);
	      	opcoesMUL.append("Sapatos", null);
      		opcoesMUL.append("Perfumes", null);
	      	opcoesMUL.append("Alimentação", null);
      		opcoesMUL.append("Variados", null);	
	      	opcoesMUL.append("Diversão", null);
      		opcoesMUL.addCommand(voltar);
    	  	opcoesMUL.setCommandListener(this);
      		display.setCurrent(opcoesMUL);
   	}

   	public void commandAction(Command c, Displayable d) {
   		 if (c == List.SELECT_COMMAND){
   		 		String selecionado = opcoesIMP.getString(opcoesIMP.getSelectedIndex());
    	 		if (selecionado == "Pagamentos") 
    		 		menuPagamentos();
    	 		else if(selecionado == "Produtos")
    		 		menuProdutos();
      		}else if(c==cancelar) {
         		destroyApp(true);    
	       	}else if(c==voltar) {
         		display.setCurrent(opcoesIMP);
       		}
  	}
}
