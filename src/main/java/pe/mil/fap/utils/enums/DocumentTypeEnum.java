package pe.mil.fap.utils.enums;

public enum DocumentTypeEnum {
	PASSPORT("Pasaporte"), 
	DNI("Documento Nacional Identidad"), 
	ID_CARD("Cédula"), 
	DRIVER_LICENSE("Licencia de Conducir"),
	RESIDENT_CARD("Tarjeta de Residencia");

	private String description;

	private DocumentTypeEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
