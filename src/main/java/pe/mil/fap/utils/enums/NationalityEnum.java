package pe.mil.fap.utils.enums;

public enum NationalityEnum {
	PERU("Peru"), VENEZUELA("Venezuela");

	private String description;

	private NationalityEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
