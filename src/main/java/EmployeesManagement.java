import com.mysql.cj.jdbc.MysqlDataSource;

import java.sql.*;
import java.time.LocalDate;
import java.time.Month;
import org.example.employeeManagement.model.EmployeeManagement;
import org.example.employeeManagement.repository.EmployeeManagementRepository;

public class EmployeesManagement {
    public static void main(String[] args) {
//         testConnectionA();
//         testConnectionB();
//         testGetAll();
//         testGetById();
//         testInsert();
//         testInsert2();
        testUpdate();


//        //Drop table
//        drop();
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

        employeeManagement.setEmployeeId(4);
        employeeManagement.setFirstName("Daniel");
        employeeManagement.setLastName("Unknown");
        employeeManagement.setEmail("daniel.unknown123@gmail.com");
        employeeManagement.setPhoneNumber(866788);
        employeeManagement.setHireDate(LocalDate.of(2002, Month.DECEMBER, 1));
        employeeManagement.setJobTittle("Java Programmer");
        employeeManagement.setSalary(10000.00);

        return employeeManagement;
    }

    public static void testGetAll()  {
        EmployeeManagementRepository employeeManagementRepository = new EmployeeManagementRepository();
        employeeManagementRepository.getAll();

    }

    public static void testGetById() {
        EmployeeManagementRepository repository = new EmployeeManagementRepository();
        int idToRetrieve = 1;
        repository.getById(idToRetrieve);
    }

    public static void testInsert() {
        EmployeeManagementRepository repository = new EmployeeManagementRepository();
        repository.insert();
    }

    public static void testInsert2() {
        EmployeeManagement employeeManagement = createEmployeeManagement();
        EmployeeManagementRepository repository = new EmployeeManagementRepository();
        repository.insert(employeeManagement);
    }

    public static void testUpdate() {
        EmployeeManagement employeeManagement = createEmployeeManagement();
        EmployeeManagementRepository repository = new EmployeeManagementRepository();
        repository.update(employeeManagement);
    }
}