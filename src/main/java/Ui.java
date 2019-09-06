import java.util.Scanner;

public class Ui {
    private Scanner input;
    public Ui() {
        input = new Scanner(System.in);
    }

    public String getUserInput() {
        String inout = input.nextLine();
        return  inout;
    }


}
