package Command;

import util.CollectionManager;

import java.io.IOException;

import Exception.EmptyIOException;

public class RemoveGreater implements Command {
    CollectionManager collectionManager;

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
        return true;
    }
}
