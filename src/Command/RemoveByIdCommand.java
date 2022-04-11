package Command;

import StudyGroup.StudyGroup;
import util.CollectionManager;
import util.CommandManager;
import util.ConsoleManager;
import Exception.EmptyIOException;

import java.io.IOException;

import static util.ConsoleManager.PrintError;

public class RemoveByIdCommand implements Command {
    CollectionManager collectionManager;
    ConsoleManager consoleManager;

    public RemoveByIdCommand(CollectionManager cm, ConsoleManager csm) {
        this.collectionManager = cm;
        this.consoleManager = csm;
    }

    @Override
    public String getName() {
        return "remove_by_id";
    }

    @Override
    public String getDescription() {
        return " id : удалить элемент из коллекции по его id";
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
        collectionManager.removeElement(id);
        return true;
    }
}
