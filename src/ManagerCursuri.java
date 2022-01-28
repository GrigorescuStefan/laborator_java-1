import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ManagerCursuri {
	// Organizarea claselor se face deobicei dupa regula: Membri, Metode private, Constructori, metode publice
	List<Curs> cursuri;
	
	// Metoda cauta un curs in lista de cursuri si arunca exceptie in cazul in care nu-l gaseste. Exceptia trebuie tratata in exterior
	public Curs search(Curs unCurs) throws Exception {
		int i = cursuri.indexOf(unCurs);
		if ( i != -1 ) {
			return cursuri.get(i);
		}
		else {
			throw new Exception("Cursul " + unCurs + " nu se se regaseste in programa scolara");
		}
	}
	
	public ManagerCursuri() {
		this.cursuri = new ArrayList<Curs>();
	}
	
	public void AddCurs(Curs c) {
		this.cursuri.add(c);
	}
	
	public void RemoveCurs(Curs c) throws Exception {
		if (!this.cursuri.remove(c)) {
			throw new Exception("Cursrul " + c + " nu poate fi sters pentru ca nu se regaseste in programa scolara");
		}
	}
	
	public void EditCurs(Curs c, Curs cursNou) throws Exception {
		// Caut cursul in lista de cursuri
		int i = cursuri.indexOf(c);
		// verific daca cursul exista
		if ( i != -1) {
			cursuri.set(i, cursNou);
		} else {
			throw new Exception("Cursul " + c + " nu se regaseste in programa scolara");
		}
	}
	
	public void reportStudentsOf(Curs unCurs) throws Exception {
		Curs c = this.search(unCurs);
		c.AfisareStudenti();
	}
	
	public void reportAllCourses() {
		for( Curs c: cursuri) {
			System.out.println( c.nume + " " + c.descriere);
			try {
				// this.reportStudentsOf(c);
			} catch (Exception e) {

			}
		}
	}

	public void setCursuri(Curs[] curs){
		cursuri = new ArrayList<>(Arrays.asList(curs));
	}

	public boolean searchStudent(Student student){
		for(Curs c:cursuri){
			if(c.cautaStudent(student))
				return true;
		}
		return false;
	}

	public boolean searchProfesor(Profesor profesor){
		for(Curs c:cursuri){
			if(c.cautaProfesor(profesor))
				return true;
		}
		return false;
	}

	public String[] showCoursesByProfesor(Profesor profesor){
		String[] lista = new String[cursuri.size()];
		int nr=0;
		for(Curs c:cursuri){
			if(c.profu.nume.equals(profesor.nume) && c.profu.prenume.equals(profesor.prenume)) {
				lista[nr++]=c.nume;
			}
		}
		return lista;
	}

	public List<Curs> getCursuri(){
		return cursuri;
	}


	
	public void reportAllStudentsGrades() {
		for( Curs c: cursuri) {
			c.AfisareNumeCurs();
			for ( Student s: c.studenti) {
				String gradeAsString = c.nota.get(s) != null ? c.nota.get(s).toString() : " nu a fost notat";
				System.out.println( s.nume + " " + s.prenume + " are nota: " + gradeAsString);
			}
		}
	}
	
	public void reportGradesOf(Curs unCurs) throws Exception {
		Curs c = this.search(unCurs);
		System.out.println("Media studentilor la cursul " + c.nume + " este:" + c.MediaStudenti());	
	}
	
	public void reportAverageGradesOf(Profesor unProf) {
		float sum = 0;
		int count = 0;
		for( Curs c : cursuri) {
			if ( c.profu == unProf ) {
				sum += c.MediaStudenti();
				count += 1;
			}
		}
		float average = count == 0 ? 0 : sum / (float)count; 
		System.out.println("Mediat notelor date de profesorul: " + unProf.formatForDisplay() + " este: " + average );
	}
	
}
