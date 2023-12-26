package org.example.employeeManagement.repository;

import org.example.employeeManagement.model.EmployeeManagement;

import java.sql.*;
import java.time.LocalDate;

public class EmployeeManagementRepository {

    public void getAll() {
        String connectionURL = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        String query = "SELECT * FROM employee";

        try(final Connection connection = DriverManager.getConnection(connectionURL,username,password);
            PreparedStatement preparedStatement = connection.prepareStatement(query)) {

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

    public void insert() {
        String connectionURL = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        String query =  "INSERT INTO employee(employee_id, first_name, last_name, email, phone_number , hire_date, job_title , salary)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)"; //placeholders

        try (final Connection connection = DriverManager.getConnection(connectionURL, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setInt(1,5);
            preparedStatement.setString(2, "Noar");
            preparedStatement.setString(3, "Noar");
            preparedStatement.setString(4, "noar@email.com");
            preparedStatement.setInt(5, 334455);
            preparedStatement.setDate(6, Date.valueOf(LocalDate.of(1998, 07,22)));
            preparedStatement.setString(7, "Software Tester");
            preparedStatement.setDouble(8, 30000.4);

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Employee inserted successfully!");
            } else {
                System.out.println("Failed to insert employee.");
            }

        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

    public void insert(EmployeeManagement employeeManagement) {
        String connectionURL = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        String insertQuery = "INSERT INTO employee(employee_id, first_name, last_name, email, phone_number , hire_date, job_title , salary)"
                + " VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

        try (final Connection connection = DriverManager.getConnection(connectionURL, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(insertQuery)) {

            preparedStatement.setInt(1,employeeManagement.getEmployeeId());
            preparedStatement.setString(2, employeeManagement.getFirstName());
            preparedStatement.setString(3, employeeManagement.getLastName());
            preparedStatement.setString(4, employeeManagement.getEmail());
            preparedStatement.setInt(5, employeeManagement.getPhoneNumber());
            LocalDate hireDate = employeeManagement.getHireDate();
            preparedStatement.setDate(6, Date.valueOf(hireDate));
            preparedStatement.setString(7, employeeManagement.getJobTittle());
            preparedStatement.setDouble(8, employeeManagement.getSalary());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Employee inserted successfully!");
            } else {
                System.out.println("Failed to insert employee.");
            }

        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }

    public void update(EmployeeManagement employeeManagement) {
        String connectionURL = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        String updateQuery = "UPDATE employee " +
                "SET first_name = ?, last_name = ?, email = ?, phone_number = ?, hire_date = ?, job_title = ?, salary = ? " +
                "WHERE employee_id = ?";
        try (Connection connection = DriverManager.getConnection(connectionURL, username, password);
             PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {

            preparedStatement.setString(1, employeeManagement.getFirstName());
            preparedStatement.setString(2, employeeManagement.getLastName());
            preparedStatement.setString(3, employeeManagement.getEmail());
            preparedStatement.setInt(4, employeeManagement.getPhoneNumber());
            preparedStatement.setDate(5, Date.valueOf(employeeManagement.getHireDate()));
            preparedStatement.setString(6, employeeManagement.getJobTittle());
            preparedStatement.setDouble(7, employeeManagement.getSalary());
            preparedStatement.setInt(8, employeeManagement.getEmployeeId());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row(s) updated.");

        }catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void delete(EmployeeManagement employeeManagement) {
        String connectionURL = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        String deleteQuery = "DELETE FROM employee " +
                "WHERE employee_id = ?";

        try(Connection connection = DriverManager.getConnection(connectionURL, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {

            preparedStatement.setInt(1, employeeManagement.getEmployeeId());

            int rowsAffected = preparedStatement.executeUpdate();
            System.out.println(rowsAffected + " row updated.");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
