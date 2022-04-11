package util;

import Command.*;

import java.io.IOException;
import Exception.EmptyIOException;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.Stack;
import Command.*;

import static util.ConsoleManager.Print;
import static util.ConsoleManager.PrintError;

/**
 * Класс нечто, которое будет парсить все поступаемые команды и вызывать их выполнение.
 */
public class CommandManager {
    /**
     * Поля, содержащие объекты команд.
     */
    private Scanner scanner;
    private Command[] commands;
    private CollectionManager collectionManager;
    private ConsoleManager consoleManager;
    private Stack<String> openedScripts;
//    private final Info info;
//    private final Show show;
//    private final AddCommand add;
//    private final UpdateIdCommand updateId;
//    private final RemoveByIdCommand removeById;
//    private final Clear clear;
//    private final SaveCommand save;
//    private final Exit exit;
//    private final RemoveAt removeAt;
//    private final RemoveLast removeLast;
//    private final RemoveGreater removeGreater;
//    private final SumOfExpelledStudents sumOfExpelledStudents;
//    private final FilterStartsWithName filterStartsWithName;
//    private final PrintFieldAscendingStudentsCount printFieldAscendingStudentsCount;

    /**
     * Конструктор менеджера. Автоматически инициализирует объекты всех команд при создании и менеджера коллекций.
     *
     * @param console    Менеджер консоли.
     * @param collection Менеджер коллекций.
     * @param scanner    Сканер команд.
     * @param commands   Список команд.
     * @throws IOException в случае ошибки ввода-вывода.
     * @see CollectionManager
     */
//    public CommandManager(Scanner scanner, Command[] commands, CollectionManager collection,
//                          ConsoleManager console, Info info, Show show, AddCommand add, UpdateIdCommand updateId, RemoveByIdCommand removeById,
//                          Clear clear, SaveCommand save, Exit exit, RemoveAt removeAt, RemoveLast removeLast, RemoveGreater removeGreater,
//    SumOfExpelledStudents sumOfExpelledStudents, FilterStartsWithName filterStartsWithName, PrintFieldAscendingStudentsCount printFieldAscendingStudentsCount) throws IOException {
    public CommandManager(ConsoleManager console, CollectionManager collection, Scanner scanner, Command[] commands) {
        consoleManager = console;
        collectionManager = collection;
        openedScripts = new Stack<String>();
        this.scanner = scanner;
        this.commands = commands;
        openedScripts = new Stack<String>();

//        this.info = info;
//        this.show = show;
//        this.add = add;
//        this.updateId = updateId;
//        this.removeById = removeById;
//        this.clear = clear;
//        this.save = save;
//        this.exit = exit;
//        this.removeAt = removeAt;
//        this.removeLast = removeLast;
//        this.removeGreater = removeGreater;
//        this.sumOfExpelledStudents = sumOfExpelledStudents;
//        this.filterStartsWithName = filterStartsWithName;
//        this.printFieldAscendingStudentsCount = printFieldAscendingStudentsCount;

    }

    public void ScriptMode(String filename) throws IOException, EmptyIOException {
        ScriptManager scriptManager = new ScriptManager(filename.trim());
        openedScripts.add(filename.trim());
        boolean isRunning = true;
        while (isRunning) {
            String nextLine = scriptManager.getNextLine();
            if (nextLine == null)
                break;
            String[] cmd = (nextLine.trim() + " ").split(" ", 2);
            if (cmd[0].trim().equals("help")) {
                for (Command comnd : commands) {
                    Print(comnd.getName() + comnd.getDescription());
                }
            } else if (cmd[0].trim().equals("execute_script")) {
                if (!cmd[1].trim().equals("")) {
                    if (openedScripts.contains(cmd[1].trim())) {
                        PrintError("Скрипт уже открыт");
                    } else {
                        ScriptMode(cmd[1].trim());
                    }
                } else {
                    PrintError("Не указан путь к скрипту");
                }
            } else if (!cmd[0].trim().equals("")) {
                boolean commandFound = false;
                for (Command comnd : commands) {
                    if (cmd[0].trim().equals(comnd.getName())) {
                        if (comnd.getName().trim().equals("add")) {
                            consoleManager.ChangeScanner(scriptManager.getScanner());
                            try {
                                isRunning = comnd.execute(cmd[1].trim());
                                commandFound = true;
                            } catch (NoSuchElementException e) {
                                PrintError("Не указаны все параметры");
                            }
                            consoleManager.ChangeScanner(new Scanner(System.in));
                        } else {
                            isRunning = comnd.execute(cmd[1].trim());
                            commandFound = true;
                        }
                        break;
                    }
                }
                if (!commandFound) {
                    PrintError("Команда:" + cmd[0] + " " + cmd[1] + " не найдена");
                }
            }
        }
        Print("Скрипт завершен");
    }

    /**
     * Консоль, которая будет выводить информацию о коллекции.
     */
    public void consoleMode() throws IOException, EmptyIOException, EmptyIOException {
        boolean isRunning = true;
        while (isRunning) {
            String[] cmd = (scanner.nextLine().trim() + " ").split(" ", 2);
            if (cmd[0].trim().equals("help")) {
                Print("help : вывод справки по доступным командам");
                Print("execute_script file_name : считать и исполнить скрипт из указанного файла, " +
                        "в скрипте содержатся команды в таком же виде, в котором их вводит " +
                        "пользователь в интерактивном режиме.");
                for (Command comnd : commands) {
                    Print(comnd.getName() + comnd.getDescription());
                }
            } else if (cmd[0].trim().equals("execute_script")) {
                if (!cmd[1].trim().equals("")) {
                    ScriptMode(cmd[1].trim());
                    openedScripts.clear();
                } else {
                    PrintError("Введите имя файла");
                }
            } else if (!cmd[0].trim().equals("")) {
                boolean commandFound = false;
                for (Command comnd : commands) {
                    if (cmd[0].trim().equals(comnd.getName())) {
                        isRunning = comnd.execute(cmd[1]);
                        commandFound = true;
                        break;
                    }
                }
                if (!commandFound) {
                    PrintError("Команда не найдена");
                }
            }
        }
    }
}
