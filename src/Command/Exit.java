package Command;

import java.io.IOException;
import Exception.EmptyIOException;
import util.CollectionManager;

public class Exit implements Command {
    CollectionManager collectionManager;
    public Exit(CollectionManager cm) {
        this.collectionManager = cm;
    }
    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public String getDescription() {
        return " : завершить программу (без сохранения в файл)";
    }

    @Override
    public boolean execute(String arg) throws IOException, EmptyIOException {
        collectionManager.exit();
        return true;
    }
}
