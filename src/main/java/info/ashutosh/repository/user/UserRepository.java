package info.ashutosh.repository.user;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import info.ashutosh.domain.entity.employee.Employee;

public interface UserRepository extends JpaRepository<Employee, Long> {

	Optional<Employee> findByEmail(String email);
}
