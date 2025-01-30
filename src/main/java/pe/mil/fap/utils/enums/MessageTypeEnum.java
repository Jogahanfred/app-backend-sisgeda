package pe.mil.fap.utils.enums;

public enum MessageTypeEnum {
	SUCCESS("success"), INFORMATION("info"), WARNING("warning"), DANGER("danger");

	private String description;

	private MessageTypeEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

}
