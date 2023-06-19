package np.com.thapanarayan.sqlinjectiondemo;

import lombok.RequiredArgsConstructor;
import np.com.thapanarayan.sqlinjectiondemo.dao.EmployeeDao;
import np.com.thapanarayan.sqlinjectiondemo.dao.ProductDao;
import np.com.thapanarayan.sqlinjectiondemo.model.Employee;
import np.com.thapanarayan.sqlinjectiondemo.model.Product;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class SqlInjectionDemoApplication implements CommandLineRunner {

    private final EmployeeDao employeeDao;
    private final ProductDao productDao;

    public static void main(String[] args) {
        SpringApplication.run(SqlInjectionDemoApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(new Employee("Ram", "Manager", "100000"));
        employeeList.add(new Employee("Hari", "Senior Sales Executive", "100000"));
        employeeList.add(new Employee("Shyam", "Sale Assistant", "100000"));
        employeeList.add(new Employee("GhanShyam", "Sale Assistant", "100000"));
        employeeList.add(new Employee("Ravi", "Shift Manager", "100000"));
        employeeList.add(new Employee("Geeta", "Sale Assistant", "100000"));
        employeeList.add(new Employee("Sita", "Sale Assistant", "100000"));

        employeeList.forEach(employeeDao::save);

        List<Product> productList = new ArrayList<>();
        productList.add(new Product("Laptop", "100000", "Ram"));
        productList.add(new Product("Monitor", "30000", "Ram"));
        productList.add(new Product("Mouse", "2000", "Hari"));
        productList.add(new Product("USB", "10000", "Hari"));
        productList.forEach(productDao::save);

    }
}
