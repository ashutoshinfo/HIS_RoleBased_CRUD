
-- Create Roles
INSERT INTO role (role) VALUES ('ROLE_EMPLOYEE') ON DUPLICATE KEY UPDATE role = 'ROLE_EMPLOYEE';
INSERT INTO role (role) VALUES ('ROLE_ADMIN') ON DUPLICATE KEY UPDATE role = 'ROLE_ADMIN';

-- *************************
-- USER DATA
-- *************************
-- Insert sample employee
INSERT INTO employees (name, photo, mobile, working_type, status, email, password)
VALUES ('Ashutosh', 'profile1.jpg', '1234567892', 'WFH', 'Active', 'admin1.admin@ashutosh.info', 'admin1');
INSERT INTO employees (name, photo, mobile, working_type, status, email, password)
VALUES ('Jayesh', 'profile2.jpg', '1234567893', 'WFO', 'Active', 'admin2.admin@ashutosh.info', 'admin2');

-- *************************
-- ROLE Assigning
-- *************************
--  assign role to user

-- assinging role to admin (Both: Admin)
INSERT INTO employee_roles (employee_f_id, role_f_id) VALUES (1, 2);
-- assinging role to Employee (Both: Employee)
INSERT INTO employee_roles (employee_f_id, role_f_id) VALUES (2, 1);
