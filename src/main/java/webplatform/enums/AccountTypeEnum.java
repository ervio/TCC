package webplatform.enums;

public enum AccountTypeEnum {

	TEACHER("Teacher"), STUDENT("Student");

	private String value;

	private AccountTypeEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
