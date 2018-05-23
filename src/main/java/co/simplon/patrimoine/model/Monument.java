package co.simplon.patrimoine.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "MONUMENTS")
@NamedQueries({
	@NamedQuery(name = "Monument.findAll", query = " SELECT c FROM Monument c ORDER BY c.name "),
	@NamedQuery(name = "Monument.deleteById", query = " DELETE FROM Monument c WHERE c.id = :id") 
})
public class Monument {
	@Id
	// @GeneratedValue(strategy= GenerationType.SEQUENCE, generator =
	// "monuments_generator")
	// @SequenceGenerator(name="monuments_generator", sequenceName =
	// "monuments_seq", allocationSize=1)
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	@Column(name = "ID", updatable = false, nullable = false)
	private Long id;

	@Column(name = "NAME", nullable = false, length = 255)
	private String name;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_cities", foreignKey = @ForeignKey(name = "fk_cities"))
	private City city;

	@ManyToMany(mappedBy = "monuments")
	private Set<User> users = new HashSet<User>();

	public Monument() {
	}

	public Monument(Long id) {
		this(id,null,null);
	}

	public Monument(String name, City city) {
		this(null,name,city);
	}

	public Monument(Long id, String name, City city) {
		this.id = id;
		this.name = name;
		this.city = city;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return super.toString() + " " + "Monument [id=" + id + ", name=" + name + ", city=(id=" + city.getId() + ") "
				+ city.getName() + "]";
	}
}