import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        ArrayList<Task> yettodo = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String inout = input.nextLine();
        try {
            while (!inout.equals("bye")) {
                Task task;
                //System.out.println(inout);
                String[] word = inout.split(" ", 2);
                if (inout.equals("list")) {
                    task = new Task(inout);
                    System.out.println("Here are the tasks in your list:");
                    int i = 0;
                    for (Task t : yettodo) {
                        System.out.println((i + 1) + ". ");
                        System.out.println(t);
                        i++;
                    }
                } else if (word[0].equals("done")) {
                    try {
                        int num = Integer.parseInt(word[1]);
                        yettodo.get(num - 1).Done();
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println ("Done what?");
                    }
                } else if (word[0].equals("todo")) {
                    try {

                        task = new Todo(word[1]);
                        System.out.println("Got it. I've added this task:");
                        yettodo.add(task);
                        System.out.println(task);
                        System.out.println("Now you have " + yettodo.size() + ((yettodo.size() > 1) ? " tasks" : " task") + " in the list");
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println ("☹ OOPS!!! The description of a todo cannot be empty.");

                    }
                } else if (word[0].equals("deadline")) {
                    try {

                        String[] holder = word[1].split(" /by ", 2);
                        task = new Deadline(holder[0], holder[1]);
                        System.out.println("Got it. I've added this task:");
                        yettodo.add(task);
                        System.out.println(task);
                        System.out.println("Now you have " + yettodo.size() + ((yettodo.size() > 1) ? " tasks" : " task") + " in the list");
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println ("Wow so free is it?");
                    }
                } else if (word[0].equals("event")) {
                    try {

                        String[] holder = word[1].split(" /at ", 2);
                        task = new Event(holder[0], holder[1]);
                        System.out.println("Got it. I've added this task:");
                        yettodo.add(task);
                        System.out.println(task);
                        System.out.println("Now you have " + yettodo.size() + ((yettodo.size() > 1) ? " tasks" : " task") + " in the list");
                    }
                    catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("Lepak?");
                    }
                }
                else {
                    try {
                        task = new Task(inout);
                        yettodo.add(task);
                        System.out.println("added: ");
                        System.out.println(inout);
                    }
                    catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("Being Air seems nice");
                    }
                }
                inout = input.nextLine();
            }


            System.out.println("Bye. Hope to see you again soon!");

        }
        finally {
            System.out.println ("That's a wrap");
        }
    }
}
