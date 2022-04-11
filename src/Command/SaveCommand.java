package Command;

import util.CollectionManager;

import java.io.IOException;
import Exception.EmptyIOException;

public class SaveCommand implements Command {
    CollectionManager collectionManager;
    public SaveCommand(CollectionManager cm) {
        this.collectionManager = cm;
    }
    @Override
    public String getName() {
        return "save";
    }

    @Override
    public String getDescription() {
        return " : сохранить коллекцию в файл";
    }

    @Override
    public boolean execute(String arg) throws IOException, EmptyIOException {
        collectionManager.save();
        return true;
    }
}
