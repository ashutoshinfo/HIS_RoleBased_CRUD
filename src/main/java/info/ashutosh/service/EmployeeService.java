package info.ashutosh.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import info.ashutosh.domain.entity.employee.Employee;
import info.ashutosh.domain.json.employee.EmployeeRequest;
import info.ashutosh.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public Employee saveEmployee(EmployeeRequest requestEmployee) {
		Employee employee = convertToEmployee(requestEmployee);
		return employeeRepository.save(employee);
	}

	private Employee convertToEmployee(EmployeeRequest requestEmployee) {
		Employee employee = new Employee();
		employee.setName(requestEmployee.getName());
		employee.setEmail(requestEmployee.getEmail());
		employee.setMobile(requestEmployee.getMobile());
		employee.setPassword(requestEmployee.getPassword());
		employee.setWorkingType(requestEmployee.getWorkingType());
		employee.setStatus(requestEmployee.getStatus());

		return employee;
	}

}
