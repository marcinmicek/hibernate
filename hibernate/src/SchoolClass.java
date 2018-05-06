import java.util.Set;

@SuppressWarnings("serial")
public class SchoolClass implements java.io.Serializable {

	// in School.db:
	// CREATE TABLE schoolClasses (id integer, profile varchar, currentYear integer, startYear timestamp, school_id bigint, PRIMARY KEY (id));
	
	private long id;
	private int startYear;
	private int currentYear;
	private String profile;
	private int school_id;
	private Set<Student> students;
	private Set<Teacher> teachers;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getStartYear() {
		return startYear;
	}

	public void setStartYear(int startYear) {
		this.startYear = startYear;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public void setCurrentYear(int currentYear) {
		this.currentYear = currentYear;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
	public int getSchool_id() {
		return school_id;
	}

	public void setSchool_id(int school_id) {
		this.school_id = school_id;
	}
	
	public Set<Student> getStudents() {
		return students;
	}
	
	public void setStudents(Set<Student> students) {
		this.students = students;
	}
	
	public Set<Teacher> getTeachers() {
		return teachers;
	}

	public void setTeachers(Set<Teacher> teachers) {
		this.teachers = teachers;
	}
	
	public String toString() {
		return "Class: " + profile + "\n    Start Year: " + getStartYear() + "\n    Current year: " + getCurrentYear() + "\n    School ID: " + getSchool_id();
	}

}