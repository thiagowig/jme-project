import javax.microedition.midlet.MIDlet;
import javax.microedition.lcdui.Choice;
import javax.microedition.lcdui.Form;
import javax.microedition.lcdui.Display;
import javax.microedition.lcdui.Displayable;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.CommandListener;
import javax.microedition.lcdui.List;
import javax.microedition.lcdui.TextBox;
import javax.microedition.lcdui.TextField;
public class FormMID1 extends MIDlet implements CommandListener {
 private Display display;
 //Forms
 private Form inicio;
 private Form logado;
 private Form recusado;
 //Comandos
 private Command cancelar;
 private Command incluir;
 private Command confirmarNovo;
 private Command confirmarSelecao;
 private Command voltarNovo;
 private Command voltarSelecao;
 private Command selecionar;
 
 private TextBox valor;
 private TextBox textoCategoria;
 List listaCategoria = null;

 public FormMID1() {
  //Forms
  inicio = new Form("Despesa / Rendimentos");
  logado = new Form("Selecionar Categoria");
  recusado = new Form("Vincular nova categoria");
  //Comandos
  cancelar = new Command("Cancelar", Command.CANCEL, 0);
  incluir = new Command("Incluir", Command.OK, 1);
  selecionar = new Command("Selecionar", Command.OK, 1);
  confirmarNovo = new Command("Confirmar", Command.OK, 1);
  confirmarSelecao = new Command("Confirmar", Command.OK, 1);
  voltarNovo = new Command("Voltar", Command.BACK, 0); 
  voltarSelecao = new Command("Voltar", Command.BACK, 0); 
  
  valor = new TextBox("valor","Valor: ", 30, TextField.ANY);
  textoCategoria = new TextBox("valor","Nova Categoria: ", 30, TextField.ANY);
  listaCategoria = new List("Lista Categoria", Choice.MULTIPLE);
   }

 public void startApp() {
  display = Display.getDisplay(this);
  //FORM Inicial
  inicio.addCommand(cancelar);
  inicio.addCommand(incluir);
  inicio.addCommand(selecionar);
  inicio.setCommandListener(this);
  //Form Categoria
  logado.addCommand(confirmarSelecao);
  logado.addCommand(voltarSelecao);
  logado.setCommandListener(this);
  //Form Incluir
  recusado.addCommand(voltarNovo);
  recusado.addCommand(confirmarNovo);
  recusado.setCommandListener(this);
  display.setCurrent(inicio);
 }
 
 public void pauseApp() {
 }

 public void destroyApp(boolean unconditional) {
  notifyDestroyed();
 }
 
 public void confirmarNovo(String valor){
  
 }
 
 public void confirmarSelecao(String valor){
  
 }
 
 public void listarCategorias() {
      listaCategoria.append("Gasolina", null);
      listaCategoria.append("Salario", null);
      listaCategoria.append("Faculdade", null);
      listaCategoria.append("Alimentação", null);
      listaCategoria.setCommandListener(this);
    }

 public void commandAction(Command c, Displayable d) {
  if(c == cancelar) {
   destroyApp(true);
  } else if(c == incluir) {
   display.setCurrent(logado);
  } else if(c == selecionar){
   listarCategorias();
   display.setCurrent(recusado); 
  }  else if(c == voltarNovo){
   display.setCurrent(inicio); 
  }  else if(c == voltarSelecao){
   display.setCurrent(inicio); 
  } else if(c == confirmarNovo){
   if(valor.getString() != null){
    confirmarNovo(valor.getString());
    display.setCurrent(inicio); 
   }
  } else if(c == confirmarSelecao){
   if(valor.getString() != null){
    confirmarSelecao(valor.getString());
    display.setCurrent(inicio);
   }
  } 
   }
}