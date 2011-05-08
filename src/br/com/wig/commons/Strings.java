package br.com.wig.commons;
/**
 * 
 * @author thiago
 *
 */
public class Strings {

	public static final String CATEGORY = "Categoria";
	
	public static final String ENTRY_TYPE = "Tipo de Lan\u00e7amento";
	
	public static final String SAVE = "Salvar";
	
	public static final String NAME = "Nome";
	
	public static final String RESET_BASE = "Excluir todos registros";
	
	public static final String BACK = "Voltar";
	
	public static final String CLOSE = "Sair";
	
	public static final String LIST_ALL = "Listar todos";
	
	public static final String ERROR = "Erro";
	
	public static final String DEFAULT_ERROR_MESSAGE = "Falha na opera\u00e7\u00e3o. Tente novamente.";
	
	public static final String REQUIRED_VALUES_NOT_SETTED = "Valores obrigat\u00f3rios n\u00e3o preenchidos";
	
	public static final String DUPLICATED_VALUE = "Valores duplicados";
	
	public static final String SUCCESS = "A opera\u00e7\u00e3o foi efetuada com sucesso";
	
	public static final String CATEGORY_REQUIRED = "\u00c9 necess\u00e1rio que existam categorias cadastradas.";
	
	public static boolean isEmpty(String valueToCheck) {
		return valueToCheck == null ? true : valueToCheck.equals("") ? true : false;
	}
	
	public static boolean isEmpty(int valueToCheck) {
		return valueToCheck < 1 ? true : false;
	}
	
}
