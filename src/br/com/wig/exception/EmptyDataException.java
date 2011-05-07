package br.com.wig.exception;
/**
 * 
 * @author thiago
 *
 */
public class EmptyDataException extends RuntimeException {
	
	public EmptyDataException(String emptyData) {
		super(emptyData);
	}

}
