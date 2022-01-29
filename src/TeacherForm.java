import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TeacherForm {

    public JPanel getPanel1() {
        return panel1;
    }

    public TeacherForm(JFrame admin){
        this.admin = admin;
        showAllCoursesWithButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] list = new String[Application.getInstance().managerCursuri.getCursuri().size()];
                for (String s : Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().keySet()) {
                    String prenume = Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().get(s);
                    list = Application.getInstance().managerCursuri.showCoursesByProfesor(new Profesor(s,prenume));
                }
                list1.setListData(list);
            }
        });
        showAllStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(list1.getSelectedIndex()!= -1){
                    try {
                        Curs c = Application.getInstance().managerCursuri.search(new Curs((String) list1.getSelectedValue()));
                        String[] listaidk = new String[c.studenti.size()];
                        int nr=0;
                        String nota;
                        for(Student s: c.studenti){
                            if(c.nota.get(s) == null)
                            {
                                 nota = " ";
                            }
                            else
                            {
                                nota= c.nota.get(s).toString();
                            }
                            listaidk[nr++] = s.nume + " " + s.prenume + " nota: " + nota;
                        }
                        list2.setListData(listaidk);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        gradeAStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(list2.getSelectedIndex()!= -1){
                    if(textField1.getText()!=null){
                        Integer nota = Integer.parseInt(textField1.getText());
                        String s = (String) list2.getSelectedValue();
                        try{
                            Curs c = Application.getInstance().managerCursuri.search(new Curs((String) list1.getSelectedValue()));
                            int index = Application.getInstance().managerCursuri.getCursuri().indexOf(c);
                            Application.getInstance().managerCursuri.getCursuri().get(index).noteazaStudent(new Student(s.split(" ")[0],s.split(" ")[1]),nota);
                            try {
                                Curs curs = Application.getInstance().managerCursuri.search(new Curs((String) list1.getSelectedValue()));
                                String[] listaidk = new String[c.studenti.size()];
                                int nr=0;
                                String nota1;
                                for(Student s1: curs.studenti){
                                    if(curs.nota.get(s1) == null)
                                    {
                                        nota1 = " ";
                                    }
                                    else
                                    {
                                        nota1= curs.nota.get(s1).toString();
                                    }
                                    listaidk[nr++] = s1.nume + " " + s1.prenume + " nota: " + nota1;
                                }
                                list2.setListData(listaidk);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                        catch (Exception f)
                        {
                            f.getMessage();
                        }
                    }
                }
            }
        });

        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel1.setVisible(false);
                admin.setContentPane(new LoginForm(admin).getMainPanel());
            }
        });
    }

    private JPanel panel1;
    private JButton showAllCoursesWithButton;
    private JButton gradeAStudentButton;
    private JButton logOutButton;
    private JList list1;
    private JList list2;
    private JButton showAllStudentsButton;
    private JTextField textField1;
    private JFrame admin;
}
