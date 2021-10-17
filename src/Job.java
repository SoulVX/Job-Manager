import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class Job {
    private String jobName;
    private String companyName;
    private boolean isJobOpen;
    private Constraint[] constraints;
    private ArrayList<User> candidates;
    private int noPositions;
    private int salary;

    public Job(String jobName, String companyName, int noPositions, int salary) {
        this.jobName = jobName;
        this.companyName = companyName;
        this.noPositions = noPositions;
        this.salary = salary;
        this.isJobOpen = true;
        constraints = new Constraint[3];
        candidates = new ArrayList<>();

    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public boolean isJobOpen() {
        return isJobOpen;
    }

    public void setJobOpen(boolean jobOpen) {
        isJobOpen = jobOpen;
    }

    public Constraint[] getConstraints() {
        return constraints;
    }

    public void setConstraints(Constraint[] constraints) {
        this.constraints = constraints;
    }

    public ArrayList<User> getCandidates() {
        return candidates;
    }

    public void setCandidates(ArrayList<User> candidates) {
        this.candidates = candidates;
    }

    public int getNoPositions() {
        return noPositions;
    }

    public void setNoPositions(int noPositions) {
        this.noPositions = noPositions;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public boolean meetsRequirments(User user) {
        for (Constraint constraint : constraints)
            switch (constraint.getConstraintType()) {
                case GRADUATION_YEAR:
                    Double d;
                    if(user.getGraduationYear() == null)
                        d = null;
                    else
                        d = user.getGraduationYear().doubleValue();
                    if(!isInRange(d,
                            constraint.getLowerBound(), constraint.getUpperBound()))
                        return false;
                    break;

                case WORK_EXPERIENCE:
                    if(!isInRange((double) user.getTotalExperience(),
                            constraint.getLowerBound(), constraint.getUpperBound()))
                        return false;
                    break;

                case MEAN_GPA:
                    if(!isInRange(user.meanGPA(),
                            constraint.getLowerBound(), constraint.getUpperBound()))
                        return false;
            }
        return true;
    }

    public void apply(User user) {
        Company company = Application.getInstance().getCompany(this.getCompanyName());
        company.addObserver(user);
        if(meetsRequirments(user)) {
            company.notifyAllObservers(user.getResume().getInformation().getName() + ":You applied successfully for " + this.getJobName() + " at " + this.getCompanyName());
            getCandidates().add(user);
            company.getRecruiter(user).evaluate(this, user);
        } else
            company.notifyAllObservers(user.getResume().getInformation().getName() + ":You dont meet the requirements for " + this.getJobName() + " at " + this.getCompanyName());
        user.getCompanies().remove(companyName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Job job = (Job) o;
        return getJobName().equals(job.getJobName()) &&
                getCompanyName().equals(job.getCompanyName());
    }

    public Department getDepartment() {
        for(Department department : Application.getInstance().getCompany(companyName).getDepartments())
            for(Job job : department.getJobs())
                if(job.equals(this))
                    return department;
        return null;
    }

    public boolean isInRange(Double x, Double min, Double max) {
        if (x == null)
            return min == null && max == null;
        if(min == null)
            return max == null || x <= max;
        if(max == null)
            return x >= min;
        return x >= min && x <= max;
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobName='" + jobName + '\'' +
                ", companyName='" + companyName + '\'' +
                ", isJobOpen=" + isJobOpen +
                ", constraints=" + Arrays.toString(constraints) +
                ", candidates=" + candidates +
                ", noPositions=" + noPositions +
                ", salary=" + salary +
                '}';
    }
}
