package pe.mil.fap.utils.enums;

public enum DocumentVerificationEnum {

	CURRENT("Vigente"), EXPIRED("Caducado");

	private String state;

	private DocumentVerificationEnum(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	} 

}
