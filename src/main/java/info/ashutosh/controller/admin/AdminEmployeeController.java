package info.ashutosh.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import info.ashutosh.domain.entity.employee.Employee;
import info.ashutosh.domain.json.employee.EmployeeRequest;
import info.ashutosh.service.EmployeeService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/admin/employees")
public class AdminEmployeeController {

	@Autowired
	EmployeeService employeeService;

	@PostMapping
	public ResponseEntity<Employee> saveEmployee(@RequestBody @Valid EmployeeRequest employeeRequest) {
		Employee savedEmployee = employeeService.saveEmployee(employeeRequest);
		return new ResponseEntity<>(savedEmployee, HttpStatus.CREATED);
	}

	@PutMapping("/{employeeId}")
	public ResponseEntity<Employee> updateEmployee(@PathVariable Long employeeId,
			@RequestBody @Valid Employee updatedEmployee) {
		return null;
		// Implementation to update employee details
	}

}
