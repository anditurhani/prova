package it.objectmethod.world.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.world.dao.ICityDao;
import it.objectmethod.world.dao.ICountryDao;
import it.objectmethod.world.model.City;
import it.objectmethod.world.model.Country;

@Controller
public class CityController {
	
	@Autowired
	private ICountryDao countryDao;
	@Autowired
	private ICityDao cityDao;
	
	@GetMapping("/cities")
	public String cityList(@RequestParam("countrycode") String countryCode, ModelMap model) {
		List<City> cityList = cityDao.getCityListByCountryCode(countryCode);
		model.addAttribute("cityList", cityList);
		return "cities";
	}
	
	@GetMapping("/search")
	public String searchCities(@RequestParam(name="searchStr") String searchStr, @RequestParam(name="countrycode") String countryCode, ModelMap model) {
		searchStr = "%" + searchStr + "%";
		List<City> cityList = cityDao.searchCities(searchStr, countryCode);
		model.addAttribute("cityList", cityList);
		return "cities";
	}
	
	@GetMapping("/updateCities")
	public String updateCities(@RequestParam(name="id") String idStr, @RequestParam(name="name") String name, @RequestParam(name="population") int population, @RequestParam(name="countrycode") String countryCode, ModelMap model, HttpSession session) {
		String message = null;
		String continent = null;
		if (population >= 0) {
			City city = new City();
			city.setName(name);
			city.setPopulation(population);
			city.setCountryCode(countryCode);
			if (!idStr.equals("")) {
				try {
					int id = Integer.parseInt(idStr);
					city.setId(id);
					//cityDao.modifyCity(city);
					message = "city updated";
				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {

				//cityDao.insertCity(city);
				message = "city added";
			}
		}

		continent = countryDao.getContinent(countryCode);
	    session.setAttribute("continent", continent);
		model.addAttribute("message", message);
		model.addAttribute("countrycode", countryCode);

		return "forward:/cities";
	}
	
	@GetMapping("/updateForm")
	public String loadUpdateForm(@RequestParam(name="id") String idStr, ModelMap model) {
		List<Country> countryList = countryDao.getAllCountries();
		try {
			int id = Integer.parseInt(idStr);
			City city = cityDao.getCityById(id);
			model.addAttribute("id", city.getId());
			model.addAttribute("name", city.getName());
			model.addAttribute("population", city.getPopulation());
			model.addAttribute("countrycode", city.getCountrycode());
		} catch (Exception e) {
			e.printStackTrace();
		}
		model.addAttribute("countryList", countryList);
		return "updateForm";
	}

}
