package pe.mil.fap.utils.enums;

public enum GenderEnum {
	MALE("Masculino"), FEMALE("Femenino");

	private String description;

	private GenderEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
