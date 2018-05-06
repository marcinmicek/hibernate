import java.util.Set;

@SuppressWarnings("serial")
public class Teacher implements java.io.Serializable {
	
	// in School.db: 
	// CREATE TABLE teachers (id INTEGER PRIMARY KEY, name TEXT, surname TEXT, title TEXT, pesel TEXT);
	
	private long id;
	private String name;
	private String surname;
	private String title;
	private String pesel;
	private Set<SchoolClass> classes;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	
	public Set<SchoolClass> getClasses() {
		return classes;
	}
	public void setClasses(Set<SchoolClass> classes) {
		this.classes = classes;
	}
	
	public String toString() {
		return "      " + getName() + " " + getSurname() + ", " + getTitle() + " (PESEL: " + getPesel() + ")";
	}
	
}