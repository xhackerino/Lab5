package Command;

import java.io.IOException;
import Exception.EmptyIOException;
import StudyGroup.StudyGroup;
import util.CollectionManager;

import static util.ConsoleManager.PrintError;

public class RemoveAt implements Command {
    CollectionManager collectionManager;
    public RemoveAt(CollectionManager cm) {
        this.collectionManager = cm;
    }
    @Override
    public String getName() {
        return "remove_at";
    }

    @Override
    public String getDescription() {
        return " index : удалить элемент, находящийся в заданной позиции коллекции (index)";
    }

    @Override
    public boolean execute(String arg) throws IOException, EmptyIOException {
        int index;
        try {
            if (arg.trim().equals("")) throw new EmptyIOException();
            index = Integer.parseInt(arg.trim());
        } catch (NumberFormatException e) {
            PrintError("Индекс должен быть целым числом");
            return true;
        } catch (EmptyIOException e) {
            PrintError("Индекс не может быть пустым");
            return true;
        }
        if (index < 0) {
            PrintError("Индекс не может быть меньше 0");
            return true;
        }
        if (collectionManager.getStudyGroup().size() < index) {
            PrintError("Индекс не должен быть больше количества элементов в коллекции");
        }
        collectionManager.removeByIndex(index);
        return true;
    }
}
