import javax.management.Notification;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ListIterator;

public class Manager extends Employee {
    private final ArrayList<Request<Job, Consumer>> requests;

    public Manager() {
        requests = new ArrayList<>();
    }

    public ArrayList<Request<Job, Consumer>> getRequests() {
        return requests;
    }

    public void addRequest(Request<Job, Consumer> request) {
        requests.add(request);
    }

    public void hire(User user, Job job) {
        ArrayList<Request<Job, Consumer>> requestsWithSameJob = new ArrayList<>();
        for(Request<Job, Consumer> request : this.requests)
            if(request.getKey().equals(job))
                requestsWithSameJob.add(request);
        if(Application.getInstance().getUsers().contains(user)) {
            Application.getInstance().getUsers().remove(user);
            Application.getInstance().getExUsers().add(user);
            Employee newEmployee = user.convert();
            newEmployee.setSalary(job.getSalary());
            newEmployee.setCompanyName(job.getCompanyName());
            Application.getInstance().getCompany(job.getCompanyName()).add(newEmployee, job.getDepartment());

            for(Company company : Application.getInstance().getCompanies())
                company.getManager().getRequests().removeIf(request -> request.getValue1().equals(user));

            Application.getInstance().getCompany(newEmployee.getCompanyName()).notifyAllObservers(user.getResume().getInformation().getName()
                    + ":You got hired at " + getCompanyName() + " as " + job.getJobName());
            Application.getInstance().getCompany(newEmployee.getCompanyName()).removeObserver(user);

            job.setNoPositions(job.getNoPositions() - 1);
        }
        if(job.getNoPositions() == 0) {
            job.setJobOpen(false);
            Application.getInstance().getCompany(this.getCompanyName()).notifyAllObservers(job.getCompanyName() + ":The job " + job.getJobName()  + " was closed!");
        }
        getRequests().removeAll(requestsWithSameJob);
    }

    public void process(Job job) {
        ArrayList<Request<Job, Consumer>> top = new ArrayList<>();
        for(Request<Job, Consumer> request : this.requests)
            if(request.getKey().equals(job))
                top.add(request);
        top.sort(Comparator.comparingDouble(Request::getScore));
        for(Request<Job, Consumer> request : top) {
            if(job.getNoPositions() > 0)
                hire((User) request.getValue1(), job);
        }
        getRequests().removeAll(top);
    }

    @Override
    public String toString() {
        return "Manager{} " + super.toString();
    }
}
