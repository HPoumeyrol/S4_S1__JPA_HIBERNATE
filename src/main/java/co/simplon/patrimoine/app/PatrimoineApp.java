package co.simplon.patrimoine.app;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import co.simplon.patrimoine.dao.CityDao;
import co.simplon.patrimoine.dao.MonumentDao;
import co.simplon.patrimoine.dao.UserDao;
import co.simplon.patrimoine.dao.jpa.CityDaoJpa;
import co.simplon.patrimoine.dao.jpa.MonumentDaoJpa;
import co.simplon.patrimoine.dao.jpa.UserDaoJpa;
import co.simplon.patrimoine.model.City;
import co.simplon.patrimoine.model.Monument;
import co.simplon.patrimoine.model.User;

/**
 * Hello world!
 *
 */
public class PatrimoineApp implements AutoCloseable
{
	private static final String PERSITENCE_UNIT_NAME = "demo-jpa-1";
	private EntityManagerFactory entityManagerFactory;
	
	PatrimoineApp() {
		entityManagerFactory = createEntityManagerFactory();
		
	}
	
	
    
    public void close() {
    	entityManagerFactory.close();
    }
    
    
    private EntityManagerFactory createEntityManagerFactory() {
        
        Map<String, String> env = System.getenv();
        Map<String, Object> configOverrides = new HashMap<String, Object>();
        for (String envName : env.keySet()) {
          if (envName.contains("DB_USER")) {
            configOverrides.put("javax.persistence.jdbc.user", env.get(envName));
          }
          if (envName.contains("DB_PASS")) {
            configOverrides.put("javax.persistence.jdbc.password", env.get(envName));
          }
          if (envName.contains("DB_URL")) {
            configOverrides.put("javax.persistence.jdbc.url", env.get(envName));    
          }
          if (envName.contains("DB_SCHEMA")) {
              //configOverrides.put("hibernate.default_schema", env.get(envName));    
          }
        }
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSITENCE_UNIT_NAME
                                                    ,configOverrides);

        return entityManagerFactory;
    }
    
    

    
    
    public static void main( String[] args )
    {
        System.out.println( "Hello JPA Demo" );
        try(PatrimoineApp app = new PatrimoineApp()) {
        	
        	EntityManager entityManager = app.entityManagerFactory.createEntityManager();
        	
        	entityManager.getTransaction().begin();
    		
        	
        	// City
        	//Creation DAO
        	CityDao cityDAO = new CityDaoJpa(entityManager);
        	
        	//Creation
        	City city1= cityDAO.create(new City("Paris", 0. , 0.)); 	
        	System.out.println(city1);
        	
        	//Update
        	city1.setName("PARIS");
        	city1.setLatitude(10.1);
        	city1.setLongitude(30.5);
        	cityDAO.update(city1);
        	
        	//Get By ID
        	City city2= cityDAO.getById(city1.getId());
        	System.out.println(city2);
       
        	System.out.println("***************");
        	System.out.println("Liste des cités");
        	System.out.println("***************");
        	for(City city : cityDAO.findAll(0, 10)) {
        		System.out.println(city);
        	}
        	System.out.println("***************");
        	
        	
        	System.out.println("***************");
        	System.out.println("Liste des cités");
        	System.out.println("***************");
        	for(String name : cityDAO.findAllNames(0, 10)) {
        		System.out.println(name);
        	}
        	System.out.println("***************");
        	
        	
        	//Monuments
        	//Creation DAO
        	MonumentDao monumentDao = new MonumentDaoJpa(entityManager);
        	
        	//Creation
        	Monument monument1= monumentDao.create(new Monument("Tour Eiffel", city2)); 	
        	System.out.println("Monument 1 : " + monument1);
        	
        	//Update
        	monument1.setName("Arc Triomphe");
        	monumentDao.update(monument1);
        	
        	//Get By ID
        	Monument monument2= monumentDao.getById(monument1.getId());
        	System.out.println("Monument 2 : " + monument2);
        	
        	
        	System.out.println("***************");
        	System.out.println("Liste des Monuments");
        	System.out.println("***************");
        	for(Monument monument : monumentDao.findAll(0, 10)) {
        		System.out.println(monument);
        	}
        	System.out.println("***************");
        	
        	
        	//User
        	//Creation DAO
        	UserDao userDao = new UserDaoJpa(entityManager);
        	
        	//Creation
        	User user1= userDao.create(new User("Hugues")); 	
        	System.out.println("User 1 : " + user1);
        	
        	//Update
        	user1.setName("Hugues POUMEYROL");
        	user1.addMonument(monument2);
        	userDao.update(user1);
        	
        	
        	//Get By ID
        	User user2= userDao.getById(user1.getId());
        	System.out.println("USER 2 : " + user2);
       	
        	
        	
        	System.out.println("===================================");
        	
        	System.out.println("Liste des visiteurs : " + cityDAO.getVisitors(city1));
        	
        	
        	
        	//Delete Monument City User
        	monumentDao.deleteById(monument2.getId());
        	cityDAO.deleteById(city2.getId());
        	userDao.deleteById(user2.getId());
        	
        	entityManager.getTransaction().commit();
        	
        	//Close du entity manager
        	entityManager.close();
        }
    	catch(Exception e)
    	{
    		System.out.println(e);
    	}
    
    	
    }
    
    
}
