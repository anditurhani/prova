package it.objectmethod.world.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.objectmethod.world.dao.ICountryDao;
import it.objectmethod.world.dao.impl.CountryDaoImpl;
import it.objectmethod.world.model.Country;

@Controller
public class CountryController {
	
	private ICountryDao countryDao = new CountryDaoImpl();
	
	@GetMapping("/continents")
	public String continentList(ModelMap model) {
		List<String> continentList = countryDao.getAllContinents();
		model.addAttribute("continentList", continentList);
		return "continents";
		
	}
	
	@GetMapping("/countries")
	public String countrytList(@RequestParam(name="continent", required = false) String continent, ModelMap model, HttpSession session) {
		if (continent != null) {
			session.setAttribute("continent", continent);
		} else {
			continent = (String) session.getAttribute("continent");
		}
		List<Country> countryList = countryDao.getCountryListByContinent(continent);
		model.addAttribute("countryList", countryList);
		return "countries";
	}

	@GetMapping("/searchForm")
	public String loadSearchForm(ModelMap model) {
		List<Country> countryList = countryDao.getAllCountries();
		model.addAttribute("countryList", countryList);
		return "searchForm";
	}

}
