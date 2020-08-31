package it.objectmethod.world.dao;

import java.util.List;

import it.objectmethod.world.model.Continent;
import it.objectmethod.world.model.Country;

public interface ICountryDao {
	
	public List<Country> getCountryListByContinent(String continent);

	public List<Continent> getAllContinents();

	public List<Country> getAllCountries();
	
	public String getContinent(String countryCode);

}
