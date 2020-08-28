package it.objectmethod.world.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.objectmethod.config.ConnectionFactory;
import it.objectmethod.world.dao.ICountryDao;
import it.objectmethod.world.model.Country;

public class CountryDaoImpl implements ICountryDao {
	
	@Override
	public List<Country> getCountryListByContinent(String continent) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		Country country = null;
		List<Country> countries = null;

		try {
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT code, name, continent, population FROM country WHERE continent = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, continent);
			rs = stmt.executeQuery();
			countries = new ArrayList<Country>();

			while (rs.next()) {
				country = new Country();
				country.setCode(rs.getString("code"));
				country.setName(rs.getString("name"));
				country.setPopulation(rs.getInt("population"));
				country.setContinent("continent");

				countries.add(country);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return countries;

	}

	@Override
	public List<String> getAllContinents() {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		List<String> continents = null;

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT DISTINCT continent FROM country";
			rs = stmt.executeQuery(sql);

			continents = new ArrayList<String>();

			while (rs.next()) {
				continents.add(rs.getString("continent"));
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return continents;

	}

	@Override
	public List<Country> getAllCountries() {

		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;

		Country country = null;
		List<Country> countries = null;

		try {
			conn = ConnectionFactory.getConnection();
			stmt = conn.createStatement();
			String sql = "SELECT code, name, continent, population FROM country";
			rs = stmt.executeQuery(sql);

			countries = new ArrayList<Country>();

			while (rs.next()) {
				country = new Country();

				country.setCode(rs.getString("code"));
				country.setName(rs.getString("name"));
				country.setContinent(rs.getString("continent"));
				country.setPopulation(rs.getInt("population"));

				countries.add(country);
			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return countries;

	}
	
	public String getContinent(String countryCode) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		String continent = null;

		try {
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT continent FROM country WHERE code = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, countryCode);
			rs = stmt.executeQuery();

			while (rs.next()) {

				continent = rs.getString("continent");

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}

		return continent;
	}

}
