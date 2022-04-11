package Command;

import java.io.IOException;

import Exception.EmptyIOException;
import util.CollectionManager;
import static util.ConsoleManager.Print;

public class Clear implements Command {
    CollectionManager collectionManager;

    public Clear(CollectionManager cm) {
        this.collectionManager = cm;
    }

    @Override
    public String getName() {
        return "clear";
    }

    @Override
    public String getDescription() {
        return " : очистить коллекцию";
    }

    @Override
    public boolean execute(String arg) throws IOException, EmptyIOException {
        collectionManager.clear();
        Print("Коллекция очищена");
        return true;
    }
}
