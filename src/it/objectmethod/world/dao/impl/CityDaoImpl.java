package it.objectmethod.world.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import it.objectmethod.world.dao.ICityDao;
import it.objectmethod.world.model.City;

public class CityDaoImpl extends NamedParameterJdbcDaoSupport implements ICityDao {
	
	public List<City> getCityListByCountryCode(String countryCode) {
		List<City> cityList = null;
		String sql = "SELECT id id, name name, population population, countrycode countrycode FROM city WHERE countrycode = :code";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("code", countryCode);
		BeanPropertyRowMapper<City> rm = new BeanPropertyRowMapper<City>(City.class);
		cityList = getNamedParameterJdbcTemplate().query(sql, params, rm);
		return cityList;
	}
	
	public List<City> searchCities(String searchStr, String countryCode) {
		List<City> cityList = null;
		String sql = "SELECT id id, name name, population population, countrycode countrycode FROM city WHERE name = :searchStr: AND ('' = :countryCode OR countrycode = :countryCode)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("searchStr", searchStr);
		params.addValue("countryCode", countryCode);
		BeanPropertyRowMapper<City> rm = new BeanPropertyRowMapper<City>(City.class);
		cityList = getNamedParameterJdbcTemplate().query(sql, params, rm);
		return cityList;
	}
	
	public void insertCity(City city) {
		String sql = "INSERT INTO city (name, countrycode, population) VALUES (:name, :countryCode, :population)";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", city.getName());
		params.addValue("countryCode", city.getCountrycode());
		params.addValue("population", city.getPopulation());
		getNamedParameterJdbcTemplate().update(sql, params);
	}
	
	public void modifyCity(City city) {
		String sql = "UPDATE city SET name = :name:, countrycode = :countryCode, population = :population WHERE id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", city.getName());
		params.addValue("countryCode", city.getCountrycode());
		params.addValue("population", city.getPopulation());
		params.addValue("id", city.getId());
		getNamedParameterJdbcTemplate().update(sql, params);
	}
	
	public City getCityById(int id) {
		City city = null;
		String sql = "SELECT id, name, population, countrycode FROM city WHERE id = :id";
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);
		BeanPropertyRowMapper<City> rm = new BeanPropertyRowMapper<City>(City.class);
		city = getNamedParameterJdbcTemplate().queryForObject(sql, params, rm);
		return city;
	}

}
