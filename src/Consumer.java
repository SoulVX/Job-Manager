import com.sun.source.tree.Tree;

import java.util.*;

public abstract class Consumer {

    private Resume resume;
    private ArrayList<Consumer> acquaintances;

    public Consumer() {
        this.resume = null;
        this.acquaintances = new ArrayList<>();
    }

    public Resume getResume() {
        return resume;
    }

    public void setResume(Resume resume) {
        this.resume = resume;
    }

    public ArrayList<Consumer> getAcquaintances() {
        return acquaintances;
    }

    public void setAcquaintances(ArrayList<Consumer> list) {
        this.acquaintances = list;
    }

    public void add(Consumer consumer) {
        this.acquaintances.add(consumer);
    }

    public void remove(Consumer consumer) {
        this.acquaintances.remove(consumer);
    }

    public Integer getGraduationYear() {
        if(getResume().getEducations().size() == 0)
            return null;

        for (Education education: getResume().getEducations())
            if(education.getEducationLevel() == Education.EDUCATION_LEVEL.BACHELOR) {
                if(education.getEnd() == null)
                    return null;
                return education.getEnd().getYear() + 1900;
            }
        return null;
    }

    public Double meanGPA() {
        if(getResume().getEducations().size() == 0)
            return (double) 0;

        double s = 0;
        for (Education education: getResume().getEducations())
            s = s + education.getFinalMean();
        return s / getResume().getEducations().size();
    }

    public int getDegreeInFriendship(Consumer consumer) {
        Consumer c;
        ArrayList<Consumer> visited = new ArrayList<>();
        LinkedList<Consumer> queue = new LinkedList<>();
        Consumer lastInDegree = null;
        visited.add(this);
        queue.add(this);
        int degree = 0;
        while (queue.size() != 0) {
            c = queue.poll();
            if(c.equals(consumer))
                return degree;
            for (Consumer next : c.getAcquaintances())
                if (!visited.contains(next)) {
                    visited.add(next);
                    queue.add(next);
                }
            if(degree == 0 || c == lastInDegree) {
                lastInDegree = queue.getLast();
                degree++;
            }
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Consumer consumer = (Consumer) o;
        return getResume().equals(consumer.getResume());
    }

    static class Resume {
        private final Information information;
        private final TreeSet<Education> educations;
        private final TreeSet<Experience> experiences;

        private Resume(ResumeBuilder resumeBuilder) {
            this.information = resumeBuilder.information;
            this.educations = resumeBuilder.educations;
            this.experiences = resumeBuilder.experiences;
        }

        public Information getInformation() {
            return information;
        }

        public TreeSet<Education> getEducations() {
            return educations;
        }

        public TreeSet<Experience> getExperiences() { return experiences; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Resume resume = (Resume) o;
            return getInformation().equals(resume.getInformation());
        }

        public static class ResumeBuilder {
            private Information information;
            private final TreeSet<Education> educations;
            private final TreeSet<Experience> experiences;

            public ResumeBuilder() {
                educations = new TreeSet<>(Education::compareTo);
                experiences = new TreeSet<>(Experience::compareTo);
                information = null;
            }

            public ResumeBuilder information(Information information) {
                this.information = information;
                return this;
            }

            public ResumeBuilder education(Education education) {
                this.educations.add(education);
                return this;
            }

            public ResumeBuilder experience(Experience experience) {
                this.experiences.add(experience);
                return this;
            }

            public Resume build() throws ResumeIncompleteException {
                if(information == null)
                    throw new ResumeIncompleteException();
                return new Resume(this);
            }

            public Information getInformation() {
                return information;
            }
        }

    }

    @Override
    public String toString() {
        return "Consumer : " + getResume().getInformation().getName() + '\n';
    }
}
