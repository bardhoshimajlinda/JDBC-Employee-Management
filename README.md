# Employee Management System (JDBC)

This Java application demonstrates basic JDBC interactions for an Employee Management System. It includes functionalities for connecting to a MySQL database, performing CRUD operations, and testing various scenarios.

## Usage

1. **Connection Testing:**
   - `testConnectionA`: Establishes a connection to the MySQL database using DriverManager. We can connect to a database using the `DriverManager` class, which offers a static `getConnection` method. This method allows to establish a connection by specifying the database URL, username, and password.
   - `testConnectionB`: Connects to the database using the `MysqlDataSource` implementation. Alternatively, we can create an implementation of the `DataSource` interface. This method provides a more flexible and efficient way to manage database connections. We configure a `DataSource` instance with the required connection details and then call the `getConnection` method on the instance to obtain a connection.

2. **Data Operations:**
   - `testGetAll`: Retrieves all employee records from the database.
   - `testGetById`: Retrieves an employee by ID.
   - `testInsert`: Inserts a predefined employee record into the database.
   - `testInsert2`: Inserts an employee record using a factory method for testing.
   - `testUpdate`: Updates an employee record.

3. **Testing and Clean-Up:**
   - `testDelete`: Deletes an employee record.

## Configuration

Ensure that your database connection details (URL, username, password) are properly configured as environment variables:
- `DB_URL`: JDBC connection URL.
- `DB_USER`: Database username.
- `DB_PASSWORD`: Database password.
