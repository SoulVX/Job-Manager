import javax.swing.*;
import java.awt.*;
import java.io.FileNotFoundException;
import java.util.Date;

public class Test {
    public static void main(String[] args) throws FileNotFoundException, ResumeIncompleteException {

        String path = System.getProperty("user.dir") + "\\input\\consumers.json";
        String path2 = System.getProperty("user.dir") + "\\input\\jobs.json";
        String path3 = System.getProperty("user.dir") + "\\input\\friends.json";
        myJsonParser.parseConsumers(path);
        myJsonParser.parseJobs(path2);
        myJsonParser.parseFriends(path3);

        /*for(User user : Application.getInstance().getUsers())
            for(Job job : Application.getInstance().getJobs(user.getCompanies()))
                job.apply(user);

        for(Company company : Application.getInstance().getCompanies())
            for(Job job : company.getJobs())
                company.getManager().process(job);*/

        JFrame frame = new JFrame("Login");
        frame.setIconImage(Toolkit.getDefaultToolkit().getImage(System.getProperty("user.dir") + "\\res\\login.png"));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setFocusable(true);
        frame.add(new LoginPanel());
        frame.pack();
        frame.setVisible(true);


    }
}
