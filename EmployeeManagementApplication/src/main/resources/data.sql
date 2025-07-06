-- Create Tables

-- Create departments table
CREATE TABLE departments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    budget DECIMAL(12,2) NOT NULL
);

-- Create employees table
CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    date_of_joining DATE NOT NULL,
    salary DECIMAL(10,2) NOT NULL,
    department_id BIGINT NOT NULL,
    manager_id BIGINT,
    FOREIGN KEY (department_id) REFERENCES departments(id),
    FOREIGN KEY (manager_id) REFERENCES employees(id)
);

-- Create projects table
CREATE TABLE projects (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE,
    department_id BIGINT NOT NULL,
    FOREIGN KEY (department_id) REFERENCES departments(id)
);

-- Create employee_projects table
CREATE TABLE employee_projects (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    project_id BIGINT NOT NULL,
    assigned_date DATE NOT NULL,
    role VARCHAR(255) NOT NULL,
    FOREIGN KEY (employee_id) REFERENCES employees(id),
    FOREIGN KEY (project_id) REFERENCES projects(id)
);

-- Create performance_reviews table
CREATE TABLE performance_reviews (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    employee_id BIGINT NOT NULL,
    review_date DATE NOT NULL,
    score DECIMAL(3,1) NOT NULL,
    review_comments TEXT,
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

-- Insert Sample Data

-- Insert Departments
INSERT INTO departments (name, budget) VALUES
('Engineering', 1000000.00),
('Marketing', 500000.00),
('Sales', 750000.00),
('HR', 300000.00);

-- Insert Employees
INSERT INTO employees (name, email, department_id, date_of_joining, salary, manager_id) VALUES
('John Doe', 'john.doe@company.com', 1, '2020-01-15', 90000.00, NULL),
('Jane Smith', 'jane.smith@company.com', 1, '2020-03-20', 85000.00, 1),
('Bob Johnson', 'bob.johnson@company.com', 1, '2021-05-10', 75000.00, 1),
('Alice Brown', 'alice.brown@company.com', 2, '2020-07-12', 70000.00, NULL),
('Charlie Wilson', 'charlie.wilson@company.com', 2, '2021-09-05', 65000.00, 4),
('Diana Davis', 'diana.davis@company.com', 3, '2019-11-18', 80000.00, NULL),
('Eve Miller', 'eve.miller@company.com', 3, '2022-01-22', 72000.00, 6),
('Frank Garcia', 'frank.garcia@company.com', 4, '2020-04-30', 68000.00, NULL);

-- Insert Projects
INSERT INTO projects (name, start_date, end_date, department_id) VALUES
('Project Alpha', '2023-01-01', '2023-12-31', 1),
('Project Beta', '2023-03-01', '2023-09-30', 1),
('Marketing Campaign 2023', '2023-02-01', '2023-11-30', 2),
('Sales Expansion', '2023-04-01', NULL, 3),
('HR Digitization', '2023-01-15', '2023-08-15', 4);

-- Insert Employee-Project relationships
INSERT INTO employee_projects (employee_id, project_id, assigned_date, role) VALUES
(1, 1, '2023-01-01', 'Tech Lead'),
(2, 1, '2023-01-01', 'Senior Developer'),
(3, 1, '2023-01-01', 'Developer'),
(2, 2, '2023-03-01', 'Project Manager'),
(3, 2, '2023-03-01', 'Developer'),
(4, 3, '2023-02-01', 'Marketing Manager'),
(5, 3, '2023-02-01', 'Marketing Specialist'),
(6, 4, '2023-04-01', 'Sales Manager'),
(7, 4, '2023-04-01', 'Sales Representative'),
(8, 5, '2023-01-15', 'HR Manager');

-- Insert Performance Reviews
INSERT INTO performance_reviews (employee_id, review_date, score, review_comments) VALUES
(1, '2023-01-31', 9.5, 'Excellent leadership and technical skills'),
(1, '2023-06-30', 9.2, 'Consistent high performance'),
(1, '2023-12-31', 9.8, 'Outstanding year-end performance'),
(2, '2023-01-31', 8.5, 'Good technical skills, needs improvement in communication'),
(2, '2023-06-30', 8.8, 'Improved communication skills'),
(2, '2023-12-31', 9.0, 'Strong performance as project manager'),
(3, '2023-02-28', 7.5, 'Solid developer, room for growth'),
(3, '2023-07-31', 8.0, 'Showing improvement in coding practices'),
(3, '2023-12-31', 8.2, 'Good progress throughout the year'),
(4, '2023-02-28', 9.0, 'Excellent marketing strategy development'),
(4, '2023-07-31', 8.8, 'Strong campaign execution'),
(4, '2023-12-31', 9.2, 'Outstanding marketing leadership'),
(5, '2023-03-31', 7.8, 'Good marketing support, needs more initiative'),
(5, '2023-08-31', 8.1, 'Increased proactivity in campaigns'),
(5, '2023-12-31', 8.3, 'Solid marketing specialist performance'),
(6, '2023-03-31', 8.7, 'Strong sales management skills'),
(6, '2023-08-31', 9.1, 'Exceeded sales targets'),
(6, '2023-12-31', 9.3, 'Exceptional sales leadership'),
(7, '2023-04-30', 8.2, 'Good sales performance, building client relationships'),
(7, '2023-09-30', 8.5, 'Improved client retention rates'),
(7, '2023-12-31', 8.7, 'Strong sales results'),
(8, '2023-02-28', 8.9, 'Excellent HR process improvements'),
(8, '2023-07-31', 9.0, 'Strong employee satisfaction initiatives'),
(8, '2023-12-31', 9.2, 'Outstanding HR leadership');