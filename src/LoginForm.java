import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class LoginForm {
    public LoginForm(JFrame owner, ArrayList<Profesor> profesori, ArrayList<Student> studenti) {
        this.studenti = studenti;
        this.profesori = profesori;
        this.owner = owner;
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( e.getSource() == btnLogin) {
                    System.out.println("Am apasat butonul de login");
                    try {
                        Application.getInstance().login(new User(txtUsername.getText(), new String(txtPassword.getPassword())));

                        JOptionPane.showMessageDialog(null, "Login successfully!");
                        mainPanel.setVisible(false);
                        if(Application.getInstance().currentUser.menuStrategy.getAccountType().toString().compareTo("TEACHER") == 0)
                            owner.setContentPane(new TeacherForm(owner).getPanel1());
                        else
                            owner.setContentPane(new StudentForm(owner).getPanel2());

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                owner.setContentPane(new RegisterForm(owner).getPanel2());
            }
        });

    }

    public LoginForm(JFrame owner){
        this.owner = owner;
        btnLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ( e.getSource() == btnLogin) {
                    System.out.println("Am apasat butonul de login");
                    try {
                        Application.getInstance().login(new User(txtUsername.getText(), new String(txtPassword.getPassword())));

                        JOptionPane.showMessageDialog(null, "Login successfully!");
                        mainPanel.setVisible(false);
                        if(Application.getInstance().currentUser.menuStrategy.getAccountType().toString().compareTo("TEACHER") == 0)
                            owner.setContentPane(new TeacherForm(owner).getPanel1());
                        else
                            owner.setContentPane(new StudentForm(owner).getPanel2());

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, ex.getMessage());
                    }
                }
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainPanel.setVisible(false);
                owner.setContentPane(new RegisterForm(owner).getPanel2());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private JPanel mainPanel;
    private JLabel lblUsername;
    private JTextField txtUsername;
    private JPasswordField txtPassword;
    private JButton btnLogin;
    private JButton registerButton;
    private JFrame owner;
    public static ArrayList<Student> studenti;
    public static ArrayList<Profesor> profesori;
}
