import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;


public class Duke extends Application{
    //private static String pathName = "C:\\Users\\axisr\\OneDrive\\Desktop\\Year 2 Sem 1\\CS2113T";
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;
    private Ui ui;
    //private Image user = new Image(this.getClass().getResourceAsStream("/images/DaUser.png"));
    //private Image duke = new Image(this.getClass().getResourceAsStream("/images/DaDuke.png"));
    private static ArrayList<Task> yettodo = new ArrayList<>();
    private static Storage storage = new Storage("./duke.txt", yettodo);
    /*public static ArrayList<Task> getYettodo()  {
        return yettodo;
    }*/

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
        yettodo = storage.fileReader();
        Duke duke = new Duke();
    }

    public Duke() {
        ui = new Ui();
        String inout = ui.getUserInput();
        while (!inout.equals("bye")) {
            System.out.println (inout);
            parseUserCommand(inout);
            inout = ui.getUserInput();// do while less messy cod
            storage.fileUpdate();
        }
        System.out.println("Bye. Hope to see you again soon!");
        Platform.exit();
        System.exit(0);
    }

    private void parseUserCommand(String inout) {
        try {
            execute(inout);

            storage.saveData();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void execute(String inout) {
            Task task;
            //System.out.println(inout);
            String[] word = inout.split(" ", 2);
            if (inout.equals("list")) {
                callList();
            } else if (word[0].equals("done")) {
                markasDone(word[1]);
            } else if (word[0].equals("delete")) {
                delete(word[1]);
            }
            else if (word[0].equals("find")) {
                finding(word[1]);
            }
            else if (word[0].equals("todo")) {
                justdo(inout, word);
            } else if (word[0].equals("deadline")) {
                deadlineAssign(inout, word[1]);
            } else if (word[0].equals("event")) {
                eventAssign(inout, word[1]);
            }
    }

    private void callList() {
        System.out.println("Here are the tasks in your list:");
        int i = 0;
        for (Task t : yettodo) {
            System.out.println((i + 1) + ". ");
            System.out.println(t);
            i++;
        }
    }


    private void markasDone(String s) {
        try {
            int num = Integer.parseInt(s);
            yettodo.get(num - 1).Done();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Done what?");
        }
    }

    private void eventAssign(String inout, String s) {
        Task task;
        try {

            String[] holder = s.split(" /at ", 2);
            //String[] eventTime = holder[1].split(" ", 2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime formatDateTime = LocalDateTime.parse(holder[1], formatter);
            task = new Event(holder[0], formatDateTime.format(formatter));
            System.out.println("Got it. I've added this task:");
            yettodo.add(task);
            System.out.println(task);
            System.out.println("Now you have " + yettodo.size() + ((yettodo.size() > 1) ? " tasks" : " task") + " in the list");
        }
        catch(ArrayIndexOutOfBoundsException e){
            System.out.println("Lepak?");
        }
    }

    private void deadlineAssign(String inout, String s) {
        Task task;
        try {

            String[] holder = s.split(" /by ", 2);
            //String[] deadlineTime = holder[1].split(" ", 2);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime formatDateTime = LocalDateTime.parse(holder[1], formatter);
            task = new Deadline(holder[0], formatDateTime.format(formatter));
            System.out.println("Got it. I've added this task:");
            yettodo.add(task);
            System.out.println(task);
            System.out.println("Now you have " + yettodo.size() + ((yettodo.size() > 1) ? " tasks" : " task") + " in the list");
        }
        catch (ArrayIndexOutOfBoundsException e) {
            System.out.println ("Wow so free is it?");
        }
    }

    private void justdo(String inout, String[] word) {
        Task task;
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
    }

    private void finding(String s) {
        int counter = 0;
        System.out.println ("Here are the matching tasks in your list:");
        try {
            for (Task t: yettodo) {
                if (t.toString().contains(s)) {
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

    private void delete(String s) {
        try {
            int num = Integer.parseInt(s);
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
    /*
    /**
     * Iteration 2:
     * Creates two dialog boxes, one echoing user input and the other containing Duke's reply and then appends them to
     * the dialog container. Clears the user input after processing.

    private void handleUserInput() {
        Label userText = new Label(userInput.getText());
        Label dukeText = new Label(getResponse(userInput.getText()));
        dialogContainer.getChildren().addAll(
                new DialogBox(userText, new ImageView(user)),
                new DialogBox(dukeText, new ImageView(duke))
        );
        userInput.clear();
    }

    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.

    private String getResponse(String input) {
        return "Duke heard: " + input;
    }*/

    @Override
    public void start(Stage stage) {
       /* Label helloWorld = new Label("Hello World!"); // Creating a new Label control
        Scene scene = new Scene(helloWorld); // Setting the scene to be our Label

        stage.setScene(scene); // Setting the stage to show our screen
        stage.show(); // Render the stage.*/

       //stage 1

        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Stage 2
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        //stage 3

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            //handleUserInput();
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            //handleUserInput();
            userInput.clear();
        });

        dialogContainer.heightProperty().addListener((observable) -> scrollPane.setVvalue(1.0));
    }
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }

}
