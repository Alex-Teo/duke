import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class addtoFile {
    private static String pathName = "C:\\Users\\axisr\\OneDrive\\Desktop\\Year 2 Sem 1\\CS2113T";
    //public ArrayList<Task> load();
    //public addtoFile ()
    private String filename;
    ArrayList<Task> tasks = new ArrayList<>(Duke.getYettodo());
    public addtoFile (String pathName, ArrayList<Task> tasks) {
        this.pathName = pathName;
        this.tasks = tasks;

    }

    public ArrayList <Task> fileReader() {

        try {
            File f = new File(pathName + "duke.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                tasks.add(ParseTextToTask.Converter(s.nextLine()));
            }
            return tasks;
        }
        catch (IOException e) {
            System.out.println (e.getMessage());
        }
        return tasks;
    }



    public void fileAddition() {
        File savefile = new File(pathName + "duke.txt");
        try {
            FileWriter Writer = new FileWriter(savefile, true);
            Writer.write(super.toString() + "\n" );
            Writer.close();
        }
        catch (IOException e) {
            System.out.println (e.getMessage());
        }

    }
    public void fileUpdate() {
        File savefile = new File(pathName + "duke.txt");
        try {
            FileWriter Writer = new FileWriter(savefile, false);
            for (Task t : tasks) {
                Writer.write(ParseTextToTask.BackConversion(t) + "\n");
                Writer.close();
            }
        }
        catch (IOException e) {
            System.out.println (e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
