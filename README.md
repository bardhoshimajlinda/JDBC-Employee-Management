# JDBC Database Connection

JDBC (Java Database Connectivity) provides two approaches for connecting to a database:

## 1. Using `DriverManager`

We can connect to a database using the `DriverManager` class, which offers a static `getConnection` method. This method allows to establish a connection by specifying the database URL, username, and password.

## 2. Using `DataSource`

Alternatively, we can create an implementation of the `DataSource` interface. This method provides a more flexible and efficient way to manage database connections. We configure a `DataSource` instance with the required connection details and then call the `getConnection` method on the instance to obtain a connection.
