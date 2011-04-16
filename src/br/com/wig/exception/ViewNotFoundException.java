package br.com.wig.exception;

/**
 * 
 * @author thiago
 * 
 */
public class ViewNotFoundException extends RuntimeException {

	public ViewNotFoundException(String navigationDescription) {
		super(navigationDescription);
	}
}
