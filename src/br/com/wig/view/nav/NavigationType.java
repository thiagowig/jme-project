package br.com.wig.view.nav;
/**
 * 
 * @author thiago
 *
 */
public class NavigationType {

	public static final Integer INDEX = new Integer(1);
	
	public static final Integer CATEGORY = new Integer(2);
	
	public static String getNavigationDescription(Integer navigatinTypeCode) {
		if (navigatinTypeCode.equals(NavigationType.INDEX)) {
			return "P�gina de index";
		} else if (navigatinTypeCode.equals(NavigationType.CATEGORY)) {
			return "P�gina de categoria";
		} else {
			return null;
		}
	}
}
