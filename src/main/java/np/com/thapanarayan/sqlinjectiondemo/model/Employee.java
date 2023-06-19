package np.com.thapanarayan.sqlinjectiondemo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "employee")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String post;
    private String salary;

    public Employee(String name, String post, String salary) {
        this.name = name;
        this.post = post;
        this.salary = salary;
    }
}
