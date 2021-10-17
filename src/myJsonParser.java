import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

class jsonObject {
    jsonEmployee[] employees;
    jsonRecruiter[] recruiters;
    jsonUser[] users;
    jsonRecruiter[] managers;

    public jsonObject() {
    }
}

class jsonResume {
    String name;
    String email;
    String phone;
    String date_of_birth;
    String genre;
    String[] languages;
    String[] languages_level;
    jsonEducation[] education;
}

class jsonEmployee extends jsonResume {
    int salary;
    jsonExperience[] experience;

    public jsonEmployee() {
    }
}

class jsonRecruiter extends jsonResume {
    int salary;
    jsonExperience[] experience;

    public jsonRecruiter() {
    }
}

class jsonUser extends jsonResume {
    String[] interested_companies;
    jsonExperience[] experience;

    public jsonUser() {
    }
}

class jsonEducation {
    String level;
    String name;
    String start_date;
    String end_date;
    float grade;

    public jsonEducation() {
    }
}

class jsonExperience {
    String company;
    String position;
    String department;
    String start_date;
    String end_date;

    public jsonExperience() {
    }
}

class jsonJobs {
    jsonJob[] jobs;

    public jsonJobs() {
    }
}

class jsonJob {
    String company;
    String department;
    String name;
    int positions;
    int salary;
    jsonConstraint[] constraints;

    public jsonJob() {
    }
}

class jsonConstraint {
    String type;
    Double min;
    Double max;

    public jsonConstraint() {
    }
}

class jsonGraph {
    jsonNode[] nodes;

    public jsonGraph() {
    }
}

class jsonNode {
    String name;
    String[] friends;

    public jsonNode() {
    }
}

public class myJsonParser {

    static Consumer.Resume.ResumeBuilder parseResume(jsonResume employee) {
        String[] splitDate = employee.date_of_birth.split("\\.");
        Date date = new Date(Integer.parseInt(splitDate[2]) - 1900, Integer.parseInt(splitDate[1]) - 1, Integer.parseInt(splitDate[0]));

        Information.SEX sex;
        if(employee.genre.equals("male"))
            sex = Information.SEX.MALE;
        else if(employee.genre.equals("female"))
            sex = Information.SEX.FEMALE;
        else
            sex = Information.SEX.OTHER;

        Consumer.Resume.ResumeBuilder resume = new Consumer.Resume.ResumeBuilder()
                .information(new Information(employee.name, employee.email, employee.phone, date, sex));

        for(int i = 0; i < employee.languages.length; i++) {
            switch (employee.languages_level[i]) {
                case "Beginner" -> resume.getInformation().addKnownLanguage(employee.languages[i], Information.LANGUAGE_LEVEL.BEGINNER);
                case "Experienced" -> resume.getInformation().addKnownLanguage(employee.languages[i], Information.LANGUAGE_LEVEL.EXPERIENCED);
                case "Advanced" -> resume.getInformation().addKnownLanguage(employee.languages[i], Information.LANGUAGE_LEVEL.ADVANCED);
            }
        }

        return resume;
    }

    static void parseEducation(jsonResume employee, Consumer.Resume.ResumeBuilder resume) {
        String[] splitDate;
        Date date;

        for(jsonEducation education : employee.education) {
            Education education1 = new Education();

            switch (education.level) {
                case "highschool" -> education1.setEducationLevel(Education.EDUCATION_LEVEL.HIGHSCHOOL);
                case "college" -> education1.setEducationLevel(Education.EDUCATION_LEVEL.BACHELOR);
                case "masters" -> education1.setEducationLevel(Education.EDUCATION_LEVEL.MASTER);
                case "phd" -> education1.setEducationLevel(Education.EDUCATION_LEVEL.PHD);
            }
            education1.setInstitutionName(education.name);

            splitDate = education.start_date.split("\\.");
            date = new Date(Integer.parseInt(splitDate[2]) - 1900, Integer.parseInt(splitDate[1]) - 1, Integer.parseInt(splitDate[0]));
            education1.setBegin(date);

            if(education.end_date == null)
                education1.setEnd(null);
            else {
                splitDate = education.end_date.split("\\.");
                date = new Date(Integer.parseInt(splitDate[2]) - 1900, Integer.parseInt(splitDate[1]) - 1, Integer.parseInt(splitDate[0]));
                education1.setEnd(date);
            }

            education1.setFinalMean(education.grade);

            resume.education(education1);
        }
    }

    static String[] parseExperience(Object object, Consumer.Resume.ResumeBuilder resume) {
        String[] splitDate;
        Date date;
        String[] result = new String[2];
        jsonExperience[] experiences;

        if(object instanceof jsonUser)
            experiences = ((jsonUser) object).experience;
        else if(object instanceof jsonRecruiter)
            experiences = ((jsonRecruiter) object).experience;
        else
            experiences = ((jsonEmployee) object).experience;

        for(jsonExperience experience : experiences) {
            Experience experience1 = new Experience();

            splitDate = experience.start_date.split("\\.");
            date = new Date(Integer.parseInt(splitDate[2]) - 1900, Integer.parseInt(splitDate[1]) - 1, Integer.parseInt(splitDate[0]));
            experience1.setBegin(date);

            if(experience.end_date == null) {
                if(object instanceof jsonEmployee)
                    result[0] = experience.department;
                result[1] = experience.company;
                experience1.setEnd(null);
            }
            else {
                splitDate = experience.end_date.split("\\.");
                date = new Date(Integer.parseInt(splitDate[2]) - 1900, Integer.parseInt(splitDate[1]) - 1, Integer.parseInt(splitDate[0]));
                experience1.setEnd(date);
            }

            experience1.setPosition(experience.position);

            experience1.setCompany(experience.company);

            resume.experience(experience1);
        }

        return result;
    }

