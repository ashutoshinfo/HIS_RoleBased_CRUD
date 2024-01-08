package info.ashutosh.configuration.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import info.ashutosh.domain.entity.employee.Employee;
import info.ashutosh.repository.user.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository reposetory;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Optional<Employee> findByEmail = reposetory.findByEmail(email);

		findByEmail.orElseThrow(() -> new UsernameNotFoundException("User not Found"));

		return new Employee(findByEmail.get());
	}

}
