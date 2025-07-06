# Employee Management System

A comprehensive Spring Boot application for managing employees, departments, projects, and performance reviews in an organization.

## 🛠 Technology Stack

- **Backend**: Spring Boot 3.x
- **Database**: H2 (In-memory for development)
- **ORM**: Spring Data JPA
- **Validation**: Jakarta Bean Validation
- **Build Tool**: Maven
- **Java Version**: 17+
- **Additional Libraries**:
  - Lombok (Code generation)
  - Jakarta Persistence API
  - H2 Database Engine

## 🗄 Database Schema

The application uses five main entities:

### Core Entities

1. **Department** - Organizational departments with budget information
2. **Employee** - Employee records with personal and professional details
3. **Project** - Project information linked to departments
4. **EmployeeProject** - Many-to-many relationship between employees and projects
5. **PerformanceReview** - Employee performance tracking

### Entity Relationships

```
Department (1) -----> (N) Employee
Department (1) -----> (N) Project
Employee (1) -------> (N) EmployeeProject
Project (1) --------> (N) EmployeeProject
Employee (1) -------> (N) PerformanceReview
Employee (1) -------> (N) Employee (Manager-Subordinate)
```

## 🚀 Getting Started

### Prerequisites

- Java 17 or higher
- Maven 3.6+
- IDE (IntelliJ IDEA, Eclipse, VS Code)

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd employee-management-system
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the application**
   - Application: `http://localhost:8080`
   - H2 Console: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:testdb`
     - Username: `sa`
     - Password: (leave empty)

## 📊 Sample Data

The application comes with pre-loaded sample data including:

- **4 Departments**: Engineering, Marketing, Sales, HR
- **8 Employees**: Including managers and subordinates
- **5 Projects**: Various projects across different departments
- **23 Performance Reviews**: Historical performance data
- **Employee-Project Assignments**: Role-based project assignments

### Sample Department Data
| Department | Budget |
|------------|---------|
| Engineering | $1,000,000 |
| Marketing | $500,000 |
| Sales | $750,000 |
| HR | $300,000 |

### Sample Employee Data
| Name | Department | Role | Salary |
|------|------------|------|---------|
| John Doe | Engineering | Tech Lead | $90,000 |
| Jane Smith | Engineering | Senior Developer | $85,000 |
| Alice Brown | Marketing | Marketing Manager | $70,000 |
| Diana Davis | Sales | Sales Manager | $80,000 |

## ⚙️ Configuration

### Database Configuration

The application uses H2 in-memory database by default. Configuration in `application.properties`:

```properties
# H2 Database Configuration
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

# JPA Configuration
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true

# H2 Console
spring.h2.console.enabled=true
spring.h2.console.path=/h2-console

# Initialize database
spring.sql.init.mode=always
```

### Switching to Production Database

For production use, replace H2 with PostgreSQL or MySQL:

```properties
# PostgreSQL Example
spring.datasource.url=jdbc:postgresql://localhost:5432/employee_management
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
```

## 🏗 Project Structure

```
src/
├── main/
│   ├── java/
│   │   └── com/example/employeemanagement/
│   │       └── EmployeeManagementApplication/
│   │           ├── entity/
│   │           │   ├── Department.java
│   │           │   ├── Employee.java
│   │           │   ├── Project.java
│   │           │   ├── EmployeeProject.java
│   │           │   └── PerformanceReview.java
│   │           ├── repository/
│   │           ├── service/
│   │           ├── controller/
│   │           └── dto/
│   └── resources/
│       ├── application.properties
│       └── data.sql
```


## 🧪 Testing

Run the tests using:

```bash
mvn test
```

## 📝 API Endpoints (To be implemented)

Future API endpoints will include:

- `GET /api/employees` - Get all employees
- `GET /api/employees/{id}` - Get an employee
- You can play with these reviewDate, minScore, maxScore, departmentNames, projectNames for the first get api call

## 🤝 Contributing

1. Fork the repository
2. Create your feature branch (`git checkout -b feature/AmazingFeature`)
3. Commit your changes (`git commit -m 'Add some AmazingFeature'`)
4. Push to the branch (`git push origin feature/AmazingFeature`)
5. Open a Pull Request

## 🆘 Support

For support and questions:
- Create an issue in the repository
- Check the documentation
- Review the sample data and configurations

## 🔮 Future Enhancements

- [ ] REST API implementation
- [ ] Authentication and authorization
- [ ] File upload for employee photos
- [ ] Email notifications for performance reviews
- [ ] Dashboard with analytics
- [ ] Export functionality (PDF, Excel)
- [ ] Role-based access control
- [ ] Audit logging
- [ ] Integration with external HR systems

---

**Happy Coding!** 🚀
