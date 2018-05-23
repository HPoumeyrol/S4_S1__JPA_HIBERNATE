package co.simplon.patrimoine.dao.jpa;
import java.util.List;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import co.simplon.patrimoine.dao.CityDao;
import co.simplon.patrimoine.model.City;

public class CityDaoJpa extends AbstractDaoJpa<City> implements CityDao{

	public CityDaoJpa(EntityManager entityManager) {
		super(entityManager, City.class);
		
	}

	public List<?> getVisitors(City city) {
	Query query= super.getEntityManager().createQuery("SELECT u.name, m.name FROM User as u join u.monuments m ");
		//query.setParameter("nameParam", city.getName());
		return query.getResultList();
	}

}
