import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterForm {
    private JPanel panel2;
    private JTextField textField1;
    private JPasswordField passwordField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton registerButton;
    private JFrame admin;

    public JPanel getPanel2() {
        return panel2;
    }
    public RegisterForm(JFrame admin){
        this.admin = admin;
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textField1.getText()!="" && passwordField1.getPassword()!=null && textField2.getText()!="" && textField3.getText()!="") {
                    if(LoginForm.studenti.indexOf(new Student(textField2.getText(), textField3.getText()))!= -1) {
                        String userName = textField1.getText();
                        String passWord = String.valueOf(passwordField1.getPassword());
                        MenuStrategy meniu = new StudentStrategy(new Student(textField2.getText(),textField3.getText()));
                        Application.getInstance().salvareUser(userName,passWord,meniu);
                    }
                    else if (LoginForm.profesori.indexOf(new Profesor(textField2.getText(), textField3.getText()))!= -1){
                        String userName = textField1.getText();
                        String passWord = String.valueOf(passwordField1.getPassword());
                        MenuStrategy meniu = new TeacherStrategy(new Profesor(textField2.getText(),textField3.getText()));
                        Application.getInstance().salvareUser(userName,passWord,meniu);
                    }
                }
                admin.setContentPane(new LoginForm(admin).getMainPanel());
            }
        });
    }

}
