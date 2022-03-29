package Exception;

public class CommandException extends Exception {
    private static final long serialVersionUID = 000L;
    public CommandException(String text) {
        super(text);
    }

}