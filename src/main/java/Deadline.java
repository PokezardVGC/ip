public class Deadline extends Task {
    private final String deadline;

    public Deadline(String input, String deadline) {
        super(input, "");
        this.deadline = deadline;
    }

    public Deadline(String input, boolean done, String deadline) {
        super(input, done, "");
        this.deadline = deadline;
    }

    public Deadline markDone() {
        return new Deadline(this.getVal(), true, this.deadline);
    }

    public Deadline markUndone() {
        return new Deadline(this.getVal(), false, this.deadline);
    }

    @Override
    public String getTiming(){
        return this.deadline;
    }
}
