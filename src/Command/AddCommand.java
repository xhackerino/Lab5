package Command;

import util.CollectionManager;
import util.ConsoleManager;

import java.io.IOException;

public class AddCommand implements Command {
    CollectionManager collectionManager;
    ConsoleManager consoleManager;
    public AddCommand(CollectionManager cm, ConsoleManager csm) {
        this.collectionManager = cm;
        this.consoleManager = csm;
    }
    @Override
    public String getName() {
        return "add";
    }

    @Override
    public String getDescription() {
        return " {element} : добавить новый элемент в коллекцию";
    }

    @Override
    public boolean execute(String arg) throws IOException {
        collectionManager.add(consoleManager.askGroup(collectionManager.nextId()));
        return true;
    }
}
