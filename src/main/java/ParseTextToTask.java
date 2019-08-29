import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ParseTextToTask  {

    public static Task Converter(String line) {
        String inout = line;
        //while (!inout.equals("bye")) {
        Task task;
        //System.out.println(inout);
        String[] word = inout.split(" ", 2);
        if (word[0].equals("todo")) {
            try {
                return new Todo(word[1]);
                        /*System.out.println("Got it. I've added this task:");
                        yettodo.add(task);
                        System.out.println(task);
                        System.out.println("Now you have " + yettodo.size() + ((yettodo.size() > 1) ? " tasks" : " task") + " in the list");*/
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! The description of a todo cannot be empty.");

            }
        } else if (word[0].equals("deadline")) {
            try {

                String[] holder = word[1].split(" /by ", 2);
                //String[] deadlineTime = holder[1].split(" ", 2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime formatDateTime = LocalDateTime.parse(holder[1], formatter);
                task = new Deadline(holder[0], formatDateTime.format(formatter));
                return new Deadline(holder[0], formatDateTime.format(formatter));
                        /*System.out.println("Got it. I've added this task:");
                        yettodo.add(task);
                        System.out.println(task);
                        System.out.println("Now you have " + yettodo.size() + ((yettodo.size() > 1) ? " tasks" : " task") + " in the list");*/
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Wow so free is it?");
            }
        } else if (word[0].equals("event")) {
            try {

                String[] holder = word[1].split(" /at ", 2);
                //String[] eventTime = holder[1].split(" ", 2);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime formatDateTime = LocalDateTime.parse(holder[1], formatter);
                task = new Event(holder[0], formatDateTime.format(formatter));
                return new Event(holder[0], formatDateTime.format(formatter));
                        /*System.out.println("Got it. I've added this task:");
                        yettodo.add(task);
                        System.out.println(task);
                        System.out.println("Now you have " + yettodo.size() + ((yettodo.size() > 1) ? " tasks" : " task") + " in the list");*/
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("Lepak?");
            }
        } else {
            //print("error")
            System.out.println(" ");
        }
        return new Task("Garbage");

    }

    public static String BackConversion(Task example) throws Exception {
        if (example instanceof Todo) {
            return "todo " + example.description;
        } else if (example instanceof Deadline) {
            return "deadline " + example.description + "/by " + ((Deadline) example).getBy();
        } else if (example instanceof Event) {
            return "event " + example.description + "/at " + ((Event) example).getAt();
        }
        throw new UnknownException("Failed to convert to string!");
    }
}
