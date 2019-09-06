import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {

    public static Task convertTextToTask(String line) {
        //while (!inout.equals("bye")) {
        Task task = null;
        //System.out.println(line);
        String[] word = line.split("\\|", 4);
        /*for (String a : word) {
            System.out.println (a);
        }*/
        String time = word[2];
        if (word[0].equals("todo")) {
            try {
                task = new Todo(word[1]);
                return new Todo(word[1]);
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");

            }
        } else if (word[0].equals("deadline")) {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime formatDateTime = LocalDateTime.parse(time, formatter);
                task = new Deadline(word[1], formatDateTime.format(formatter));
                return new Deadline(word[1], formatDateTime.format(formatter));
            } catch (DateTimeParseException e) {
                System.out.println("Gone Fuck");
            }
        } else if (word[0].equals("event")){
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime formatDateTime = LocalDateTime.parse(time, formatter);
                task = new Event(word[1], formatDateTime.format(formatter));
                return new Event(word[1], formatDateTime.format(formatter));
            } catch (DateTimeParseException e) {
                System.out.println("Gone Fuck?");
            }
        }
        if (task != null && word[3].equals("true")) {
            task.Done();
        }
        return task;

    }

    public static String convertTaskToText(Task example) throws Exception {
        if (example instanceof Todo) {
            return "todo|" + example.description + "|as|" + example.isDone();
        } else if (example instanceof Deadline) {
            return "deadline|" + example.description + "|" + ((Deadline) example).getBy() + "|" + example.isDone();
        } else if (example instanceof Event) {
            return "event|" + example.description + "|" + ((Event) example).getAt() + "|" + example.isDone();
        }
        throw new UnknownException("Failed to convert to string!");
    }
}
