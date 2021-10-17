import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class ProfilePanel extends javax.swing.JPanel {

    private JButton companyButton;
    private JButton applyButton;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JList<String> companies;
    private JList<String> notifications;
    private JScrollPane jScrollPane1;
    private JScrollPane jScrollPane2;
    private JButton notificationButton;

    public ProfilePanel(String name, boolean buttons) {
        initComponents(name, buttons);
    }

    private void initComponents(String name, boolean buttons) {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        companies = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        notifications = new javax.swing.JList<>();
        companyButton = new javax.swing.JButton();
        notificationButton = new javax.swing.JButton();
        applyButton = new javax.swing.JButton();

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "logoff");
        this.getActionMap().put("logoff", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame top = (JFrame) SwingUtilities.getWindowAncestor(ProfilePanel.this);
                top.remove(ProfilePanel.this);
                top.add(new LoginPanel());
                top.setTitle("Login");
                top.pack();
                top.setVisible(true);
            }
        });

        jLabel1.setText("Companies");

        jScrollPane1.setViewportView(companies);

        jLabel2.setText("Notifications");

        jScrollPane2.setViewportView(notifications);

        companyButton.setText("Add");
        companyButton.setEnabled(buttons);

        notificationButton.setText("Remove notifications");

        applyButton.setText("Apply");
        applyButton.setEnabled(buttons);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(87, 87, 87)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel2)
                                .addGap(222, 222, 222))
                        .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(122, 122, 122)
                                                .addComponent(applyButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(companyButton, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane2)
                                                .addContainerGap())
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(233, 233, 233)
                                                .addComponent(notificationButton)
                                                .addGap(103, 103, 103)
                                                .addGap(44, 44, 44))))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel1)
                                        .addComponent(jLabel2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 352, Short.MAX_VALUE)
                                        .addComponent(jScrollPane2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(companyButton)
                                        .addComponent(notificationButton)
                                        .addComponent(applyButton))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );


        User user = null;
        ArrayList<User> allUsers = new ArrayList<>();
        allUsers.addAll(Application.getInstance().getUsers());
        allUsers.addAll(Application.getInstance().getExUsers());
        for(User u : allUsers)
            if(u.getResume().getInformation().getName().equals(name)) {
                user = u;
                break;
            }

        companies.setModel(new DefaultListModel<>());
        ((DefaultListModel<String>) companies.getModel()).addAll(user.getCompanies());

        notifications.setModel(new DefaultListModel<>());
        ((DefaultListModel<String>) notifications.getModel()).addAll(user.getNotifications());

        User finalUser = user;
        applyButton.addActionListener(e -> {
            if(companies.getSelectedValue() == null)
                return;
            if(Application.getInstance().getCompany(companies.getSelectedValue()) == null) {
                ((DefaultListModel<String>) companies.getModel()).removeElement(companies.getSelectedValue());
                JOptionPane.showMessageDialog(this, "This company does not exist!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            for(Job job : Application.getInstance().getCompany(companies.getSelectedValue()).getJobs())
                job.apply(finalUser);
            ((DefaultListModel<String>) companies.getModel()).removeElement(companies.getSelectedValue());
            ((DefaultListModel<String>) notifications.getModel()).removeAllElements();
            ((DefaultListModel<String>) notifications.getModel()).addAll(finalUser.getNotifications());
        });

        notificationButton.addActionListener(e -> {
            finalUser.getNotifications().clear();
            ((DefaultListModel<String>) notifications.getModel()).removeAllElements();
        });

        companyButton.addActionListener(e -> {
            String s = (String) JOptionPane.showInputDialog(this, "Company name", "Adding a new company ...", JOptionPane.PLAIN_MESSAGE, null, null, null);
            ((DefaultListModel<String>) companies.getModel()).addElement(s);
        });


    }

}