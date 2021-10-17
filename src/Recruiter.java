public class Recruiter extends Employee {
    private double rating;

    public Recruiter() {
        rating = 5;
    }

    public double getRating() {
        return rating;
    }

    public int evaluate(Job job, User user) {
        double score = this.rating * user.getTotalScore();
        Request<Job, Consumer> request = new Request<>(job, user, this, score);
        this.rating += 0.1;
        Application.getInstance().getCompany(this.getCompanyName()).getManager().addRequest(request);
        return (int) score;
    }

    @Override
    public String toString() {
        return super.toString() + "Recruiter : " + rating + '\n';
    }
}
