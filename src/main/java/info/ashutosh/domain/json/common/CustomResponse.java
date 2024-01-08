package info.ashutosh.domain.json.common;


import info.ashutosh.domain.enums.ResponseStatus;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class CustomResponse {

	private final int status;

	private final Object payload;

	private final String message;

	public static CustomResponse prepareResponse(ResponseStatus status, Object payload, String message) {
		return new CustomResponse(status.getCode(), payload, status.getMessage() + " : " + message);
	}
}
