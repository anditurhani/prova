package it.objectmethod.world.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcDaoSupport;

import it.objectmethod.world.dao.ICountryDao;
import it.objectmethod.world.model.Continent;
import it.objectmethod.world.model.Country;

public class CountryDaoImpl extends NamedParameterJdbcDaoSupport implements ICountryDao {
	
	public List<Country> getCountryListByContinent(String continent) {
		List<Country> countries = null;
		String sql = "SELECT code code, name name, continent continent, population population FROM country WHERE continent = ?";
		BeanPropertyRowMapper<Country> rm = new BeanPropertyRowMapper<Country>(Country.class);
		countries = getJdbcTemplate().query(sql, new Object[]{continent}, rm);	
		return countries;
	}
	
	public List<Continent> getAllContinents() {
		List<Continent> continents = null;
		String sql = "SELECT DISTINCT continent name FROM country";
		BeanPropertyRowMapper<Continent> rm = new BeanPropertyRowMapper<Continent>(Continent.class);
		continents = getJdbcTemplate().query(sql, rm);
		return continents;
		
	}
	
	public List<Country> getAllCountries() {
		List<Country> countries = null;
		String sql = "SELECT code code, name name, continent continent, population population FROM country";
		BeanPropertyRowMapper<Country> rm = new BeanPropertyRowMapper<Country>(Country.class);
		countries = getJdbcTemplate().query(sql, rm);	
		return countries;
	}
	
	public String getContinent(String countryCode) {
		String continent = null;
		String sql = "SELECT continent FROM country WHERE code = ?";
		BeanPropertyRowMapper<String> rm = new BeanPropertyRowMapper<String>(String.class);
		continent = getJdbcTemplate().queryForObject(sql, new Object[]{countryCode}, rm);	
		return continent;
	}
	
}
