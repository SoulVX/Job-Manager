import java.util.ArrayList;

abstract class Subject {
    ArrayList<User> observers = new ArrayList<>();
    void addObserver(User user) {
        if(!observers.contains(user))
        observers.add(user);
    }
    void removeObserver(User c) {
        observers.remove(c);
    }
    void notifyAllObservers(String notification) {
        for(User user : observers)
            user.update(notification);
    }
}

public class Company extends Subject{
    private String name;
    private Manager manager;
    private final ArrayList<Department> departments;
    private final ArrayList<Recruiter> recruiters;

    public Company() {
        departments = new ArrayList<>();
        recruiters = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Manager getManager() {
        return manager;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public ArrayList<Department> getDepartments() {
        return departments;
    }

    public void add(Department department) {
        this.departments.add(department);
    }

    public ArrayList<Recruiter> getRecruiters() {
        return recruiters;
    }

    public void add(Recruiter recruiter) {
        this.recruiters.add(recruiter);
    }

    public void add(Employee employee, Department department) {
        department.add(employee);
    }

    public void remove(Employee employee) {
        for(Department department : departments)
            department.getEmployees().remove(employee);
    }

    public void remove(Department department) {
        for(Department dep : departments) {
            if (dep.equals(department)) {
                for (Employee employee : dep.getEmployees())
                    recruiters.remove((Recruiter) employee);
                departments.remove(dep);
                break;
            }
        }
    }

    public void remove(Recruiter recruiter) {
        this.recruiters.remove(recruiter);
    }

    public void move(Department source, Department destination) {
        for(Employee employee : source.getEmployees())
            this.add(employee, destination);
        this.remove(source);
    }

    public void move(Employee employee, Department newDepartment) {
        this.remove(employee);
        this.add(employee, newDepartment);
    }

    public boolean contains(Department department) {
        return departments.contains(department);
    }

    public boolean contains(Employee employee) {
        for(Department department : departments)
            if(department.getEmployees().contains(employee))
                return true;
        return false;
    }

    public boolean contains(Recruiter recruiter) {
        return recruiters.contains(recruiter);
    }

    public Recruiter getRecruiter(User user) {
        Recruiter goodRecruiter = null;
        for(Recruiter recruiter : recruiters)
            if(goodRecruiter == null
                    || user.getDegreeInFriendship(recruiter) > user.getDegreeInFriendship(goodRecruiter)
                    || (user.getDegreeInFriendship(recruiter) == user.getDegreeInFriendship(goodRecruiter)
                            && recruiter.getRating() > goodRecruiter.getRating()))
             goodRecruiter = recruiter;
        return goodRecruiter;
    }

    public ArrayList<Job> getJobs() {
        ArrayList<Job> arrayList = new ArrayList<>();
        for(Department department : departments)
            for(Job job : department.getJobs())
                if(job.isJobOpen())
                    arrayList.add(job);
        return arrayList;
    }

    public Department getDepartment(String department) {
        for(Department department1 : getDepartments())
            if(department1.getClass().getName().equals(department))
                return department1;
        return null;
    }

    public Job getJob(String name) {
        for(Job job : getJobs())
            if(job.getJobName().equals(name))
                return job;
        return null;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return getName().equals(company.getName());
    }

}
