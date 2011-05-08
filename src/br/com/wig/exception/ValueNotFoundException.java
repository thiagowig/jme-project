package br.com.wig.exception;

/**
 * 
 * @author thiago
 * 
 */
public class ValueNotFoundException extends RuntimeException {

	public ValueNotFoundException(String value) {
		super(value);
	}
}
