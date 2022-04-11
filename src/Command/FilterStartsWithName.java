package Command;

import util.CollectionManager;

import java.io.IOException;
import Exception.EmptyIOException;

public class FilterStartsWithName implements Command{
    CollectionManager collectionManager;
    public FilterStartsWithName(CollectionManager collectionManager){
        this.collectionManager = collectionManager;
    }
    @Override
    public String getName() {
        return "filter_starts_with_name";
    }

    @Override
    public String getDescription() {
        return " name : вывести элементы, значение поля name которых начинается с заданной подстроки";
    }

    @Override
    public boolean execute(String arg) throws IOException, EmptyIOException {
        collectionManager.filterStartsWithName(arg);
        return true;
    }
}
