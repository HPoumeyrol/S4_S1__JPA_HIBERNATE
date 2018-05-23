package co.simplon.patrimoine.model;


import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name= "CITIES")
@NamedQueries({
	@NamedQuery(name = "City.findAll", query = " SELECT c FROM City c ORDER BY c.name "),
	@NamedQuery(name = "City.findAllNames", query = " SELECT c.name FROM City c ORDER BY c.name "),
	@NamedQuery(name = "City.deleteById", query = " DELETE FROM City c WHERE c.id = :id") 
})
public class City {
	@Id 
//	@GeneratedValue(strategy= GenerationType.SEQUENCE, generator = "cities_generator")
//	@SequenceGenerator(name="cities_generator", sequenceName = "cities_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;
	
	@Column(name="NAME", nullable=false, length=255)
    private String name;
	
	@Column(name="LATITUDE")
    private Double latitude;
	
	@Column(name="LONGITUDE")
    private Double longitude;

	@OneToMany(mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Monument> monuments;

	
    public City() {
    }
    
    public City(Long id) {
    	this(id, null, 0., 0.);
    }
    
    public City(String name, double latitude, double longitude) {
	  this(null, name, latitude, longitude);
    }
    
    public City(Long id, String name, double latitude, double longitude) {
	  this.id= id;
	  this.name= name;
	  this.latitude= latitude;
	  this.longitude= longitude;
    }
    
    public Long getId() {
	  return id;
    }

    public void setId(Long id) {
	  this.id = id;
    }

    public String getName() {
	  return name;
    }

    public void setName(String nom) {
	  this.name = nom;
    }

    public Double getLongitude() {
	  return this.longitude;
    }

    public void setLongitude(Double longitude) {
	  this.longitude = longitude;
    }

    public Double getLatitude() {
	  return this.latitude;
    }

    public void setLatitude(Double latitude) {
	  this.latitude = latitude;
    }

    
    
    public Set<Monument> getMonuments() {
		return monuments;
	}

	@Override
    public String toString() {
      String affichage = "\n--------------------------\n" + super.toString() + " " + "City [id=" + id + ", name=" + name + ", latitude=" + latitude
		      + ", longitude=" + longitude + "]\n";
      
      // list monuments associated with this city 
      if (getMonuments() != null ) {
    	  affichage += "Monuments :\n";
    	  for (Monument monument : getMonuments())
    	  {
    		  affichage += "\t" +  monument + "\n";
    	  }
      }
      affichage += "--------------------------\n";
	  return  affichage;

    }
}
