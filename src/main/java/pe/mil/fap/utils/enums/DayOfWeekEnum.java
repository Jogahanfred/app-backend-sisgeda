package pe.mil.fap.utils.enums;

public enum DayOfWeekEnum {

	MONDAY("Lunes"), 
	TUESDAY("Martes"), 
	WEDNESDAY("Miercoles"), 
	THURSDAY("Jueves"),
	FRIDAY("Viernes"),
	SATURDAY("Sabado"),
	SUNDAY("Domingo");

	private String description;

	private DayOfWeekEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
