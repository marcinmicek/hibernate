import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class Main {

	Session session;

	public static void main(String[] args) {
		Main main = new Main();
		
		// Cwiczenie nr 4 - wyszukiwanie i edycja - uaktywnic tylko metody main.addSchoolUE(), main.executeQueries() i modifySchoolData()
		// w odpowiedniej kolejnosci
		// main.addSchoolUE(); // cwiczenie 4 - przed punktem 3
		// main.executeQueries(); // cwiczenie 4 - punkt 3.1 - 3.6
		// main.modifySchoolData(); // cwiczenie 4 - punkt 5
		
		
		// Cwiczenie nr 5 - Dalszy rozwoj (relacja many to many)
		// tworzenie tabeli teachers w bazie poprzez query na bazie
		// CREATE TABLE teachers (id INTEGER PRIMARY KEY, name TEXT, surname TEXT, title TEXT, pesel TEXT);
		
		// tworzenie tabeli "join" (teachers_schoolclasses) w bazie poprzez query na bazie
		// CREATE TABLE teachers_schoolClasses (teacher_id INTEGER REFERENCES teachers (id), class_id BIGINT REFERENCES schoolClasses (id));
		
		// Dla dodawania i wyswietlania szkol wraz z klasami, studentami i nauczycielami 
		// nalezy uaktywnic tylko metody ponizej
		main.addNewData();
		main.printSchools();
		
		main.close();
	}

	
	
	public Main() {
		session = HibernateUtil.getSessionFactory().openSession();
	}

	public void close() {
		session.close();
		HibernateUtil.shutdown();
	}
	
	public void addSchoolUE() {
		/* szkola UE*/
		School schoolUE = new School();
		Set<SchoolClass> classesToInsert = new HashSet<SchoolClass>();
		schoolUE.setName("UE");
		schoolUE.setAddress("ul. Krakowska 15, Warszawa");
		
		/* klasa nalezaca do szkoly UE i do zbioru klas classesToInsert */
		SchoolClass schoolClass = new SchoolClass();
		Set<Student> studentsToInsert = new HashSet<Student>();
		schoolClass.setStartYear(2008);
		schoolClass.setCurrentYear(1);
		schoolClass.setProfile("biol.");
		schoolUE.setClasses(classesToInsert);
		classesToInsert.add(schoolClass);
		
		/* student nalezacy do klasy w szkole UE i do zbioru studentow studentsToInsert */
		Student student = new Student();
		student.setName("Jacek");
		student.setSurname("Jackowski");
		student.setPesel("89060911223");
		schoolClass.setStudents(studentsToInsert);
		studentsToInsert.add(student);
		
		Transaction transaction = session.beginTransaction();
		session.save(schoolUE); // gdzie newSchool to instancja nowej szko³y
		transaction.commit();
	}
	
	public void addNewData() {
		/* SZKOLA */
		/* nowa szkola */
		School newSchool = new School();
		Set<SchoolClass> classesToInsert = new HashSet<SchoolClass>();
		newSchool.setName("PK");
		newSchool.setAddress("ul. Warszawska 24, Kraków");
		newSchool.setClasses(classesToInsert);
		
		/* KLASY */
		/* nowa klasa "A" nalezaca do nowej szkoly i do zbioru klas classesToInsert i posiadajaca zbior studentow studentsToInsert1
		 * oraz zbior nauczycieli teachersToInsert1 */
		SchoolClass newSchoolClassA = new SchoolClass();
		Set<Student> studentsToInsert1 = new HashSet<Student>();
		Set<Teacher> teachersToInsert1 = new HashSet<Teacher>();
		newSchoolClassA.setStartYear(2011);
		newSchoolClassA.setCurrentYear(4);
		newSchoolClassA.setProfile("bud.");
		newSchoolClassA.setStudents(studentsToInsert1);
		newSchoolClassA.setTeachers(teachersToInsert1);
		classesToInsert.add(newSchoolClassA);
		
		/* nowa klasa "B" nalezaca do nowej szkoly i do zbioru klas classesToInsert i posiadajaca zbior studentow studentsToInsert2
		 * oraz zbior nauczycieli teachersToInsert2 */
		SchoolClass newSchoolClassB = new SchoolClass();
		Set<Student> studentsToInsert2 = new HashSet<Student>();
		Set<Teacher> teachersToInsert2 = new HashSet<Teacher>();
		newSchoolClassB.setStartYear(2009);
		newSchoolClassB.setCurrentYear(3);
		newSchoolClassB.setProfile("chem.");
		newSchoolClassB.setStudents(studentsToInsert2);
		newSchoolClassB.setTeachers(teachersToInsert2);
		classesToInsert.add(newSchoolClassB);
		
		/* STUDENCI */
		/* nowy student 1 nalezacy do nowej klasy "A" i do zbioru studentow studentsToInsert1 */
		Student newStudent1 = new Student();
		newStudent1.setName("Piotr");
		newStudent1.setSurname("Piotrowski");
		newStudent1.setPesel("88010298765");
		studentsToInsert1.add(newStudent1);
		
		/* nowy student 2 nalezacy do nowej klasy "A" i do zbioru studentow studentsToInsert1 */
		Student newStudent2 = new Student();
		newStudent2.setName("Pawe³");
		newStudent2.setSurname("Pawelski");
		newStudent2.setPesel("87111200001");
		studentsToInsert1.add(newStudent2);
		
		/* nowy student 3 nalezacy do nowej klasy "A" i do zbioru studentow studentsToInsert1 */
		Student newStudent3 = new Student();
		newStudent3.setName("Andrzej");
		newStudent3.setSurname("Andrzejewski");
		newStudent3.setPesel("86090922234");
		// newSchoolClassA.setStudents(studentsToInsert1);
		studentsToInsert1.add(newStudent3);
		
		/* nowy student 4 nalezacy do nowej klasy "B" i do zbioru studentow studentsToInsert2 */
		Student newStudent4 = new Student();
		newStudent4.setName("Paulina");
		newStudent4.setSurname("Paulinska");
		newStudent4.setPesel("89070766667");
		// newSchoolClassB.setStudents(studentsToInsert2);
		studentsToInsert2.add(newStudent4);
		
		/* nowy student 5 nalezacy do nowej klasy "B" i do zbioru studentow studentsToInsert2 */
		Student newStudent5 = new Student();
		newStudent5.setName("Michalina");
		newStudent5.setSurname("Michalska");
		newStudent5.setPesel("85050534345");
		// newSchoolClassB.setStudents(studentsToInsert2);
		studentsToInsert2.add(newStudent5);
		
		/* nowy student 6 nalezacy do nowej klasy "B" i do zbioru studentow studentsToInsert2 */
		Student newStudent6 = new Student();
		newStudent6.setName("Wiktoria");
		newStudent6.setSurname("Wiktorowska");
		newStudent6.setPesel("87030311996");
		// newSchoolClassB.setStudents(studentsToInsert2);
		studentsToInsert2.add(newStudent6);
		
		/* NAUCZYCIELE */
		/* nowy nauczyciel 1 nalezacy TYLKO do zbioru nauczycieli teachersToInsert1 przypisanego do nowej klasy "A" */
		Teacher newTeacher1 = new Teacher();
		newTeacher1.setName("Adam");
		newTeacher1.setSurname("Jakubowski");
		newTeacher1.setTitle("dr hab");
		newTeacher1.setPesel("78010298765");
		teachersToInsert1.add(newTeacher1);
		
		/* nowy nauczyciel 2 nalezacy do zbioru nauczycieli teachersToInsert1 przypisanego do nowej klasy "A"
		 * ORAZ do zbioru nauczycieli teachersToInsert2 przypisanego do nowej klasy "B" */
		Teacher newTeacher2 = new Teacher();
		newTeacher2.setName("Damian");
		newTeacher2.setSurname("Drozdowski");
		newTeacher2.setTitle("prof zw");
		newTeacher2.setPesel("77121200001");
		teachersToInsert1.add(newTeacher2);
		teachersToInsert2.add(newTeacher2);
		
		/* nowy nauczyciel 3 nalezacy TYLKO do zbioru nauczycieli teachersToInsert2 przypisanego do nowej klasy "B" */
		Teacher newTeacher3 = new Teacher();
		newTeacher3.setName("Anna");
		newTeacher3.setSurname("Kowal");
		newTeacher3.setTitle("mgr in¿");
		newTeacher3.setPesel("83010990000");
		teachersToInsert2.add(newTeacher3);
		
		/* nowy nauczyciel 4 nalezacy do zbioru nauczycieli teachersToInsert1 przypisanego do nowej klasy "A"
		 * ORAZ do zbioru nauczycieli teachersToInsert2 przypisanego do nowej klasy "B" */
		Teacher newTeacher4 = new Teacher();
		newTeacher4.setName("Dagmara");
		newTeacher4.setSurname("Kubicka");
		newTeacher4.setTitle("dr");
		newTeacher4.setPesel("85110770707");
		teachersToInsert1.add(newTeacher4);
		teachersToInsert2.add(newTeacher4);
	
		Transaction transaction = session.beginTransaction();
		session.save(newSchool); // gdzie newSchool to instancja nowej szko³y
		transaction.commit();
		
	}
	
	@SuppressWarnings("unchecked")
	private void printSchools() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Criteria crit = session.createCriteria(School.class);
		List<School> schools = crit.list();

		System.out.println("### Schools and classes");
		for (School s : schools) {
			System.out.println("\n" + s);
			for (SchoolClass schoolClass : s.getClasses()) {
				System.out.println(schoolClass);
				if (schoolClass.getStudents().size() > 0) {
					System.out.println("    > Students:");
					for (Student student : schoolClass.getStudents()) {
						System.out.println(student);
					}
				}
				
				if (schoolClass.getTeachers().size() > 0) {
					System.out.println("    > Teachers:");
					for (Teacher teacher : schoolClass.getTeachers()) {
						System.out.println(teacher);
					}
				}
			}
		}
	}
	
	private void executeQueries() {
		
		// query0(); // cwiczenie 4 - punkt 2
		// query1(); // cwiczenie 4 - punkt 3.1
		// query2(); // cwiczenie 4 - punkt 3.2
		// query3(); // cwiczenie 4 - punkt 3.3
		// query4(); // cwiczenie 4 - punkt 3.4
		// query5(); // cwiczenie 4 - punkt 3.5
		// query6(); // cwiczenie 4 - punkt 3.6
	}
	
	private void query0() {
		String hql = "FROM School";
		Query query = session.createQuery(hql);
		List results = query.list();
		System.out.println(results);
	}
	
	private void query1() {
		String hql = "FROM School s WHERE s.name = 'UE'";
		Query query = session.createQuery(hql);
		List results = query.list();
		System.out.println(results);
	}
	
	private void query2() {
		String hql = "FROM School s WHERE s.name = 'UE'";
		Query query = session.createQuery(hql);
		List<School> results = query.list();
		Transaction transaction = session.beginTransaction();
		for (School s : results) {
			session.delete(s);
		}
		transaction.commit();
		System.out.println(results);
	}
	
	private void query3() {
		String hql = "SELECT COUNT(s) FROM School s";
		Query query = session.createQuery(hql);
		Integer schoolsQuantity = (Integer) query.uniqueResult();
		System.out.println("Schools quantity: " + schoolsQuantity);
	}
	
	private void query4() {
		String hql = "SELECT COUNT(st) FROM Student st";
		Query query = session.createQuery(hql);
		Integer studentsQuantity = (Integer) query.uniqueResult();
		System.out.println("Students quantity: " + studentsQuantity);
	}
	
	private void query5() {
		String hql = "FROM School s WHERE size(s.classes) >= 2";
		Query query = session.createQuery(hql);
		List results = query.list();
		System.out.println(results);
	}
	
	private void query6() {
		String hql = "SELECT s FROM School s INNER JOIN s.classes classes "
				+ "WHERE classes.profile = 'mat-fiz' and classes.currentYear >= 2";
		Query query = session.createQuery(hql);
		List results = query.list();
		System.out.println(results);
	}
	
	private void modifySchoolData() {
		Query query = session.createQuery("from School where id= :id");
		query.setLong("id", 1);
		School school = (School) query.uniqueResult();
		System.out.println(school);
		school.setAddress("ul. Kopernika 12, Kraków");
		//school.setAddress("ul. Mickiewicza 30");
		Transaction transaction = session.beginTransaction();
		session.save(school);
		transaction.commit();
		System.out.println(school);
	}
	
	private void jdbcTest() {
		Connection conn = null;
		Statement stmt = null;
		try {
			// STEP 2: Register JDBC driver
			Class.forName("org.sqlite.JDBC");

			// STEP 3: Open a connection
			System.out.println("Connecting to database...");
			conn = DriverManager.getConnection("jdbc:sqlite:school.db", "", "");

			// STEP 4: Execute a query
			System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT * FROM schools";
			ResultSet rs = stmt.executeQuery(sql);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				String name = rs.getString("name");
				String address = rs.getString("address");

				// Display values
				System.out.println("Name: " + name);
				System.out.println(" address: " + address);
			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Goodbye!");
	}// end jdbcTest

}
