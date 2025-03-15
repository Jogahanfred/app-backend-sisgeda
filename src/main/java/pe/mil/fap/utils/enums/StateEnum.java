package pe.mil.fap.utils.enums;

public enum StateEnum {
	ACTIVE(1, true), INACTIVE(0, false);

	private Integer state;
	private Boolean condition;

	private StateEnum(Integer state, Boolean condition) {
		this.state = state;
		this.condition = condition;
	}

	public Integer getState() {
		return state;
	}

	public Boolean getCondition() {
		return condition;
	}

}
