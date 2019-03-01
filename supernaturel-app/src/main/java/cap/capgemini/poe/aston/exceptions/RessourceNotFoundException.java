package cap.capgemini.poe.aston.exceptions;

public class RessourceNotFoundException extends RuntimeException{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final String ressourceName;
	private final String fieldName;
	private final Object fieldValue;
	
	public RessourceNotFoundException(String ressourceName, String fieldName, Object fieldValue) {
		super(String.format("%s not found with %s : %s", ressourceName, fieldName, fieldValue));
		this.ressourceName = ressourceName;
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}

	public String getRessourceName() {
		return ressourceName;
	}

	public String getFieldName() {
		return fieldName;
	}

	public Object getFieldValue() {
		return fieldValue;
	}
}
