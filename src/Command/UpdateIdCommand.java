package Command;

import java.io.*;

import Exception.EmptyIOException;
import StudyGroup.StudyGroup;
import util.CollectionManager;
import util.ConsoleManager;

import static util.ConsoleManager.PrintError;

/**
 * Команда 'update id'. Обновляет значение элемента коллекции, id которого равен заданному.
 */
public class UpdateIdCommand implements Command {
    CollectionManager collectionManager;
    ConsoleManager consoleManager;

    public UpdateIdCommand(CollectionManager cm, ConsoleManager csm) {
        this.collectionManager = cm;
        this.consoleManager = csm;
    }

    @Override
    public String getName() {
        return "update";
    }

    @Override
    public String getDescription() {
        return " id {element} : обновить значение элемента коллекции, id которого равен заданному";
    }

    @Override
    public boolean execute(String arg) throws IOException, EmptyIOException {
        long id;
        try {
            if (arg.trim().equals("")) throw new EmptyIOException();
            id = Long.parseLong(arg.trim());
        } catch (NumberFormatException e) {
            PrintError("ID должен быть целым числом");
            return true;
        } catch (EmptyIOException e) {
            PrintError("ID не может быть пустым");
            return true;
        }
        if (!collectionManager.checkId(id)) {
            PrintError("Элемент с таким id не найден");
            return true;
        }
        StudyGroup group = consoleManager.askGroup(collectionManager.nextId());
        collectionManager.updateElement(group);
        return true;
    }
}