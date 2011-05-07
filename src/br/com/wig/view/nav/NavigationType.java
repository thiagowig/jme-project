package br.com.wig.view.nav;
/**
 * 
 * @author thiago
 *
 */
public class NavigationType {

	public static final Integer INDEX = new Integer(1);
	
	public static final Integer CATEGORY = new Integer(2);
	
	public static final Integer ENTRY_TYPE = new Integer(3);
	
	public static final Integer ENTRY = new Integer(4);
	
	public static String getNavigationDescription(Integer navigatinTypeCode) {
		if (navigatinTypeCode.equals(NavigationType.INDEX)) {
			return "P\u00e1gina de Index";
		} else if (navigatinTypeCode.equals(NavigationType.CATEGORY)) {
			return "P\u00e1gina de Categoria";
		} else if (navigatinTypeCode.equals(NavigationType.ENTRY_TYPE)) {
			return "P\u00e1gina de Tipo de Lan\u00e7amento";
		} else if (navigatinTypeCode.equals(NavigationType.ENTRY)) {
			return "P\u00e1gina de Lan\u00e7amento";
		} else {
			return null;
		}
	}
}
