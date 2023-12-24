import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import org.example.employeeManagement.model.EmployeeManagement;
import org.example.employeeManagement.repository.EmployeeManagementRepository;

public class EmployeesManagement {
    public static void main(String[] args) {
         testConnectionA();
         testConnectionB();
         testGetAll();
         testGetById();


//        //Connecting to the same database using an implementation of the DataSource interface:
//        MysqlDataSource dataSource = new MysqlDataSource();
//        dataSource.setUrl(System.getenv("DB_URL"));
//        dataSource.setUser(System.getenv("DB_USER"));
//        dataSource.setPassword(System.getenv("DB_PASSWORD"));
//
//        try (Connection connection = dataSource.getConnection()) {
//            PreparedStatement statement = connection.prepareStatement("SELECT * FROM employee WHERE first_name = ? OR employee_id = ?");
//
//            int rowCount = statement.executeUpdate("INSERT INTO  employee VALUES (4, 'Daniel', 'daniel', 'daniel@gmail.com',2345,'2002-01-02','Java Programmer',700)");
//            statement.setString(1, "Leo");
//            statement.setInt(2, 2);
//
//
//            final ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                System.out.println("First name is " + resultSet.getString(2));
//            }
//
//        } catch (SQLException exp) {
//            exp.printStackTrace();
//        }
//
//
//        //Drop table
//        drop();
//    }
//
//    private static void drop() {
//        String connectionUrl = System.getenv("DB_URL");
//        String username = System.getenv("DB_USER");
//        String password = System.getenv("DB_PASSWORD");
//
//        try (Connection connection = DriverManager.getConnection(connectionUrl, username, password);
//             Statement dropStatement = connection.createStatement()) {
//            dropStatement.execute("DROP TABLE IF EXISTS departments");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
   }

    //Establishing a connection to the MySQL database using the DriverManager class:
    public static void testConnectionA() {

        String connectionUrl = System.getenv("DB_URL");
        String username = System.getenv("DB_USER");
        String password = System.getenv("DB_PASSWORD");

        try(final Connection connection = DriverManager.getConnection(connectionUrl, username,password)){

            if (connection.isValid(0)) {
                System.out.println("Connection successful");
            }

        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }


    //Connecting to the same database using an implementation of the DataSource interface:
    public static void testConnectionB() {

        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl(System.getenv("DB_URL"));
        dataSource.setUser(System.getenv("DB_USER"));
        dataSource.setPassword(System.getenv("DB_PASSWORD"));

        try (Connection connection = dataSource.getConnection()) {

            if (connection.isValid(0)) {
                System.out.println("Connection successful");
            }

         } catch (SQLException exp) {
             exp.printStackTrace();
        }
    }

    public static EmployeeManagement createEmployeeManagement() {

        EmployeeManagement employeeManagement = new EmployeeManagement();

        employeeManagement.setEmployeeId(1);
        employeeManagement.setFirstName("Ana");
        employeeManagement.setLastName("anna");
        employeeManagement.setEmail("ana@gmail.com");
        employeeManagement.setPhoneNumber(866788);
        employeeManagement.setHireDate(LocalDate.of(2020, Month.DECEMBER, 14));
        employeeManagement.setJobTittle("Java Programmer");
        employeeManagement.setSalary(800.00);

        return employeeManagement;
    }

    public static void testGetAll()  {
        EmployeeManagementRepository employeeManagementRepository = new EmployeeManagementRepository();
        try {
            employeeManagementRepository.getAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void testGetById() {
        EmployeeManagementRepository repository = new EmployeeManagementRepository();
        int idToRetrieve = 1;
        repository.getById(idToRetrieve);
    }
}