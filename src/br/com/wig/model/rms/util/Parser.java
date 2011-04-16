package br.com.wig.model.rms.util;
/**
 * 
 * @author thiago
 *
 */
public class Parser {
	
	private String fieldsConcatenated;
	
	private final String FIELD_SEPARATOR = "';'";

	public Parser(String[] fieldsValues) {
		this.fieldsConcatenated = this.concatenateFields(fieldsValues);
	}
	
	private String concatenateFields(String[] fieldsValues) {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < fieldsValues.length; i++) {
			buffer.append(fieldsValues[i]);
			buffer.append(this.FIELD_SEPARATOR);
		}
		return buffer.toString();
	}
	
	public String getConcatenateFields() {
		return this.fieldsConcatenated;
	}
}
