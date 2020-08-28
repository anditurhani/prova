package it.objectmethod.world.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.objectmethod.config.ConnectionFactory;
import it.objectmethod.world.dao.ICityDao;
import it.objectmethod.world.model.City;

public class CityDaoImpl implements ICityDao {
	
	@Override
	public List<City> getCityListByCountryCode(String countryCode) {
		List<City> cities = null;
		City city = null;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT id, name, population, countrycode FROM city WHERE countrycode = ? ";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, countryCode);
			rs = stmt.executeQuery();
			cities = new ArrayList<City>();
			while (rs.next()) {
				city = new City();
				city.setId(rs.getInt("id"));
				city.setName(rs.getString("name"));
				city.setPopulation(rs.getInt("population"));
				city.setCountryCode("countrycode");

				cities.add(city);
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

		return cities;

	}

	@Override
	public List<City> searchCities(String searchStr, String countryCodeStr) {
		List<City> cities = null;
		City city = null;

		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn = ConnectionFactory.getConnection();
			String sql = "SELECT id, name, population, countrycode FROM city WHERE name LIKE ? AND ('' = ? OR countrycode = ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, searchStr);
			stmt.setString(2, countryCodeStr);
			stmt.setString(3, countryCodeStr);

			rs = stmt.executeQuery();

			cities = new ArrayList<City>();
			while (rs.next()) {
				city = new City();
				city.setId(rs.getInt("id"));
				city.setName(rs.getString("Name"));
				city.setPopulation(rs.getInt("Population"));
				city.setCountryCode("countrycode");
				cities.add(city);
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

		return cities;
	}

	@Override
	public void insertCity(City city) {

		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionFactory.getConnection();
			String sql;
			sql = "INSERT INTO city (name, countrycode, population) VALUES (?, ?, ?)";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, city.getName());
			stmt.setString(2, city.getCountrycode());
			stmt.setInt(3, city.getPopulation());
			stmt.executeUpdate();

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
	}

	@Override
	public void modifyCity(City city) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = ConnectionFactory.getConnection();
			String sql;
			sql = "UPDATE city SET name = ?, countrycode = ?, population = ? WHERE id = ?";
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, city.getName());
			stmt.setString(2, city.getCountrycode());
			stmt.setInt(3, city.getPopulation());
			stmt.setInt(4, city.getId());
			stmt.executeUpdate();

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
	}

	@Override
	public City getCityById(int id) {
		Connection conn = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		City city = null;

		try {
			conn = ConnectionFactory.getConnection();
			String sql;
			sql = "SELECT id, name, population, countrycode FROM city WHERE id = ?";

			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, id);

			rs = stmt.executeQuery();

			city = new City();

			while (rs.next()) {
				city.setId(rs.getInt("id"));
				city.setName(rs.getString("name"));
				city.setPopulation(rs.getInt("population"));
				city.setCountryCode(rs.getString("countrycode"));
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

		return city;

	}

}
