import java.util.ArrayList;

public abstract class Department {
    private final ArrayList<Employee> employees;
    private final ArrayList<Job> jobs;

    public Department() {
        employees = new ArrayList<>();
        jobs = new ArrayList<>();
    }

    public abstract double getTotalSalaryBudget();

    public ArrayList<Job> getJobs() {
        ArrayList<Job> openJobs = new ArrayList<>();
        for(Job job : this.jobs)
            if(job.isJobOpen())
                openJobs.add(job);
        return openJobs;
    }

    public void add(Employee employee) {
        this.employees.add(employee);
    }

    public void remove(Employee employee) {
        this.employees.remove(employee);
    }

    public void add(Job job) {
        this.jobs.add(job);
    }

    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    @Override
    public String toString() {
        return "Department : " + employees + " " + jobs + '\n';
    }

}
