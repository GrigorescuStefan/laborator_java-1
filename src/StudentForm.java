import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentForm {

    public JPanel getPanel2() {
        return panel2;
    }

    public StudentForm(JFrame admin){
        this.admin = admin;
        showMyCoursesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String[] list = new String[Application.getInstance().managerCursuri.getCursuri().size()];
                for (String s : Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().keySet()){
                    String prenume = Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().get(s);
                    list = Application.getInstance().managerCursuri.showCoursesByStudent(new Student(s,prenume));
                }
                list1.setListData(list);
            }
        });
        averageGradeButton.addActionListener(new ActionListener() {
            String nume = String.valueOf(Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().keySet());
            String prenume = Application.getInstance().currentUser.menuStrategy.getAccountHolderInformation().get(nume);
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField1.getText()=="")
                    textField1.setText(String.valueOf(Application.getInstance().managerCursuri.reportAverageGradesOfStudent(new Student(nume,prenume))));
            }
        });
        logOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panel2.setVisible(false);
                admin.setContentPane(new LoginForm(admin).getMainPanel());
            }
        });
    }

    private JPanel panel2;
    private JButton showMyCoursesButton;
    private JList list1;
    private JButton averageGradeButton;
    private JTextField textField1;
    private JButton logOutButton;
    private JFrame admin;
}
