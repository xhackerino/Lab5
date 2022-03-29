package Command;
import java.io.*;
import java.util.Arrays;
import Exception.CommandException;
public abstract class DefaultCommand {
    private String name;
    private String[] args;
    public DefaultCommand(String name, String[] args) {
        this.name = name;
        this.args = args;
    }
    public abstract void execute(Environment env, Object[] args, InputStream inStream, OutputStream outStream)
        throws CommandException;
    public final String getUsing() {
        return "Usage:" + name + " " + getArgsAsString();
    }
    public final String getArgsAsString() {
        if (args.length > 0) {
            return Arrays.toString(args).replace(", ", "] [");
        } else {
            return "";
        }
    }
    public final String getName() {
        return name;
    }
    public boolean isNeedInput() {
        return false;
    }
    public String[] getInputOrder(int type) {
        return new String[] {};
    }
}
