import java.util.ArrayList;
import java.util.List;

public class Application {
    private final ArrayList<Company> companies;
    private final ArrayList<User> users;
    private static Application instance;

    private final ArrayList<User> exUsers;

    private Application() {
        companies = new ArrayList<>();
        users = new ArrayList<>();
        exUsers = new ArrayList<>();
    }

    public static Application getInstance() {
        if(instance == null)
            instance = new Application();
        return instance;
    }

    public ArrayList<Company> getCompanies() {
        return this.companies;
    }

    public Company getCompany(String name) {
        for(Company company : this.companies)
            if(company.getName().equals(name))
                return company;
        return null;
    }

    public void add(Company company) {
        this.companies.add(company);
    }

    public void add(User user) {
        this.users.add(user);
    }

    public boolean remove(Company company) {
        return this.companies.remove(company);
    }

    public boolean remove(User user) {
        return this.users.remove(user);
    }

    public ArrayList<Job> getJobs(List<String> companies) {
        ArrayList<Job> jobs = new ArrayList<>();
        for(String company: companies)
            jobs.addAll(getCompany(company).getJobs());
        return jobs;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public Consumer getConsumer(String name) {
        for(User user : getUsers())
            if(user.getResume().getInformation().getName().equals(name))
                return user;
        for(Company company : getCompanies()) {
            if(company.getManager().getResume().getInformation().getName().equals(name))
                return company.getManager();
            for (Department department : company.getDepartments())
                for(Employee employee : department.getEmployees())
                    if(employee.getResume().getInformation().getName().equals(name))
                        return employee;

        }
        return null;
    }

    public ArrayList<User> getExUsers() {
        return exUsers;
    }
}
