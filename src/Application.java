import java.beans.ExceptionListener;
import java.beans.XMLDecoder;
import java.beans.XMLEncoder;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
public class Application {
    private static Application single_instance = null;
    private List<User> userList = new ArrayList<>();

    public User currentUser = null;

    static Application getInstance() {
        if ( single_instance == null) {
            single_instance = new Application();
        }
        return  single_instance;
    }

    public HardcodatDataManager manager = new HardcodatDataManager();
    public ManagerCursuri managerCursuri = new ManagerCursuri();

    private Application() {
         /* HardcodatDataManager dataManager = new HardcodatDataManager();
        Random r = new Random();
        var studenti = dataManager.dataSetOfStudent;
        var profesori = dataManager.dataSetOfProfesor;
        this.userList.add(new User("aaa", "aaa", new StudentStrategy( studenti[r.nextInt(studenti.length)])));
        this.userList.add(new User("bbb", "aaa", new TeacherStrategy( profesori[r.nextInt(profesori.length)])));
        this.userList.add(new User("ccc", "ccc", new StudentStrategy( studenti[r.nextInt(studenti.length)])));
        this.userList.add(new User("ddd", "ddd", new TeacherStrategy( profesori[r.nextInt(profesori.length)])));
        this.userList.add(new User("eee", "eee", new StudentStrategy( studenti[r.nextInt(studenti.length)])));
        try {
            FileOutputStream fos = new FileOutputStream("users.xml");
            XMLEncoder encoder = new XMLEncoder(fos);
            encoder.setExceptionListener(new ExceptionListener() {
                @Override
                public void exceptionThrown(Exception e) {
                    System.out.println("Exception:" + e.toString());
                }
            });
            encoder.writeObject(userList);
            encoder.close();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } */
        this.initUsers();
    }

    public void salvareUser(String username, String password, MenuStrategy meniu){
        this.initUsers();
        userList.add(new User(username,password,meniu));
        try(FileOutputStream fisier = new FileOutputStream("users.xml")) {
            XMLEncoder encoder = new XMLEncoder(fisier);
            encoder.writeObject(this.userList);
            encoder.close();
            fisier.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initUsers() {
        try (FileInputStream fis = new FileInputStream("users.xml")) {
            XMLDecoder decoder = new XMLDecoder(fis);
            this.userList = (ArrayList<User>)decoder.readObject();
            decoder.close();
            fis.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void login(User user) throws Exception {
        int index = userList.indexOf(user);
        if ( index != -1 ) {
            Application.getInstance().currentUser = userList.get(index);
            Application.getInstance().managerCursuri.setCursuri(manager.createCoursesData());
        } else {
            throw new Exception("Username sau parola este gresita!");
        }
    }

}
