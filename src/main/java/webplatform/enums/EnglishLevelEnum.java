package webplatform.enums;

public enum EnglishLevelEnum {

	BASIC("Basic"), INTERMEDIATE("Intermediate"), ADVANCED("Advanced");

	private String value;

	private EnglishLevelEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

}