    static Company companyCheck(String companyName) {
        Company company;
        if(Application.getInstance().getCompany(companyName) == null) {
            company = new Company();
            company.setName(companyName);
            Application.getInstance().add(company);
        }
        else
            company = Application.getInstance().getCompany(companyName);
        return company;
    }

    static Department departmentCheck(Company company, String departmentName) {
        Department myDept = null;
        for(Department department : company.getDepartments()) {
            if(departmentName.equals("IT") && department.getClass() == IT.class ||
                    departmentName.equals("Management") && department.getClass() == Management.class ||
                    departmentName.equals("Marketing") && department.getClass() == Marketing.class ||
                    departmentName.equals("Finance") && department.getClass() == Finance.class) {
                myDept = department;
                break;
            }
        }
        if(myDept == null) {
            myDept = DepartmentFactory.factory(departmentName);
            company.add(myDept);
        }
        return myDept;
    }

    static void parseConsumers(String path) throws FileNotFoundException, ResumeIncompleteException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        Gson gson = new Gson();
        jsonObject jsonObject = gson.fromJson(bufferedReader, jsonObject.class);

        for(jsonEmployee employee : jsonObject.employees) {

            Consumer.Resume.ResumeBuilder resume = parseResume(employee);

            parseEducation(employee, resume);

            String[] result = parseExperience(employee, resume);
            String departmentName = result[0];
            String companyName = result[1];

            Employee employee1 = new Employee(companyName, employee.salary);
            employee1.setResume(resume.build());

            Company company = companyCheck(companyName);

            Department department = departmentCheck(company, departmentName);

            department.add(employee1);
        }

        for(jsonRecruiter recruiter : jsonObject.recruiters) {

            Consumer.Resume.ResumeBuilder resume = parseResume(recruiter);

            parseEducation(recruiter, resume);

            String companyName = parseExperience(recruiter, resume)[1];
            Recruiter recruiter1 = new Recruiter();
            recruiter1.setCompanyName(companyName);
            recruiter1.setSalary(recruiter.salary);
            recruiter1.setResume(resume.build());

            Company company = companyCheck(companyName);

            Department department = departmentCheck(company, "IT");

            department.add(recruiter1);
            company.add(recruiter1);
        }

        for(jsonUser user : jsonObject.users) {
            Consumer.Resume.ResumeBuilder resume = parseResume(user);

            parseEducation(user, resume);

            parseExperience(user, resume);
            User user1 = new User();
            user1.setResume(resume.build());

            user1.setCompanies(new ArrayList<>(Arrays.asList(user.interested_companies)));
            Application.getInstance().add(user1);
        }

        for(jsonRecruiter manager : jsonObject.managers) {

            Consumer.Resume.ResumeBuilder resume = parseResume(manager);

            parseEducation(manager, resume);

            String companyName = parseExperience(manager, resume)[1];
            Manager manager1 = new Manager();
            manager1.setCompanyName(companyName);
            manager1.setSalary(manager.salary);
            manager1.setResume(resume.build());

            Company company = companyCheck(companyName);

            company.setManager(manager1);
        }

    }

    static void parseJobs(String path) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        Gson gson = new Gson();
        jsonJobs jsonJobs = gson.fromJson(bufferedReader, jsonJobs.class);

        for(jsonJob job : jsonJobs.jobs) {
            Job job1 = new Job(job.name, job.company, job.positions, job.salary);
            Constraint[] constraints = new Constraint[3];
            Constraint.CONSTRAINT_TYPE type = null;
            for(int i = 0; i < 3; i++) {
                switch (job.constraints[i].type) {
                    case "graduation" -> type = Constraint.CONSTRAINT_TYPE.GRADUATION_YEAR;
                    case "experience" -> type = Constraint.CONSTRAINT_TYPE.WORK_EXPERIENCE;
                    case "average" -> type = Constraint.CONSTRAINT_TYPE.MEAN_GPA;
                }
                constraints[i] = new Constraint(type, job.constraints[i].min, job.constraints[i].max);
            }
            job1.setConstraints(constraints);
            departmentCheck(companyCheck(job.company), job.department).add(job1);
            companyCheck(job.company).notifyAllObservers(job.company + ":A new job was posted!");
        }
    }

    static void parseFriends(String path) throws FileNotFoundException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(path));
        Gson gson = new Gson();
        jsonGraph graph = gson.fromJson(bufferedReader, jsonGraph.class);

        for(jsonNode node : graph.nodes) {
            Consumer current = Application.getInstance().getConsumer(node.name);
            for(String friendName : node.friends) {
                Consumer friend = Application.getInstance().getConsumer(friendName);
                friend.add(current);
                current.add(friend);
            }
        }
    }
}






