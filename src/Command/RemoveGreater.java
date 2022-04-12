package Command;

import util.CollectionManager;
import util.ConsoleManager;

import java.io.IOException;

import Exception.EmptyIOException;

/**
 * Команда 'remove_greater'. Удаляет из коллекции все элементы, превышающие заданный.
 */
public class RemoveGreater implements Command {
    CollectionManager collectionManager;
    ConsoleManager consoleManager;
    int index;

    /**
     * Конструктор команды.
     * @param cm менеджер коллекции.
     */
    public RemoveGreater(CollectionManager cm) {
        this.collectionManager = cm;
    }

    @Override
    public String getName() {
        return "remove_greater";
    }

    @Override
    public String getDescription() {
        return " {element} : удалить из коллекции все элементы, превышающие заданный";
    }

    @Override
    public boolean execute(String arg) throws IOException, EmptyIOException {
        index = Integer.parseInt(arg.trim());
        collectionManager.removeAllGreater(index);
        return true;
    }
}
