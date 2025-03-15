package pe.mil.fap.utils.enums;

public enum VisitSituationEnum {

	COMPLETED("Culminada"), //Ya terminó y almenos una persona ingreso
	IN_PROGRESS("En progreso"), //La visita está ocurriendo en este momento, PERO tiene al menos una persona que ha ingresado y no le han dado salida
	EXPIRED("Expirada"), //Cuando la visita ya paso y ninguna persona entro
	PENDING("Pendiente"), //Cuando esta programa pero aun no ha iniciado
	STARTED("Iniciada"), //Cuando esta en el rango de visita, y almenos un visitante a ingresado
	SCHEDULED("Programada"); //La visita esta ocurriendo en este momento, PERO no tiene ninguna persona ingresada

	private String description;

	private VisitSituationEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}

