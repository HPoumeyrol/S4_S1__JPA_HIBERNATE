package co.simplon.patrimoine.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "USERS")
@NamedQueries({
	@NamedQuery(name = "User.findAll", query = " SELECT c FROM User c ORDER BY c.name "),
	@NamedQuery(name = "User.deleteById", query = " DELETE FROM User c WHERE c.id = :id") 
})

public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "NAME", nullable = false, length = 100)
	private String name;
	
	@ManyToMany
    @JoinTable(name= "USER_MONUMENT",
                   joinColumns = {@JoinColumn(name = "FK_USER", referencedColumnName= "ID",  foreignKey= @ForeignKey(name = "FK_USER")) },
                   inverseJoinColumns = { @JoinColumn(name = "FK_MONUMENT", referencedColumnName= "ID", foreignKey= @ForeignKey(name = "FK_MONUMENT")) })
    private Set<Monument> monuments = new HashSet<Monument>();
	
	public User() {
	}
	
	public User(Long id) {
    	this(id, null);
    }
	public User(String name) {
		this(null, name);
	}
	
	public User(Long id, String name) {
		this.id= id;
		this.name= name;
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
	
	public void setName(String name) {
		this.name= name;
	}
	
	public void addMonument(Monument m){
		monuments.add(m);
		m.getUsers().add(this);
	}
	
	public Set<Monument> getMonuments(){
		return monuments;
	}
	
	public void setMonuments(Set<Monument> monuments) {
		this.monuments= monuments;
	}
	
	public String toString() {
		return "User :{ id= "+id+"\n name= "+name+"\n nb momunents : "+ monuments.size()+"\n}";
	}
	
}