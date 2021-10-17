import javax.swing.*;
import javax.swing.tree.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Enumeration;

public class ManagerPanel extends JPanel {

    public ManagerPanel(String companyName) {
        initComponents(companyName);
    }

    class myJTree extends JTree {
        public myJTree(TreeModel defaultTreeModel) {
            super(defaultTreeModel);
        }

        @Override
        public String getToolTipText(MouseEvent event) {
            if (getRowForLocation(event.getX(), event.getY()) == -1)
                return null;
            TreePath curPath = getPathForLocation(event.getX(), event.getY());
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) curPath.getLastPathComponent();
            for(Job job : Application.getInstance().getCompany((String) ((DefaultMutableTreeNode) ((DefaultMutableTreeNode) this.getModel().getRoot())
                    .getChildAt(0)).getUserObject()).getJobs()) {
                if((node.getUserObject()).equals(job.getJobName()))
                    return job.getNoPositions() + " job(s) left";
            }

           return null;
        }
    }

    DefaultMutableTreeNode getNodeWithName(DefaultMutableTreeNode root, String name) {
        for(int i = 0; i < root.getChildCount(); i++)
            if ((((DefaultMutableTreeNode) root.getChildAt(i)).getUserObject()).equals(name))
                return (DefaultMutableTreeNode) root.getChildAt(i);
        return null;
    }

    JTree initTree(String companyName) {
        Company motherCompany = Application.getInstance().getCompany(companyName);
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Manager");
        DefaultMutableTreeNode company = new DefaultMutableTreeNode(companyName);
        DefaultMutableTreeNode requests = new DefaultMutableTreeNode("Requests");

        root.add(company);
        root.add(requests);

        for(Department department : motherCompany.getDepartments()) {
            DefaultMutableTreeNode departmentNode = new DefaultMutableTreeNode(department.getClass().getName());
            company.add(departmentNode);
            for(Employee employee : department.getEmployees())
                departmentNode.add(new DefaultMutableTreeNode(employee.getResume().getInformation().getName()));
        }

        for(Request<Job, Consumer> request : motherCompany.getManager().getRequests()) {
            if (getNodeWithName(requests, request.getKey().getDepartment().getClass().getName()) == null) {
                requests.add(new DefaultMutableTreeNode(request.getKey().getDepartment().getClass().getName()));
            }
        }

        for(Request<Job, Consumer> request : motherCompany.getManager().getRequests()) {
            DefaultMutableTreeNode departamentNode = getNodeWithName(requests, request.getKey().getDepartment().getClass().getName());
            if(getNodeWithName(departamentNode, request.getKey().getJobName()) == null) {
                DefaultMutableTreeNode jobNode = new DefaultMutableTreeNode(request.getKey().getJobName());
                departamentNode.add(jobNode);
                jobNode.add(new DefaultMutableTreeNode(request.getValue1().getResume().getInformation().getName() + " - " + String.format("%.2f", request.getScore())));
            } else {
                DefaultMutableTreeNode jobNode = getNodeWithName(departamentNode, request.getKey().getJobName());
                jobNode.add(new DefaultMutableTreeNode(request.getValue1().getResume().getInformation().getName() + " - " + String.format("%.2f", request.getScore())));
            }
        }

        JTree tree = new myJTree(new DefaultTreeModel(root));
        tree.setEditable(true);
        tree.setCellRenderer(new AdminPanel.myTreeRenderer());
        ToolTipManager.sharedInstance().registerComponent(tree);
        TreePopup popup = new TreePopup(tree, motherCompany);
        tree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if(SwingUtilities.isRightMouseButton(e)
                        && tree.getPathForLocation(e.getX(), e.getY()) != null && tree.getPathForLocation(e.getX(), e.getY()).getPathCount() > 2) {
                    TreePath selPath = tree.getPathForLocation(e.getX(), e.getY());
                    for (Job job : motherCompany.getJobs()) {
                        if (job.getJobName().equals(selPath.getPathComponent(selPath.getPathCount()-2).toString())) {
                            tree.setSelectionPath(selPath);
                            popup.show(e.getComponent(), e.getX(), e.getY());
                            break;
                        }
                    }
                }
            }
        });

        return tree;

    }

    private void initComponents(String companyName) {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTree1 = initTree(companyName);

        jScrollPane1.setViewportView(jTree1);

        this.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "logoff");
        this.getActionMap().put("logoff", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame top = (JFrame) SwingUtilities.getWindowAncestor(ManagerPanel.this);
                top.remove(ManagerPanel.this);
                top.add(new LoginPanel());
                top.setTitle("Login");
                top.pack();
                top.setVisible(true);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 536, Short.MAX_VALUE)
        );

    }

    void removeNodeFromReq(JTree tree, ArrayList<DefaultMutableTreeNode> sameNameNodes, Company company) {

        for(DefaultMutableTreeNode node : sameNameNodes) {
            //daca e singurul request la jobul respectiv, se sterge toata categoria jobului
            if(company.getJob((String) ((DefaultMutableTreeNode) node.getPath()[node.getPath().length - 2]).getUserObject()) == null
                    || node.getPath()[node.getPath().length - 2].getChildCount() == 1
                    || company.getJob((String) ((DefaultMutableTreeNode) node.getPath()[node.getPath().length - 2]).getUserObject()).getNoPositions() == 0)
                ((DefaultTreeModel) tree.getModel()).removeNodeFromParent((MutableTreeNode) node.getPath()[node.getPath().length - 2]);
            else
                ((DefaultTreeModel) tree.getModel()).removeNodeFromParent(node);
        }

        //se elimina departamentele fara requesturi
        Enumeration<TreeNode> enumeration = ((DefaultMutableTreeNode) ((DefaultMutableTreeNode) tree.getModel().getRoot()).getChildAt(1)).children();
        sameNameNodes.clear();
        while(enumeration.hasMoreElements()) {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
            if(node.isLeaf())
                sameNameNodes.add(node);
        }
        for(DefaultMutableTreeNode node : sameNameNodes)
            ((DefaultTreeModel) tree.getModel()).removeNodeFromParent(node);
    }

    class TreePopup extends JPopupMenu {
        public TreePopup(JTree tree, Company company) {
            JMenuItem hireItem = new JMenuItem("Hire");
            hireItem.addActionListener(e -> {
                String[] leaf = ((String) ((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject()).split(" ");
                String name = leaf[0] + " " + leaf[1];
                for(User user : Application.getInstance().getUsers())
                    if(user.getResume().getInformation().getName().equals(name)) {
                        String department = (String) ((DefaultMutableTreeNode) tree.getSelectionPath().getPathComponent(tree.getSelectionPath().getPathCount() - 3)).getUserObject();
                        //se angajeaza utilizatorul
                        company.getManager().hire(user, company.getJob((String) ((DefaultMutableTreeNode) tree.getSelectionPath().getPathComponent(tree.getSelectionPath().getPathCount() - 2)).getUserObject()));

                        // se elimina utilizatorul de peste tot din Requests
                        DefaultTreeModel model = (DefaultTreeModel) tree.getModel();
                        Enumeration<TreeNode> enumeration = ((DefaultMutableTreeNode) model.getRoot()).preorderEnumeration();
                        ArrayList<DefaultMutableTreeNode> sameNameNodes = new ArrayList<>();
                        while(enumeration.hasMoreElements()) {
                            DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
                            leaf = ((String) node.getUserObject()).split(" ");
                            if(leaf.length > 1 && (leaf[0] + " " + leaf[1]).equals(user.getResume().getInformation().getName())) {
                                sameNameNodes.add(node);
                            }
                        }
                        removeNodeFromReq(tree, sameNameNodes, company);

                        //se pune noul angajat in departamentul cerut
                        enumeration = ((DefaultMutableTreeNode) ((DefaultMutableTreeNode) tree.getModel().getRoot()).getChildAt(0)).children();
                        while(enumeration.hasMoreElements()) {
                            DefaultMutableTreeNode node = (DefaultMutableTreeNode) enumeration.nextElement();
                            if (node.getUserObject().equals(department)) {
                                ((DefaultTreeModel) tree.getModel()).insertNodeInto(new DefaultMutableTreeNode(user.getResume().getInformation().getName()), node, node.getChildCount());
                                break;
                            }
                        }

                        break;
                    }
            });
            JMenuItem removeItem = new JMenuItem("Remove");
            removeItem.addActionListener(e -> {
                String[] leaf = ((String) ((DefaultMutableTreeNode) tree.getLastSelectedPathComponent()).getUserObject()).split(" ");
                String name = leaf[0] + " " + leaf[1];
                for(User user : Application.getInstance().getUsers())
                    if(user.getResume().getInformation().getName().equals(name)) {
                        String jobName = (String) ((DefaultMutableTreeNode) tree.getSelectionPath().getPathComponent(tree.getSelectionPath().getPathCount() - 2)).getUserObject();
                        company.getManager().getRequests().removeIf(req -> req.getKey().getJobName().equals(jobName)
                                && req.getValue1().getResume().getInformation().getName().equals(name));
                        ArrayList<DefaultMutableTreeNode> sameNameNodes = new ArrayList<>();
                        sameNameNodes.add((DefaultMutableTreeNode) tree.getLastSelectedPathComponent());
                        removeNodeFromReq(tree, sameNameNodes, company);
                        company.notifyAllObservers(user.getResume().getInformation().getName() + ":Your request for "
                                + jobName + " at " + company.getName() + " was removed by a manager.");
                    }
            });
            add(hireItem);
            add(removeItem);
        }
    }

    private JScrollPane jScrollPane1;
    private JTree jTree1;
}
