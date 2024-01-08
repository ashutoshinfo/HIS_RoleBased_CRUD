package info.ashutosh.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import info.ashutosh.domain.entity.employee.Employee;
import info.ashutosh.repository.user.UserRepository;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository; // Replace with your actual user repository

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// Load user details from your data store (e.g., database) based on the provided
		// username
		Employee user = userRepository.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));

		// Create a UserDetails object with the user's details
		return User.builder().username(user.getUsername())
				.password(user.getPassword()) // Make sure to store passwords securely (hashed)
				.roles(user.getRoles().toArray(new String[0])).build();
	}
}
