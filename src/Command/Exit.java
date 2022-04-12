package Command;

import java.io.IOException;
import Exception.EmptyIOException;
import util.CollectionManager;

/**
 * Команда 'exit'. Завершает выполнение программы.
 */
public class Exit implements Command {
    CollectionManager collectionManager;

    /**
     * Конструктор команды.
     * @param cm менеджер коллекции.
     * @throws EmptyIOException если коллекция пуста.
     */
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
