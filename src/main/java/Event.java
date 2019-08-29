public class Event extends Task{
    public String getAt() {
        return at;
    }

    private String at;
    //private String time;

    public Event(String description, String at) {
        super(description);
        this.at = at;
        //this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at: " + at + ")";
    }
}
