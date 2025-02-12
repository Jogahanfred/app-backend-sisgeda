package pe.mil.fap.utils.enums;

public enum PersonalSituationEnum {

	BLOCKED("Acceso Denegado"), 
	PERMITTED("Acesso Permitido"), 
	SUSPENDED("Acceso Suspendido"), 
	IN_PROCESS("Acceso en Proceso");

	private String description;

	private PersonalSituationEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
