package org.example;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;

public class Main {
    public static void main(String[] args) throws SQLException {

        //Establishing a connection to the MySQL database using the DriverManager class:
        try(final Connection connection = DriverManager.getConnection(System.getenv("DB_URL"), System.getenv("DB_USER"),System.getenv("DB_PASSWORD"))){

            if (connection.isValid(0)) {
                System.out.println("Connection successful");
            }

            Statement statement = connection.createStatement();

            final ResultSet resultSet = statement.executeQuery("SELECT * FROM employee");
            while (resultSet.next()) {
                System.out.println("Employee id: " + resultSet.getInt("employee_id"));
                System.out.println("First name: " + resultSet.getString("first_name"));
                System.out.println("Last name: " + resultSet.getString("last_name"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Phone number: " + resultSet.getInt("phone_number"));
                System.out.println("Hire date: " + resultSet.getDate("hire_date"));
                System.out.println("Job title: " + resultSet.getString("job_title"));
                System.out.println("Salary: " + resultSet.getDouble("salary"));
                System.out.println("-----------------------------------------");
            }
        } catch (SQLException exp) {
            exp.printStackTrace();
        }

        //Connecting to the same database using an implementation of the DataSource interface:
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(System.getenv("DB_URL"));
        dataSource.setUser(System.getenv("DB_USER"));
        dataSource.setPassword(System.getenv("DB_PASSWORD"));

        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE first_name = ? OR employee_id = ?");

            int rowCount = statement.executeUpdate("INSERT INTO  employee VALUES (4, 'Daniel', 'daniel', 'daniel@gmail.com',2345,'2002-01-02','Java Programmer',700)");
            statement.setString(1, "Leo");
            statement.setInt(2, 2);


            final ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println("First name is " + resultSet.getString(2));
            }

        } catch (SQLException exp) {
            exp.printStackTrace();
        }


        //Drop table
        drop();
    }

    private static void drop() {
        String connectionUrl = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try (Connection connection = DriverManager.getConnection(connectionUrl, username, password);
             Statement dropStatement = connection.createStatement()) {
            dropStatement.execute("DROP TABLE IF EXISTS departments");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}