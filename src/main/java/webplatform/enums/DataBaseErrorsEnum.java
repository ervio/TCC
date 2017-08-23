package webplatform.enums;

public enum DataBaseErrorsEnum {

	EMAIL_PROFESSOR_TRIGGER("ORA-20001: Already exist in ALUNO"), //
	EMAIL_ALUNO_TRIGGER("ORA-20002: Already exist in PROFESSOR");

	private String value;

	private DataBaseErrorsEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
