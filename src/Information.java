import java.util.Date;
import java.util.HashMap;
import java.util.Objects;

public class Information {
    public enum LANGUAGE_LEVEL {BEGINNER, ADVANCED, EXPERIENCED}
    public enum SEX {MALE, FEMALE, OTHER}

    private String name;
    private String email;
    private String phoneNo;
    private Date birthDate;
    private SEX sex;
    private final HashMap<String, LANGUAGE_LEVEL> knownLanguages;

    public Information(String name, String email, String phoneNo, Date birthDate, SEX sex) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
        this.birthDate = birthDate;
        this.sex = sex;
        knownLanguages = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public SEX getSex() {
        return sex;
    }

    public HashMap<String, LANGUAGE_LEVEL> getKnownLanguages() {
        return knownLanguages;
    }

    public void addKnownLanguage(String language, LANGUAGE_LEVEL level) {
        knownLanguages.put(language, level);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Information that = (Information) o;
        return getName().equals(that.getName()) &&
                getEmail().equals(that.getEmail()) &&
                getPhoneNo().equals(that.getPhoneNo()) &&
                getBirthDate().equals(that.getBirthDate()) &&
                getSex().equals(that.getSex()) &&
                getKnownLanguages().equals(that.getKnownLanguages());
    }

}
