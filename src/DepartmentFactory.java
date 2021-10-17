public class DepartmentFactory {
    public static Department factory(String name) {
        switch (name) {
            case "IT" -> { return new IT(); }
            case "Finance" -> { return new Finance(); }
            case "Management" -> { return new Management(); }
            case "Marketing" -> { return new Marketing(); }
        }
        return null;
    }
}

class IT extends Department {
    @Override
    public double getTotalSalaryBudget() {
        double s = 0;
        for(Employee employee : this.getEmployees())
            s += employee.getSalary();
        return s;
    }
}

class Finance extends Department {
    @Override
    public double getTotalSalaryBudget() {
        double s = 0;
        for(Employee employee : this.getEmployees()) {
            for(Experience experience : employee.getResume().getExperiences()) {
                if (experience.getEnd() == null) {
                    if((System.currentTimeMillis() - experience.getBegin().getTime()) / 86400000 / 365 < 1 )
                        s += employee.getSalary() * 90 / 100;
                    else
                        s += employee.getSalary() * 84 / 100;
                    break;
                }
            }
        }
        return s;
    }
}

class Management extends Department {
    @Override
    public double getTotalSalaryBudget() {
        double s = 0;
        for(Employee employee : this.getEmployees())
            s += employee.getSalary() * 84 / 100;
        return s;
    }
}

class Marketing extends Department {
    @Override
    public double getTotalSalaryBudget() {
        double s = 0;
        for(Employee employee : this.getEmployees()) {
            if(employee.getSalary() < 3000)
                s += employee.getSalary();
            else if(employee.getSalary() <= 5000)
                s += employee.getSalary() * 84 / 100;
            else
                s += employee.getSalary() * 90 / 100;
        }
        return s;
    }
}

