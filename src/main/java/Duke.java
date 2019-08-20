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
        Scanner input = new Scanner(System.in);
        String inout = input.nextLine();
        while (!inout.equals("bye")) {
            System.out.println(inout);
            inout = input.nextLine();
        }

        System.out.println("Bye. Hope to see you again soon!");
    }
}
