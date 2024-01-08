package info.ashutosh.configuration.security;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

	@Value("${jwt.secret}")
	private String jwtSecret;

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		final String requestTokenHeader = request.getHeader("Authorization");

		String username = null;
		String jwtToken = null;

		// JWT Token is in the form "Bearer token". Remove Bearer word and get only the
		// Token
		if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
			jwtToken = requestTokenHeader.substring(7);
			try {
				String encodeSecretKey = encodeSecretKey(jwtSecret);
//				username = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(jwtToken).getBody().getSubject();
				username = Jwts.parserBuilder().setSigningKey(encodeSecretKey).build().parseClaimsJws(jwtToken).getBody().getSubject();
				System.out.println(username);
			} catch (ExpiredJwtException e) {
				// Handle expired token
			} catch (Exception e) {
				// Handle other token validation errors
			}
		}

		// Once we get the token, validate it and load user details
		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(username);

			// If token is valid, configure Spring Security to manually set authentication
			if (jwtToken != null && jwtTokenIsValid(jwtToken, userDetails)) {
				UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

				SecurityContextHolder.getContext().setAuthentication(authenticationToken);
			}
		}
		chain.doFilter(request, response);
	}

	private boolean jwtTokenIsValid(String token, UserDetails userDetails) {
		// Validate token expiration, signature, or any additional validation logic
		// Return true if the token is valid, otherwise false
		// You may use a library like jjwt or Nimbus JOSE + JWT for handling JWT
		// validation
		// Example: return !isTokenExpired(token);
		return new JwtTokenUtil().validateToken(token, userDetails);
	}
	

    private String encodeSecretKey(String originalSecretKey) {
        // Convert the original secret key to bytes
        byte[] decodedKey = originalSecretKey.getBytes();

        // Encode the bytes using Base64
        String encodedKey = Base64.getEncoder().encodeToString(decodedKey);

        return encodedKey;
    }

}
