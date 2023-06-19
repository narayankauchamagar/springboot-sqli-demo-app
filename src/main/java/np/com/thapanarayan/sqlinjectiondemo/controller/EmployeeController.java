package np.com.thapanarayan.sqlinjectiondemo.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import np.com.thapanarayan.sqlinjectiondemo.dao.EmployeeDao;
import np.com.thapanarayan.sqlinjectiondemo.dto.EmployeeDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@Slf4j
@RequiredArgsConstructor
@RequestMapping("/api/employee")
@RestController
public class EmployeeController {

    private final EmployeeDao employeeDao;

    @GetMapping()
    public ResponseEntity<?> getAllEmployee() {
        return ResponseEntity.ok(employeeDao.listEmployee());
    }

    @PostMapping("byPost")
    public ResponseEntity<?> getEmployeeByPost(@RequestBody EmployeeDto employeeDto) {
        log.info("Payload : " + employeeDto );
        return ResponseEntity.ok(employeeDao.listEmployeeByPost(employeeDto.getPost()));
    }
}
