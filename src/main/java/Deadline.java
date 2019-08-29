import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Deadline extends Task {
    public String getBy() {
        return by;
    }

    private String by;
    //private String time;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
        //this.time = time;
    }



    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }
}