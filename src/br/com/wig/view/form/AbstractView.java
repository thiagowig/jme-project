package br.com.wig.view.form;

import javax.microedition.lcdui.Command;
import br.com.wig.commons.Strings;

/**
 * 
 * @author thiago
 *
 */
public abstract class AbstractView {

	private Command backCommand = new Command(Strings.BACK, Command.BACK, 1);

	private Command saveCommand = new Command(Strings.SAVE, Command.OK, 1);;

	private Command resetBaseCommand = new Command(Strings.RESET_BASE, Command.OK, 1);
	
	private Command closeCommnad = new Command(Strings.CLOSE, Command.EXIT, 1);
	
	private Command listAllCommnad = new Command(Strings.LIST_ALL, Command.EXIT, 1);

	protected Command getBackCommand() {
		return backCommand;
	}

	protected void setBackCommand(Command backCommand) {
		this.backCommand = backCommand;
	}

	protected Command getSaveCommand() {
		return saveCommand;
	}

	protected void setSaveCommand(Command save) {
		this.saveCommand = save;
	}

	protected Command getResetBaseCommand() {
		return resetBaseCommand;
	}

	protected void setResetBaseCommand(Command resetBase) {
		this.resetBaseCommand = resetBase;
	}

	protected Command getCloseCommnad() {
		return closeCommnad;
	}

	protected void setCloseCommnad(Command closeCommnad) {
		this.closeCommnad = closeCommnad;
	}

	protected Command getListAllCommnad() {
		return listAllCommnad;
	}

	protected void setListAllCommnad(Command listAllCommnad) {
		this.listAllCommnad = listAllCommnad;
	}
	
}
