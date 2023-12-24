package org.example.employeeManagement.repository;

import org.example.employeeManagement.model.EmployeeManagement;

import java.sql.*;

public class EmployeeManagementRepository {

    public void getAll() throws SQLException {

        String connectionURL = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        String query = "SELECT * FROM employee";

        try(final Connection connection = DriverManager.getConnection(connectionURL,username,password)) {

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Employee Id: " + resultSet.getInt("employee_id"));
                System.out.println("First Name: " + resultSet.getString("first_name"));
                System.out.println("Last Name: " + resultSet.getString("last_name"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Phone Number: " + resultSet.getInt("phone_number"));
                System.out.println("Hire Date: " + resultSet.getDate("hire_date"));
                System.out.println("Job Title: " + resultSet.getString("job_title"));
                System.out.println("Salary: " + resultSet.getDouble("salary"));
                System.out.println("-----------------------------------------");
            }
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

    public void getById(int employeeId) {
        String connectionURL = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        String query = "SELECT * FROM employee WHERE employee_id = ?";

        try (final Connection connection = DriverManager.getConnection(connectionURL, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1, employeeId);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    System.out.println("Employee Id: " + resultSet.getInt("employee_id"));
                    System.out.println("First Name: " + resultSet.getString("first_name"));
                    System.out.println("Last Name: " + resultSet.getString("last_name"));
                    System.out.println("Salary: " + resultSet.getDouble("salary"));
                    System.out.println("-----------------------------------------");
                } else {
                    System.out.println("Employee with ID " + employeeId + " not found.");
                }
            }
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

}
