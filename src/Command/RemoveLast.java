package Command;

import util.CollectionManager;

import java.io.IOException;
import Exception.EmptyIOException;

public class RemoveLast implements Command {
    CollectionManager collectionManager;
    public RemoveLast(CollectionManager cm){
        this.collectionManager = cm;
    }
    @Override
    public String getName() {
        return "remove_last";
    }

    @Override
    public String getDescription() {
        return " : удалить последний элемент из коллекции";
    }

    @Override
    public boolean execute(String arg) throws IOException, EmptyIOException {
        collectionManager.removeLast();
        return true;
    }
}
