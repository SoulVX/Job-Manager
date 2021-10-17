public class Constraint {
    public enum CONSTRAINT_TYPE {GRADUATION_YEAR, WORK_EXPERIENCE, MEAN_GPA}

    private final CONSTRAINT_TYPE constraintType;
    private final Double lowerBound;
    private final Double upperBound;

    public Constraint(CONSTRAINT_TYPE constraintType, Double lowerBound, Double upperBound) {
        this.constraintType = constraintType;
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    public CONSTRAINT_TYPE getConstraintType() {
        return constraintType;
    }

    public Double getLowerBound() {
        return lowerBound;
    }

    public Double getUpperBound() {
        return upperBound;
    }

    @Override
    public String toString() {
        return "Constraint{" +
                "constraintType=" + constraintType +
                ", lowerBound=" + lowerBound +
                ", upperBound=" + upperBound +
                '}';
    }
}
