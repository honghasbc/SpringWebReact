package com.heb.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.heb.model.User;

@Repository
public class UserDaoImpl implements UserDao {

	NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired
	public void setNamedParameterJdbcTemplate(NamedParameterJdbcTemplate namedParameterJdbcTemplate2) {
		this.namedParameterJdbcTemplate = namedParameterJdbcTemplate2;
	}
	
	@Override
	public User findByName(String username) {
		
		Map<String, Object> params = new HashMap<String, Object>();
        params.put("username", username);
        
		String sql = "SELECT * FROM users WHERE username=:username";
		
        User result = namedParameterJdbcTemplate.queryForObject(
                    sql,
                    params,
                    new UserMapper());
                    
        //new BeanPropertyRowMapper(Customer.class));
        
        return result;
        
	}

	@Override
	public List<User> findAll() {
		
		Map<String, Object> params = new HashMap<String, Object>();
		
		String sql = "SELECT * FROM users";
		
        List<User> result = namedParameterJdbcTemplate.query(sql, params, new UserMapper());
        
        return result;
        
	}

	private static final class UserMapper implements RowMapper<User> {

		public User mapRow(ResultSet rs, int rowNum) throws SQLException {
			User user = new User();
			user.setUsername(rs.getString("username"));
			user.setPassword(rs.getString("password"));
			user.setEnabled(rs.getInt("enabled"));
			return user;
		}
	}

}