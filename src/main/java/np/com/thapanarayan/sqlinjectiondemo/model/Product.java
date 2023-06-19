package np.com.thapanarayan.sqlinjectiondemo.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String price;
    private String createdBy;

    public Product(String name, String price, String createdBy) {
        this.name = name;
        this.price = price;
        this.createdBy = createdBy;
    }
}
