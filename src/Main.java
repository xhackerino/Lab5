import StudyGroup.StudyGroup;
import util.FileManager;

import java.io.*;
import java.util.*;

import util.*;
import Command.*;
import Exception.EmptyIOException;

import static util.ConsoleManager.Print;

/**
 * @author Ilya Rakin ISU 336934
 */
public class Main {
    public static void main(String[] args) throws IOException, EmptyIOException {
        boolean success = false;
        while (!success) {
            try {
//        Stack<StudyGroup> jopa = FileManager.ReadCollection("C:\\Users\\rakin\\IdeaProjects\\Lab5-master\\ilya_reading.csv");
//        FileManager.WriteCollection("C:\\Users\\rakin\\IdeaProjects\\Lab5-master\\ilya_writing.csv", jopa);
                System.out.println("Hello, World!");
                String file_name = "FILE_NAME";
//        System.setOut(new PrintStream(System.out, true, "UTF-8"));
                if (System.getenv(file_name) == null) {
                    Print("Нет переменной с загрузочным файлом");
                }
                String fileName = System.getenv(file_name);
                FileManager fm = new FileManager(fileName);
                Scanner scanner = new Scanner(System.in);
                ConsoleManager consoleManager = new ConsoleManager(scanner);
                CollectionManager collectionManager = new CollectionManager(fm);
                System.out.println("Введите Вашу команду...");
                CommandManager commandManager = new CommandManager(consoleManager, collectionManager, scanner, new Command[]{
                        new AddCommand(collectionManager, consoleManager), new Clear(collectionManager), new Exit(collectionManager),
                        new FilterStartsWithName(collectionManager), new Info(collectionManager), new PrintFieldAscendingStudentsCount(collectionManager),
                        new RemoveAt(collectionManager), new RemoveByIdCommand(collectionManager, consoleManager), new RemoveGreater(collectionManager),
                        new RemoveLast(collectionManager), new SaveCommand(collectionManager), new Show(collectionManager), new SumOfExpelledStudents(collectionManager),
                        new UpdateIdCommand(collectionManager, consoleManager)});
                commandManager.consoleMode();
                success = true;
            } catch (Exception e) {
                break;
            }
//            public void func ( boolean a1, boolean a2, boolean a3, boolean a4){
//            }
        }
    }
}