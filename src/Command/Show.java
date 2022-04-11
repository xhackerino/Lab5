package Command;
import util.*;

import java.io.IOException;
import util.CollectionManager;
import static util.ConsoleManager.Print;

public class Show implements Command {
    CollectionManager collectionManager;
    public Show(CollectionManager cm) {
        this.collectionManager = cm;
    }
    @Override
    public String getName() {
        return "show";
    }

    @Override
    public String getDescription() {
        return " : вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }

    @Override
    public boolean execute(String arg) throws IOException {
        Print("Все элементы коллекции:");
        Print(collectionManager.show());
        return true;
    }
}