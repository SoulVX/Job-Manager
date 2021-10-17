import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.Map;

public class AdminPanel extends javax.swing.JPanel {

    private javax.swing.JLabel birthLabel;
    private javax.swing.JLabel educ_begin;
    private javax.swing.JLabel educ_end;
    private javax.swing.JLabel educ_grade;
    private javax.swing.JLabel educ_inst;
    private javax.swing.JLabel educ_lv;
    private javax.swing.JComboBox<String> educationDropdown;
    private javax.swing.JLabel emailLabel;
    private javax.swing.JLabel exp_begin;
    private javax.swing.JLabel exp_comp;
    private javax.swing.JLabel exp_end;
    private javax.swing.JLabel exp_pos;
    private javax.swing.JComboBox<String> experienceDropdown;
    private javax.swing.JList<String> friendList;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTree jTree1;
    private javax.swing.JTable languages;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JLabel phoneLabel;
    private javax.swing.JLabel sexLabel;

        String formatDate(Date date) {
        if(date == null)
            return "";
        return (date.getDate()) + "." + (date.getMonth() + 1) + "." + (date.getYear() + 1900);
    }

        public AdminPanel() {
            initComponents();
        }

        private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTree1 = initTree();
        jPanel1 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        languages = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        educationDropdown = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        educ_begin = new javax.swing.JLabel();
        educ_end = new javax.swing.JLabel();
        educ_inst = new javax.swing.JLabel();
        educ_lv = new javax.swing.JLabel();
        educ_grade = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        experienceDropdown = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        exp_begin = new javax.swing.JLabel();
        exp_end = new javax.swing.JLabel();
        exp_pos = new javax.swing.JLabel();
        exp_comp = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        friendList = new javax.swing.JList<>();
        nameLabel = new javax.swing.JLabel();
        emailLabel = new javax.swing.JLabel();
        phoneLabel = new javax.swing.JLabel();
        birthLabel = new javax.swing.JLabel();
        sexLabel = new javax.swing.JLabel();

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "logoff");
        this.getActionMap().put("logoff", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame top = (JFrame) SwingUtilities.getWindowAncestor(AdminPanel.this);
                top.remove(AdminPanel.this);
                top.add(new LoginPanel());
                top.setTitle("Login");
                top.pack();
                top.setVisible(true);
            }
        });

        setName("mainPanel"); // NOI18N

        jTree1.setName("tree"); // NOI18N
        jScrollPane2.setViewportView(jTree1);

        jPanel1.setName("infoPanel"); // NOI18N
        jPanel1.setLayout(new java.awt.CardLayout());

        jPanel6.setBackground(new java.awt.Color(153, 255, 51));

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 532, Short.MAX_VALUE)
        );
        jPanel6Layout.setVerticalGroup(
                jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 505, Short.MAX_VALUE)
        );

        jPanel1.add(jPanel6, "card3");

        jLabel1.setText("Name");

        jLabel2.setText("Email");

        jLabel3.setText("Phone");

        jLabel4.setText("Birth date");

        jLabel5.setText("Sex");

        languages.setModel(new javax.swing.table.DefaultTableModel(
                new Object [][] {
                },
                new String [] {
                        "Language", "Level"
                }
        ) {
            Class[] types = new Class [] {
                    java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        languages.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(languages);

        jLabel6.setText("Educations :");

        jLabel7.setText("Begin date");

        jLabel8.setText("End date");

        jLabel9.setText("Institution");

        jLabel11.setText("Grade");

        jLabel17.setText("Experiences :");

        jLabel20.setText("Position");

        jLabel21.setText("Company");

        jLabel26.setText("Friend list");

        jScrollPane3.setViewportView(friendList);
        friendList.setModel(new DefaultListModel<>());

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel7)
                                                        .addComponent(jLabel8)
                                                        .addComponent(jLabel9)
                                                        .addComponent(jLabel11)
                                                        .addComponent(jLabel17)
                                                        .addComponent(jLabel20)
                                                        .addComponent(jLabel21))
                                                .addGap(18, 18, 18)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(experienceDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(educationDropdown, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(educ_begin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(educ_end, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(educ_inst, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                                .addComponent(educ_lv, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addGap(0, 10, Short.MAX_VALUE))
                                                        .addComponent(educ_grade, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(exp_begin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(exp_end, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(exp_pos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(exp_comp, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel5Layout.createSequentialGroup()
                                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(jLabel1)
                                                                .addComponent(jLabel2)
                                                                .addComponent(jLabel3)
                                                                .addComponent(jLabel4)
                                                                .addComponent(jLabel5))
                                                        .addGap(32, 32, 32)
                                                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                                .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(emailLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(phoneLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(birthLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                                .addComponent(sexLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel26)
                                                .addGap(56, 56, 56))))
        );
        jPanel5Layout.setVerticalGroup(
                jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                                                .addComponent(jLabel26)
                                                .addGap(18, 18, 18)
                                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(17, 17, 17))
                                        .addGroup(jPanel5Layout.createSequentialGroup()
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel1)
                                                        .addComponent(nameLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel2)
                                                        .addComponent(emailLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel3)
                                                        .addComponent(phoneLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel4)
                                                        .addComponent(birthLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                        .addComponent(jLabel5)
                                                        .addComponent(sexLabel))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)))
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(educationDropdown)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel7)
                                        .addComponent(educ_begin))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel8)
                                        .addComponent(educ_end))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel9)
                                        .addComponent(educ_inst))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(educ_lv)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel11)
                                        .addComponent(educ_grade))
                                .addGap(32, 32, 32)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17)
                                        .addComponent(experienceDropdown, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exp_begin)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(exp_end)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel20)
                                        .addComponent(exp_pos))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel21)
                                        .addComponent(exp_comp))
                                .addContainerGap(65, Short.MAX_VALUE))
        );

        jLabel6.getAccessibleContext().setAccessibleName("");

        jPanel1.add(jPanel5, "consumerCard");
        ((CardLayout) jPanel1.getLayout()).show(jPanel1, "consumerCard");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 532, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(104, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane2)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }

        class TreePopup extends JPopupMenu {
            public TreePopup(JTree tree) {
                JMenuItem item = new JMenuItem("Calculate total salary budget");
                item.addActionListener(e -> JOptionPane.showMessageDialog(tree, "The total budget for this department is " + Application.getInstance()
                        .getCompany((String) ((DefaultMutableTreeNode) tree.getSelectionPath().getPathComponent(2)).getUserObject())
                        .getDepartment((String) ((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject()).getTotalSalaryBudget(),
                        "Budget info", JOptionPane.INFORMATION_MESSAGE));
                add(item);
            }
        }

        static class myTreeRenderer extends DefaultTreeCellRenderer {
        static Icon users, employees, company, company_2, app, departments, it, management, marketing,
                finance, jobs, requests, manager, recruiter;

        public myTreeRenderer() {
            manager = new ImageIcon(System.getProperty("user.dir") + "\\res\\manager.png");
            recruiter = new ImageIcon(System.getProperty("user.dir") + "\\res\\recruiter.png");
            users = new ImageIcon(System.getProperty("user.dir") + "\\res\\users.png");
            employees = new ImageIcon(System.getProperty("user.dir") + "\\res\\employees.png");
            company = new ImageIcon(System.getProperty("user.dir") + "\\res\\company.jpg");
            company_2 = new ImageIcon(System.getProperty("user.dir") + "\\res\\company_2.png");
            app = new ImageIcon(System.getProperty("user.dir") + "\\res\\app.png");
            departments = new ImageIcon(System.getProperty("user.dir") + "\\res\\departments.png");
            it = new ImageIcon(System.getProperty("user.dir") + "\\res\\it.png");
            management = new ImageIcon(System.getProperty("user.dir") + "\\res\\management.png");
            marketing = new ImageIcon(System.getProperty("user.dir") + "\\res\\marketing.jpg");
            finance = new ImageIcon(System.getProperty("user.dir") + "\\res\\finance.png");
            jobs = new ImageIcon(System.getProperty("user.dir") + "\\res\\job.png");
            requests = new ImageIcon(System.getProperty("user.dir") + "\\res\\request.png");
            setLeafIcon(null);
            setClosedIcon(null);
            setOpenIcon(null);
        }

        public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded,
                                                      boolean leaf, int row, boolean hasFocus) {
            super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
            String name = (String) (((DefaultMutableTreeNode) value).getUserObject());
            for(Company company : Application.getInstance().getCompanies()) {
                if (company.getManager().getResume().getInformation().getName().equals(name)) {
                    setIcon(manager);
                    return this;
                }
                if(company.getName().equals(name)) {
                    setIcon(company_2);
                    return this;
                }
            }
            switch (name) {
                case "Recruiters" -> setIcon(recruiter);
                case "Users" -> setIcon(users);
                case "Employees" -> setIcon(employees);
                case "Companies" -> setIcon(company);
                case " " -> setIcon(app);
                case "Departments" -> setIcon(departments);
                case "IT" -> setIcon(it);
                case "Management" -> setIcon(management);
                case "Marketing" -> setIcon(marketing);
                case "Finance" -> setIcon(finance);
                case "Jobs" -> setIcon(jobs);
                case "Manager" -> setIcon(manager);
                case "Requests" -> setIcon(requests);
            }
            return this;
        }
    }

        private JTree initTree() {
            DefaultMutableTreeNode app = new DefaultMutableTreeNode(" ");
            DefaultMutableTreeNode users = new DefaultMutableTreeNode("Users");
            DefaultMutableTreeNode companies = new DefaultMutableTreeNode("Companies");
            app.add(users);
            app.add(companies);
            for(User user : Application.getInstance().getUsers())
                users.add(new DefaultMutableTreeNode(user.getResume().getInformation().getName()));
            for(Company company : Application.getInstance().getCompanies()) {
                DefaultMutableTreeNode companyNode = new DefaultMutableTreeNode(company.getName());
                companies.add(companyNode);
                companyNode.add(new DefaultMutableTreeNode(company.getManager().getResume().getInformation().getName()));
                DefaultMutableTreeNode recruiters = new DefaultMutableTreeNode("Recruiters");
                DefaultMutableTreeNode departments = new DefaultMutableTreeNode("Departments");
                companyNode.add(recruiters);
                companyNode.add(departments);
                // se face topul recruiterilor
                ArrayList<Recruiter> sortedRec = company.getRecruiters();
                sortedRec.sort(Comparator.comparingDouble(Recruiter::getRating).reversed());
                for(Recruiter recruiter : sortedRec)
                    recruiters.add(new DefaultMutableTreeNode(recruiter.getResume().getInformation().getName()
                            + " - " + String.format("%.2f", recruiter.getRating())));
                for(Department department : company.getDepartments()) {
                    DefaultMutableTreeNode departmentNode = new DefaultMutableTreeNode(department.getClass().getName());
                    departments.add(departmentNode);
                    DefaultMutableTreeNode employees = new DefaultMutableTreeNode("Employees");
                    DefaultMutableTreeNode jobs;
                    if(department.getJobs().size() == 0)
                        jobs = new DefaultMutableTreeNode("No jobs available");
                    else
                        jobs = new DefaultMutableTreeNode("Jobs");
                    departmentNode.add(employees);
                    departmentNode.add(jobs);

                    for(Employee employee : department.getEmployees())
                        employees.add(new DefaultMutableTreeNode(employee.getResume().getInformation().getName()));
                    for(Job job : department.getJobs())
                        jobs.add(new DefaultMutableTreeNode(job.getJobName()));
                }
            }

            JTree tree = new JTree(app);
            tree.setCellRenderer(new myTreeRenderer());
            tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
            tree.addTreeSelectionListener(e -> {
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();
                if (node == null)
                    return;
                if( Application.getInstance().getConsumer((String) node.getUserObject()) != null) {
                    Consumer consumer = Application.getInstance().getConsumer((String) node.getUserObject());
                    nameLabel.setText(consumer.getResume().getInformation().getName());
                    emailLabel.setText(consumer.getResume().getInformation().getEmail());
                    phoneLabel.setText(consumer.getResume().getInformation().getPhoneNo());
                    birthLabel.setText(formatDate(consumer.getResume().getInformation().getBirthDate()));
                    sexLabel.setText(String.valueOf(consumer.getResume().getInformation().getSex().toString().charAt(0)));

                    ((DefaultTableModel) languages.getModel()).setRowCount(0);
                    Object[] row = new Object[2];
                    for(Map.Entry<String, Information.LANGUAGE_LEVEL> entry:
                            consumer.getResume().getInformation().getKnownLanguages().entrySet()) {
                        row[0] = entry.getKey();
                        row[1] = entry.getValue().toString();
                        ((DefaultTableModel) languages.getModel()).addRow(row);
                    }
                    languages.setDefaultEditor(Object.class, null);

                    ((DefaultComboBoxModel<String>) educationDropdown.getModel()).removeAllElements();
                    for(Education education : consumer.getResume().getEducations())
                        educationDropdown.addItem(education.getEducationLevel().toString());
                    educ_begin.setText("");
                    educ_end.setText("");
                    educ_inst.setText("");
                    educ_grade.setText("");
                    if(educationDropdown.getActionListeners().length == 1)
                        educationDropdown.removeActionListener(educationDropdown.getActionListeners()[0]);
                    educationDropdown.addActionListener(e1 -> {
                        JComboBox<String> mybox = (JComboBox<String>) e1.getSource();
                        String current = (String) mybox.getSelectedItem();
                        for(Education education : consumer.getResume().getEducations())
                            if(education.getEducationLevel().toString().equals(current)) {
                                //System.out.print(consumer);
                                educ_begin.setText(formatDate(education.getBegin()));
                                if(education.getEnd() == null)
                                    educ_end.setText("-");
                                else
                                    educ_end.setText(formatDate(education.getEnd()));
                                educ_inst.setText(education.getInstitutionName());
                                educ_grade.setText(String.format("%.2f", education.getFinalMean()));
                                break;
                            }
                    });

                    ((DefaultComboBoxModel<String>) experienceDropdown.getModel()).removeAllElements();
                    for(Experience experience : consumer.getResume().getExperiences())
                        experienceDropdown.addItem(formatDate(experience.getBegin()) + " - " + formatDate(experience.getEnd()));
                    exp_pos.setText("");
                    exp_comp.setText("");
                    if(experienceDropdown.getActionListeners().length == 1)
                        experienceDropdown.removeActionListener(experienceDropdown.getActionListeners()[0]);
                    experienceDropdown.addActionListener(e1 -> {
                        JComboBox<String> mybox = (JComboBox<String>) e1.getSource();
                        if(mybox.getSelectedItem() == null)
                            return;
                        String current = ((String) mybox.getSelectedItem()).split(" ")[0];
                        for(Experience experience : consumer.getResume().getExperiences())
                            if(experience.getBegin().getDate() == Integer.parseInt(current.split("\\.")[0])
                                    && experience.getBegin().getMonth() + 1 == Integer.parseInt(current.split("\\.")[1])
                                    && experience.getBegin().getYear() + 1900 == Integer.parseInt(current.split("\\.")[2])) {
                                exp_pos.setText(experience.getPosition());
                                exp_comp.setText(experience.getCompany());
                                break;
                            }
                    });

                    ((DefaultListModel<String>) friendList.getModel()).clear();
                    for(Consumer consumer1 : consumer.getAcquaintances())
                        ((DefaultListModel<String>) friendList.getModel()).addElement(consumer1.getResume().getInformation().getName());
                }
            });

            TreePopup popup = new TreePopup(tree);
            tree.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    super.mouseClicked(e);
                    if(SwingUtilities.isRightMouseButton(e)
                            && tree.getPathForLocation(e.getX(), e.getY()) != null) {
                        TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                        if(selPath.getLastPathComponent().toString().equals("IT")
                                || selPath.getLastPathComponent().toString().equals("Management")
                                || selPath.getLastPathComponent().toString().equals("Marketing")
                                || selPath.getLastPathComponent().toString().equals("Finance")) {
                            //daca se da click dreapta pe un departament
                            tree.setSelectionPath(selPath);
                            popup.show(e.getComponent(), e.getX(), e.getY());
                        }
                    }
                }
            });

            return tree;
        }

}
