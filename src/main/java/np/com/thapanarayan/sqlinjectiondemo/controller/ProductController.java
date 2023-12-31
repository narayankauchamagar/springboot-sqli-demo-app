package np.com.thapanarayan.sqlinjectiondemo.controller;

import io.swagger.v3.oas.annotations.Hidden;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import np.com.thapanarayan.sqlinjectiondemo.dao.EmployeeDao;
import np.com.thapanarayan.sqlinjectiondemo.dao.ProductDao;
import np.com.thapanarayan.sqlinjectiondemo.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/product")
@RestController
public class ProductController {

    private final ProductDao productDao;

    @Hidden
    @GetMapping()
    public ResponseEntity<?> getAllProducts() {
        return ResponseEntity.ok(productDao.getAllProducts());
    }

    @Hidden
    @PostMapping("employee/{name}")
    public ResponseEntity<?> getEmployeeByPost(@PathVariable("name") String name) {
        log.info("Payload : " + name );
        return ResponseEntity.ok(productDao.getProductByEmployee(name));
    }

}
