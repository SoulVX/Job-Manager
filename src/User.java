import java.lang.reflect.Array;
import java.util.ArrayList;

interface Observer {
    void update(String notification);
}

public class User extends Consumer implements Observer{
    private ArrayList<String> companies;
    ArrayList<String> notifications;

    public User() {
        companies = new ArrayList<>();
        notifications = new ArrayList<>();
    }

    public void setCompanies(ArrayList<String> companies) {
        this.companies = companies;
    }

    public ArrayList<String> getCompanies() {
        return companies;
    }

    public Employee convert() {
        Employee employee = new Employee();
        employee.setAcquaintances(this.getAcquaintances());
        employee.setResume(this.getResume());
        return employee;
    }

    public int getTotalExperience() {
        int totalExp = 0;
        for (Experience experience: this.getResume().getExperiences())
            if(experience.getEnd() != null)
                totalExp += (experience.getEnd().getTime() - experience.getBegin().getTime()) / 86400000;
        int years = totalExp / 365;
        if(totalExp % 365 >= 92)
            years++;
        return years;
    }

    public Double getTotalScore() {
        return getTotalExperience() * 1.5 + this.meanGPA();
    }

    @Override
    public String toString() {
        return "User{" +
                "companies=" + companies +
                "} " + super.toString();
    }

    @Override
    public void update(String notification) {
        String[] split = notification.split(":");
        if(this.getCompanies().contains(split[0]) || this.getResume().getInformation().getName().equals(split[0]))
            notifications.add(split[1]);
    }

    public ArrayList<String> getNotifications() {
        return notifications;
    }
}
