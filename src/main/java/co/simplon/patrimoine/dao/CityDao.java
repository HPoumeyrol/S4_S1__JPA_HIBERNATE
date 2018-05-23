package co.simplon.patrimoine.dao;

import java.util.List;

import co.simplon.patrimoine.model.City;

public interface CityDao extends AbstractDao<City>{

	public List<?> getVisitors(City city);

			
}
