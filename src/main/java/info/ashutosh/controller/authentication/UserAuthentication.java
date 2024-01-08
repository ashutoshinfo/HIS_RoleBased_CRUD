package info.ashutosh.controller.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.ashutosh.configuration.security.JwtTokenUtil;
import info.ashutosh.domain.entity.user.authentication.AuthRequest;
import info.ashutosh.domain.entity.user.authentication.AuthResponse;
import info.ashutosh.domain.enums.ResponseStatus;
import info.ashutosh.domain.json.common.CustomResponse;

@RestController
@RequestMapping("/authenticate")
public class UserAuthentication {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@PostMapping
	public CustomResponse authenticate(@RequestBody AuthRequest authRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		String jwt = jwtTokenUtil.generateToken(userDetails);

//		return ResponseEntity.ok(new AuthResponse(jwt));
		return CustomResponse.prepareResponse(ResponseStatus.SUCCESS, new AuthResponse(jwt),"Token Generated Successfully");
		
	}

}
