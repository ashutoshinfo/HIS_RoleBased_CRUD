package info.ashutosh.domain.entity.user.authentication;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {

	private String token;

	public AuthResponse(String token) {
		super();
		this.token = token;
	}

}