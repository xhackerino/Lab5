import StudyGroup.StudyGroup;

import java.io.*;
import java.util.*;
/**
 * @author Ilya Rakin ISU 336934
 */
public class Main {
    private static Stack<StudyGroup> stack = new Stack<StudyGroup>();
    private static HashMap<String, DefaultCommand> commandMap = new HashMap<String, DefaultCommand>();
    public static void main(String[] args) {
        System.setErr(System.out);
        if (args.length != 1) {
            args = new String[] { "" };
            System.out.println("You must enter a path to .csv file! Collection will be empty");
        }
        File file = new File(args[0]);
        try {
            System.out.println("Reading file...");
            if (CSVManager.read(file.toPath().toString(), stack, System.out)) {
                System.out.println("Collection loaded!");
            }
        } catch (Exception e) {
            System.out.println("Unexpected error during initialization. Program will be closed...");
            System.exit(-1);
        }

    }
}
