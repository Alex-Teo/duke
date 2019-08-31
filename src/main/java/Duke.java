import java.util.ArrayList;
import java.io.*;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class Duke {
    //private static String pathName = "C:\\Users\\axisr\\OneDrive\\Desktop\\Year 2 Sem 1\\CS2113T";
    private static ArrayList<Task> yettodo = new ArrayList<Task>();
    private static addtoFile Fileread = new addtoFile("./duke.txt", yettodo);
    public static ArrayList<Task> getYettodo()  {
        return yettodo;
    }

    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello! I'm Duke");
        System.out.println("For entering time and date, please enter it in this format: yyyy-MM-dd HH:mm");
        System.out.println("What can I do for you?");
        yettodo = Fileread.fileReader();
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
                        Fileread.fileUpdate();
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Done what?");
                    }
                } else if (word[0].equals("delete")) {
                    try {
                        int num = Integer.parseInt(word[1]);
                        System.out.println ("Noted. I've removed this task: ");
                        System.out.println(yettodo.get(num - 1));
                        yettodo.remove(num - 1);
                        //Fileread.fileUpdate();
                        System.out.println("Now you have " + yettodo.size() + ((yettodo.size() > 1) ? " tasks" : " task") + " in the list");
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println("Delete what?");
                    }

                }
                else if (word[0].equals("find")) {
                    int counter = 0;
                    System.out.println ("Here are the matching tasks in your list:");
                    try {
                        for (Task t: yettodo) {
                            if (t.toString().contains(word[1])) {
                                System.out.println(yettodo.indexOf(t) + 1 + ". " + t.toString());
                                counter ++;
                            }
                            else {

                            }

                        }
                        if (counter == 0) {
                            System.out.println ("Nothing found!");
                        }

                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println ("Gone shit!");
                    }
                }
                else if (word[0].equals("todo")) {
                    try {

                        task = new Todo(word[1]);
                        System.out.println("Got it. I've added this task:");
                        yettodo.add(task);
                        Fileread.fileAddition(inout);
                        System.out.println(task);
                        System.out.println("Now you have " + yettodo.size() + ((yettodo.size() > 1) ? " tasks" : " task") + " in the list");
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println ("☹ OOPS!!! The description of a todo cannot be empty.");

                    }
                } else if (word[0].equals("deadline")) {
                    try {

                        String[] holder = word[1].split(" /by ", 2);
                        //String[] deadlineTime = holder[1].split(" ", 2);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime formatDateTime = LocalDateTime.parse(holder[1], formatter);
                        task = new Deadline(holder[0], formatDateTime.format(formatter));
                        System.out.println("Got it. I've added this task:");
                        yettodo.add(task);
                        Fileread.fileAddition(inout);
                        System.out.println(task);
                        System.out.println("Now you have " + yettodo.size() + ((yettodo.size() > 1) ? " tasks" : " task") + " in the list");
                    }
                    catch (ArrayIndexOutOfBoundsException e) {
                        System.out.println ("Wow so free is it?");
                    }
                } else if (word[0].equals("event")) {
                    try {

                        String[] holder = word[1].split(" /at ", 2);
                        //String[] eventTime = holder[1].split(" ", 2);
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                        LocalDateTime formatDateTime = LocalDateTime.parse(holder[1], formatter);
                        task = new Event(holder[0], formatDateTime.format(formatter));
                        System.out.println("Got it. I've added this task:");
                        yettodo.add(task);
                        Fileread.fileAddition(inout);
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
                        Fileread.fileAddition(inout);
                        System.out.println("added: ");
                        System.out.println(inout);
                    }
                    catch(ArrayIndexOutOfBoundsException e){
                        System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-()");
                    }

                }
                inout = input.nextLine();
            }

            Fileread.saveData();
            System.out.println("Bye. Hope to see you again soon!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println ("That's a wrap");
        }
    }

}
