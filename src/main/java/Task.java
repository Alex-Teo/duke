public class Task {
    public String description;

    public boolean isDone() {
        return isDone;
    }

    private boolean isDone;

    public Task (String description) {
        this.description = description;
        this.isDone = false;
        // - = private in class diagram, +  = public
    }

    public void Done () {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(("[✔] ") + this.description);

    }

    /*public void Delete () {
        System.out.println ("Noted. I've removed this task: ");

    }*/

    /*public String getStatusIcon() {
        return (isDone ? "✔" : "x"); //return tick or X symbols
    }*/

    @Override
    public String toString() {
        if (isDone) {
            return "[✔] " + this.description;
        } else {
            return "[x] " + this.description;
        }
    }
}