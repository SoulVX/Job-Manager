import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class LoginPanel extends javax.swing.JPanel {

    public LoginPanel() {
        initComponents();
    }

    void login() {
        ArrayList<User> allUsers = new ArrayList<>();
        allUsers.addAll(Application.getInstance().getUsers());
        allUsers.addAll(Application.getInstance().getExUsers());
        String username = emailField.getText();
        for (User u : allUsers)
            if (username.equals(u.getResume().getInformation().getEmail()) && passField.getText().equals("user")) {
                JFrame top = (JFrame) SwingUtilities.getWindowAncestor(this);
                top.remove(this);
                if (Application.getInstance().getExUsers().contains(u))
                    top.add(new ProfilePanel(u.getResume().getInformation().getName(), false));
                else
                    top.add(new ProfilePanel(u.getResume().getInformation().getName(), true));
                top.setTitle(u.getResume().getInformation().getName());
                top.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\user.png"));
                top.pack();
                return;
            }
        for (Company company : Application.getInstance().getCompanies())
            if (username.equals((company.getManager().getResume().getInformation().getEmail()))
                    && passField.getText().equals("manager")) {
                JFrame top = (JFrame) SwingUtilities.getWindowAncestor(this);
                top.remove(this);
                ManagerPanel managerPanel = new ManagerPanel(company.getName());
                top.add(managerPanel);
                top.setTitle(company.getManager().getResume().getInformation().getName());
                top.pack();
                return;
            }
        if (username.equals("admin") && passField.getText().equals("admin")) {
            JFrame top = (JFrame) SwingUtilities.getWindowAncestor(this);
            top.remove(this);
            AdminPanel adminPanel = new AdminPanel();
            top.add(adminPanel);
            top.setTitle("Admin");
            top.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\admin.png"));
            top.pack();
            return;
        }
        JOptionPane.showMessageDialog(this, "Incorrect email or password!", "Error", JOptionPane.ERROR_MESSAGE);
    }

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        emailField = new javax.swing.JTextField();
        passField = new javax.swing.JPasswordField();
        jButton1 = new javax.swing.JButton();

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0), "enter");
        this.getActionMap().put("enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                login();
            }
        });

        jLabel1.setText("Email");

        jLabel2.setText("Password");

        jButton1.setText("Login");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(44, 44, 44)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(emailField, javax.swing.GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                        .addComponent(passField))
                                .addContainerGap(69, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton1)
                                .addGap(36, 36, 36))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(35, 35, 35)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(emailField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel2)
                                        .addComponent(passField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(29, 29, 29)
                                .addComponent(jButton1)
                                .addContainerGap(22, Short.MAX_VALUE))
        );

        jButton1.addActionListener(e -> login());
    }

    private javax.swing.JTextField emailField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPasswordField passField;

}