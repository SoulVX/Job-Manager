import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

public class Education implements Comparable<Education> {
    public enum EDUCATION_LEVEL { HIGHSCHOOL, BACHELOR, MASTER, PHD }

    private Date begin;
    private Date end;
    private String institutionName;
    private EDUCATION_LEVEL educationLevel;
    private double finalMean;

    public Education() {
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getInstitutionName() {
        return institutionName;
    }

    public void setInstitutionName(String institutionName) {
        this.institutionName = institutionName;
    }

    public EDUCATION_LEVEL getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EDUCATION_LEVEL educationLevel) {
        this.educationLevel = educationLevel;
    }

    public double getFinalMean() {
        return finalMean;
    }

    public void setFinalMean(float finalMean) {
        this.finalMean = finalMean;
    }

    @Override
    public int compareTo(Education o) {
        if(this.end == null || o.end == null)
            return Long.compare(this.begin.getTime(), o.begin.getTime());
        else if(this.end.getTime() != o.end.getTime())
            return - Long.compare(this.end.getTime(), o.end.getTime());
        else return - Double.compare(this.finalMean, o.finalMean);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Education education = (Education) o;
        return Double.compare(education.getFinalMean(), getFinalMean()) == 0 &&
                getBegin().equals(education.getBegin()) &&
                Objects.equals(getEnd(), education.getEnd()) &&
                getInstitutionName().equals(education.getInstitutionName()) &&
                getEducationLevel().equals(education.getEducationLevel());
    }

}
