package info.ashutosh.domain.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {
	
	NOT_ALLOWED(-5, "Not Allowed"),
	ALREADY_EXIST(-4, "Already Exist"), 
	ERROR(-3, "Error"),
	BAD_CREDENTIAL(-2, "Bad Credential"),
	NOT_EXIST(-1, "Not Exist"), 
	SUCCESS(0, "Success");

	private final int code;
	private final String message;

}
