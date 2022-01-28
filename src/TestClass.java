import javax.swing.*;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;
import java.io.File;

enum LOAD_TYPE {
	HARDCODAT, KEYBOARD, FILE
}

enum DISPLAY_TYPE  {
	CONSOLA, FISIER, GUI
}

public class TestClass {
	public static void main(String[] args) {
		HardcodatDataManager dataManager = new HardcodatDataManager();
		ArrayList<Student> listaStudenti = new ArrayList<>(Arrays.asList(dataManager.createStudentsData()));
		ArrayList<Profesor> listaProfesori = new ArrayList<>(Arrays.asList(dataManager.createProfesorData()));
		JFrame frame = new JFrame("Graphic user interface");
		LoginForm loginForm = new LoginForm(frame,listaProfesori,listaStudenti);
		frame.setContentPane(loginForm.getMainPanel());
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
		/* Scanner sc = new Scanner(System.in);
		System.out.println("Username = ");
		var username = sc.next();
		System.out.println("Password = ");
		var password = sc.next();

		try {
			Application.getInstance().login(new User(username, password));
			System.out.println(Application.getInstance().currentUser);
			System.out.println(Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation());
			System.out.println(Application.getInstance().currentUser.menuStrategy.getAccountType());
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}*/
	}
}
