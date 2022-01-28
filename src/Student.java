import java.util.*;
public class Student implements Comparable {
	public String nume;
	public String prenume;
	public int grupa;

	public Student() { }
	Student(String nume, String prenume, int grupa) {
		this.nume = nume;
		this.prenume = prenume;
		this.grupa = grupa;
	}

	public Student(String nume, String prenume){
		this.nume = nume;
		this.prenume = prenume;
	}
	
	Student(ArrayList<String> properties) throws Exception {
		if ( properties.size() != 3 ) {
			throw new Exception("Invalid number of properties! The student cannot be created!");
		} else {
			this.nume = properties.get(0);
			this.prenume = properties.get(1);
			this.grupa = Integer.parseInt(properties.get(2));
		}
	}
	@Override
	public String toString() {
		return "Student [nume=" + nume + ", prenume=" + prenume + ", grupa=" + grupa + "]";
	}


	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return nume.equals(student.nume) && prenume.equals(student.prenume);
	}

	@Override
	public int hashCode() {
		return Objects.hash(nume, prenume);
	}

	@Override
	public int compareTo(Object o) {
		Student s = (Student)o;
		return this.nume.compareTo(s.nume);
	}
}
