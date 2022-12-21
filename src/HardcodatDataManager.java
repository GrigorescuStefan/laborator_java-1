import java.util.*;

public class HardcodatDataManager implements IDataLoader {
	public ManagerCursuri manager = new ManagerCursuri();
	// Obiectul rand genereaza numere aleatorii. Folosit in programul de testare
	public Random rand = new Random();
	public int minimumRequiredStudents = 5;
	public Student[] dataSetOfStudent = createStudentsData();
	public Profesor[] dataSetOfProfesor = createProfesorData();
	
	public HardcodatDataManager() {
		this.createCoursesData();
	}
	
	// Numele au fost alese la intamplare
	public Student[] createStudentsData() {
		String nume[] = { "Adamescu", "Aldulescu", "Balauru", "Baicu", "Becheru", "Botnari", "Budescu", "Caiman", "Caciuc", "Călătoru", "Debu", "Dianu", "Daiciu", "Digulescu", "Dinea", "Ecobescu"
				, "Elencu", "Elisei", "Elvireanu", "Enache", "Encea", "Eretescu", "Erneanu", "Ersegean", "Evinoiu", "Fachin", "Fagu", "Faroghi", "Fechea", "Fecioru", "Felecanu"
				, "Feodor", "Gaboreanu", "Gaftea", "Galaicu", "Galeru", "Geaferu", "Ghemea", "Ghenoiu" };
		String prenume[] = { "Mihai", "Andrei", "Stefan", "Nicolae", "Maria", "Cristina", "Ionut", "Mihai", "Marius", "Alin", "Iulian", "Octavian", "Daria", "Lucian", "Luca", "Daniel" ,"Marius"
				, "Cristian", "Daniel", "Elena", "Ana-Maria", "Valentina", "Ioana", "Vasile", "Paul", "Bianca", "David", "Constantin", "Georgiana", "Cezar", "Victor", "Razvan", "Emanuel", "Florentina", "Ioan", "Marian" ,"Nina", "Tudor", "Madalina"};
		
		Student studenti[] = new Student[nume.length];
		for (int i = 0; i < nume.length;i++) {
			Student s = new Student(nume[i], prenume[i], 1 + rand.nextInt(4));
			studenti[i] = s;
		}
		return studenti;
	}
	
	public Profesor[] createProfesorData() {
		String nume[] = { "URSUTIU", "PANA", "ALEXANDRU","CRETU ", "KRISTALY", "DANILA", "DEMETER", "DIACONU", "ILEA", "POP", "BOER" };
		String prenume[] = {"DORU","GHEORGHE", "MARIAN","NICOLAE CONSTANTIN", "DOMINIC", "ADRIAN", "ROBERT", "LAURENTIU", "GELU","MIHAIL", "ATTILA"};
		Profesor profesori[] = new Profesor[nume.length];
		for (int i = 0; i < nume.length;i++) {
			profesori[i] = new Profesor(nume[i], prenume[i]);
		}
		return profesori;
	}
	
	public Set<Student> createRandomSetOfStudents() {
		int studentInscrisiLaCurs = minimumRequiredStudents + rand.nextInt(dataSetOfStudent.length - minimumRequiredStudents);
		Set<Student> setOfStudents = new HashSet<Student>();
		for (int i = 0; i < studentInscrisiLaCurs; i++) {
			int randomStudentIndex = rand.nextInt(dataSetOfStudent.length);
			setOfStudents.add(dataSetOfStudent[randomStudentIndex]);
		}
		return setOfStudents;
	}
	
	public Curs[] createCoursesData() {
		String curs[] = { "Teoria sistemelor", "Masurari electronice", "Dispozitive electronice", "Structuri de date", "Procesarea semnalelor", "Limba engleza", "Limbaje formale", "PCLP 1", "PCLP 2" };
		String descriere = "descriere curs";
		ArrayList<Curs> cursuri = new ArrayList<>();
		for (String numeCurs : curs) {
			Set<Student> studentsData = createRandomSetOfStudents();
			Profesor profesor = dataSetOfProfesor[rand.nextInt(dataSetOfProfesor.length)];
			Curs c = new Curs(numeCurs, descriere, profesor, studentsData);
			cursuri.add(c);
		}
		return cursuri.toArray(new Curs[cursuri.size()]);
	}
	
	public void gradeStudents() {
		for (Curs c: manager.cursuri) {
			for( Student s: c.studenti) {
				try {
					c.noteazaStudent(s, 1 + rand.nextInt(10));
				} catch (Exception e) {

				}
			}
		}
	}
}
