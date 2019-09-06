import java.io.*;
import java.util.ArrayList;
import java.util.*;

public class Storage {
    private static String pathName = "C:\\Users\\axisr\\OneDrive\\Desktop\\Year 2 Sem 1\\CS2113T";
    //public ArrayList<Task> load();
    //public addtoFile ()
    private String filename;
    ArrayList<Task> tasks ;
    public Storage(String pathName, ArrayList<Task> tasks) {
        this.pathName = pathName;
        this.tasks = tasks;

    }

    public ArrayList <Task> fileReader() {

        try {
            File f = new File("./duke.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                tasks.add(Parser.ConverterTexttoTask(s.nextLine()));
            }
            return tasks;
        }
        catch (IOException e) {
            System.out.println (e.getMessage());
        }
        return tasks;
    }


    public void fileUpdate() {
        File savefile = new File("./duke.txt");
        try {
            FileWriter Writer = new FileWriter(savefile);
            for (Task t : tasks) {
                Writer.write(Parser.ConverterTasktoText(t) + "\n");
            }
            Writer.close();
        }
        catch (IOException e) {
            System.out.println (e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public void saveData() throws Exception {
        File savefile = new File("./duke.txt");
        FileWriter Writer = new FileWriter(savefile, false);
        for (Task t : tasks) {
            Writer.write(Parser.ConverterTasktoText(t) + "\n");
        }
        Writer.close();

    }

}
