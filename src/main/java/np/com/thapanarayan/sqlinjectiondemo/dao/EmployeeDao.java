package np.com.thapanarayan.sqlinjectiondemo.dao;


import lombok.extern.slf4j.Slf4j;
import np.com.thapanarayan.sqlinjectiondemo.model.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Component
public class EmployeeDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Employee save(Employee employee){
        String query = "insert into employee(name, post, salary) values (:name, :post, :salary)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", employee.getName());
        params.addValue("post", employee.getPost());
        params.addValue("salary", employee.getSalary());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(query, params, keyHolder);
        employee.setId(keyHolder.getKey().longValue());
        return employee;
    }


    public List<Map<String, Object>> listEmployee() {
        String query = "SELECT id, name, post, salary FROM employee";
        return jdbcTemplate.queryForList(query, new HashMap<>());
    }


    public List<Map<String, Object>> listEmployeeByPost(String post) {
        String query = "SELECT id, name, post, salary FROM employee WHERE post = '" + post + "'" ;
        log.info("QUERY : " + query);
        return jdbcTemplate.queryForList(query, new HashMap<>());
    }
}
