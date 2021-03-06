package Command;

import util.CollectionManager;

import java.io.IOException;
import Exception.EmptyIOException;

/**
 * Команда 'print_field_ascending_students_count'. Выводит значения поля studentsCount всех элементов коллекции в порядке возрастания.
 */
public class PrintFieldAscendingStudentsCount implements Command {
    CollectionManager collectionManager;

    /**
     * Конструктор команды.
     * @param collectionManager менеджер коллекции.
     */
    public PrintFieldAscendingStudentsCount(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }
    @Override
    public String getName() {
        return "print_field_ascending_students_count";
    }

    @Override
    public String getDescription() {
        return " : prints the values of the studentsCount field of all elements in ascending order";
    }

    @Override
    public boolean execute(String arg) throws IOException, EmptyIOException {
        collectionManager.printFieldAscendingStudentsCount();
        return true;
    }
}
