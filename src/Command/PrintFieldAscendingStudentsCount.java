package Command;

import util.CollectionManager;

import java.io.IOException;
import Exception.EmptyIOException;

public class PrintFieldAscendingStudentsCount implements Command {
    CollectionManager collectionManager;
    public PrintFieldAscendingStudentsCount(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "print_field_ascending_students_count";
    }

    @Override
    public String getDescription() {
        return " : вывести значения поля studentsCount всех элементов в порядке возрастания";
    }

    @Override
    public boolean execute(String arg) throws IOException, EmptyIOException {
        collectionManager.printFieldAscendingStudentsCount();
        return true;
    }
}
