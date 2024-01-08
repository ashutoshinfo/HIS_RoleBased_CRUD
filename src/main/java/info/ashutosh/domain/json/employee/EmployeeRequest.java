package info.ashutosh.domain.json.employee;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeRequest {

	@NotBlank(message = "Name cannot be blank")
	@Size(max = 255, message = "Name cannot exceed 255 characters")
	private String name;

	@Email(message = "Invalid email address")
	@NotBlank(message = "Email cannot be blank")
	private String email;

	@NotBlank(message = "Mobile cannot be blank")
	@Pattern(regexp = "^[0-9]{10}$", message = "Mobile must be a 10-digit number")
	private String mobile;

	@NotBlank(message = "Password cannot be blank")
	@Size(min = 8, message = "Password must be at least 8 characters long")
	private String password;

	@NotBlank(message = "Working type cannot be blank")
	@Pattern(regexp = "^(WFH|WFO)$", message = "Working type must be WFH or WFO")
	private String workingType;

	@NotBlank(message = "Status cannot be blank")
	@Pattern(regexp = "^(Active|Inactive)$", message = "Status must be Active or Inactive")
	private String status;

}
