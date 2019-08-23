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
        ArrayList<Task> todo = new ArrayList<>();
        Scanner input = new Scanner(System.in);
        String inout = input.nextLine();

        while (!inout.equals("bye")) {
            Task task = new Task (inout);
            //System.out.println(inout);
            String[] word = inout.split(" ");
            if (inout.equals("list")) {
                int i = 0;
                for (Task t: todo) {
                    System.out.println((i + 1) + ". ");
                    System.out.println(t);
                    i++;
                }

            }
            else if (word[0].equals("done")) {
                int num = Integer.parseInt(word[1]);
                todo.get(num - 1).Done();
            }
            else {
                todo.add(task);
                System.out.println("added: ");
                System.out.println(inout);
            }
            inout = input.nextLine();
        }


        System.out.println("Bye. Hope to see you again soon!");
    }
}
