package np.com.thapanarayan.sqlinjectiondemo.dao;


import np.com.thapanarayan.sqlinjectiondemo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class ProductDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    public Product save(Product product){
        String query = "insert into product (name, price, created_by) values(:name, :price, :createdBy)";
        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("name", product.getName());
        params.addValue("price", product.getPrice());
        params.addValue("createdBy", product.getCreatedBy());

        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(query, params, keyHolder);
        product.setId(keyHolder.getKey().longValue());
        return product;
    }

    public List<Map<String, Object>> getAllProducts() {
        String query = "SELECT id, name, price, created_by FROM product";
        return jdbcTemplate.queryForList(query, new HashMap<>());
    }

    public List<Map<String, Object>> getProductByEmployee(String employee) {
        String query = "SELECT id, name, price, created_by FROM product where created_by = '" + employee + "'";
        return jdbcTemplate.queryForList(query, new HashMap<>());
    }
}
