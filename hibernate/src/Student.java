import java.util.Set;

@SuppressWarnings("serial")
public class Student implements java.io.Serializable {
	
	// in School.db:
	// CREATE TABLE students (id INTEGER PRIMARY KEY, name TEXT, surname TEXT, class_id BIGINT REFERENCES schoolClasses (id), pesel TEXT);
	
	private long id;
	private String name;
	private String surname;
	private int class_id;
	private String pesel;
	private Set<Student> students;
	
	public Set<Student> getStudents() {
		return students;
	}
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
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
	
	public int getClass_id() {
		return class_id;
	}

	public void setClass_id(int class_id) {
		this.class_id = class_id;
}
	
	public String getPesel() {
		return pesel;
	}
	public void setPesel(String pesel) {
		this.pesel = pesel;
	}
	
	public String toString() {
		return "      " + getName() + " " + getSurname() + " (PESEL: "  + getPesel() + "), Class ID: " + getClass_id();
	}
	
}
