package pe.mil.fap.utils.enums;

public enum SegmentTypeEnum {
	DETENTION("Detención"), ENTRANCE("Entrada"), EXIT("Salida");

	private String description;

	private SegmentTypeEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}
}
