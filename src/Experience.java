import java.util.Date;
import java.util.Objects;

public class Experience implements Comparable<Experience> {
    private Date begin;
    private Date end;
    private String position;
    private String company;

    public Experience() {
    }

    public Experience(Date begin, Date end, String position, String company) throws InvalidDatesException {
        this.begin = begin;
        this.end = end;
        if(end != null && begin.after(end))
            throw new InvalidDatesException();
        this.position = position;
        this.company = company;
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

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    @Override
    public int compareTo(Experience o) {
        if(this.end == null || o.end == null || this.end.getTime() == o.end.getTime())
            return this.company.compareTo(o.company);
        else return - Long.compare(this.end.getTime(), o.end.getTime());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Experience that = (Experience) o;
        return getBegin().equals(that.getBegin()) &&
                Objects.equals(getEnd(), that.getEnd()) &&
                getPosition().equals(that.getPosition()) &&
                getCompany().equals(that.getCompany());
    }

}
