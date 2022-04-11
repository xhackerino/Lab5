package Command;

import util.CollectionManager;

import java.io.IOException;

import Exception.EmptyIOException;

public class SumOfExpelledStudents implements Command {
    CollectionManager collectionManager;

    public SumOfExpelledStudents(CollectionManager collectionManager) {
        this.collectionManager = collectionManager;
    }

    @Override
    public String getName() {
        return "sum_of_expelled_students";
    }

    @Override
    public String getDescription() {
        return " : вывести сумму значений поля expelledStudents для всех элементов коллекции";
    }

    @Override
    public boolean execute(String arg) throws IOException, EmptyIOException {
        collectionManager.sumOfExpelledStudents();
        return true;
    }
}
