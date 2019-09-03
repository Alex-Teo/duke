import java.util.Scanner;

public class UI {
    private Scanner input;
    public UI() {
        input = new Scanner(System.in);
        getUserInput();
    }

    public String getUserInput() {
        String inout = input.nextLine();
        return  inout;
    }


}
