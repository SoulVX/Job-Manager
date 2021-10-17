public class Employee extends Consumer {
    private String companyName;
    private double salary;

    public Employee() {
        this.companyName = null;
        this.salary = 0;
    }

    public Employee(String companyName, double salary) {
        this.companyName = companyName;
        this.salary = salary;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return super.toString() + "Employee : " + companyName + " " + salary + '\n';
    }
}
