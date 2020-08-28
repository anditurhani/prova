package it.objectmethod.world.dao;

import java.util.List;

import it.objectmethod.world.model.City;

public interface ICityDao {
	
	public List<City> getCityListByCountryCode(String countryCode);

	public List<City> searchCities(String searchStr, String countryCodeStr);

	public void insertCity(City city);

	public void modifyCity(City city);

	public City getCityById(int id);

}
