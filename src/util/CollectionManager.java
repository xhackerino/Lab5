package util;

import StudyGroup.StudyGroup;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Stack;
import java.util.Comparator;

import static java.lang.Math.abs;
import static util.ConsoleManager.Print;
import static util.ConsoleManager.PrintError;
//import static util.FileManager.ReadCollection;
//import static util.FileManager.WriteCollection;

/**
 * Класс, объект которого хранит в себе коллекцию и управляет ей.
 */
public class CollectionManager {
    private Stack<StudyGroup> studyGroup;
    private FileManager fileManager;
    private java.time.ZonedDateTime currTime;

    /**
     * Конструктор класса CollectionManager
     *
     * @param fm объект класса FileManager
     * @throws IOException исключение возникающее при ошибке чтения или записи в файл
     */

    public CollectionManager(FileManager fm) throws IOException {
//        loadCollection();
//        saveCollection();
        this.fileManager = fm;
        studyGroup = fileManager.ReadCollection();
        currTime = java.time.ZonedDateTime.now();
        ;
        System.out.println("Для получения списка команд введите help");
    }
    public Stack<StudyGroup> getStudyGroup() {
        return studyGroup;
    }

    /**
     * Метод для получения информации о коллекции.
     * @return возвращает информацию о коллекции
     */
    public String getInfo() {
        return "Коллекция типа Stack из элементов StudyGroup"
                + ", дата инициализации: " + currTime.toString()
                + ", количество элементов в коллекции: " + (studyGroup.size());
    }

    /**
     * Метод для вывода коллекции в консоль.
     * @return возвращает коллекцию в консоль в виде строк
     */
    public String show() {
        String show = "";
        for (StudyGroup sgp : studyGroup) {
            show += sgp.toString() + "\n";
        }
        return show;
    }

    /**
     * Метод для добавления элемента в коллекцию.
     * @param sg объект класса StudyGroup
     * @throws IOException исключение возникающее при ошибке чтения или записи в файл
     */
    public void add(StudyGroup sg) throws IOException {
        studyGroup.add(sg);
        Print("Элемент добавлен");
//        Print(studyGroup.toString()+"\n");
    }

    /**
     * Метод для генерации следующего id для нового элемента.
     * @return возвращает следующий идентификатор
     */
    public long nextId() {
        if (studyGroup.isEmpty())
            return 1;
        else
            return studyGroup.lastElement().getId() + 1;
    }

    /**
     * Метод для проверки соответствия ID.
     * @param Id идентификатор
     * @return возвращает true/false
     */
    public boolean checkId(long Id) {
        for (StudyGroup group : studyGroup) {
            {
                if (group.getId() == Id) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Метод для обновления значений элемента коллекции.
     * @param sg объект класса StudyGroup.
     * @throws IOException исключение возникающее при ошибке чтения или записи в файл
     */
    public void updateElement(StudyGroup sg) throws IOException {
        for (StudyGroup group : studyGroup) {
            if (Objects.equals(group.getId(), sg.getId())) {
//                Collections.replaceAll(studyGroup, group, sg);
                group.setId(sg.getId());
                group.setName(sg.getName());
                group.setCoordinates(sg.getCoordinates());
                group.setCreationDate(sg.getCreationDate());
                group.setStudentsCount(sg.getStudentsCount());
                group.setExpelledStudents(sg.getExpelledStudents());
                group.setFormOfEducation(sg.getFormOfEducation());
                group.setSemesterEnum(sg.getSemesterEnum());
                group.setGroupAdmin(sg.getGroupAdmin());
                Print("Элемент обновлен");
//                Print(studyGroup.toString());
            }
        }
    }

    /**
     * Метод для удаления элемента по ID.
     * @param id идентификатор
     * @throws IOException исключение возникающее при ошибке чтения или записи в файл
     */
    public void removeElement(Long id) throws IOException {
        for (StudyGroup group : studyGroup) {
            if (Objects.equals(group.getId(), id)) {
                studyGroup.remove(group);
                Print("Элемент удален");
//                Print(studyGroup.toString());
            }
        }
    }

    /**
     * Метод для сохранения коллекции в файл.
     * @throws IOException исключение возникающее при ошибке чтения или записи в файл
     */
    public void save() throws IOException {
        fileManager.WriteCollection(studyGroup);
        Print("Коллекция сохранена");
    }

    /**
     * Метод очищающий коллекцию.
     * @throws IOException исключение возникающее при ошибке чтения или записи в файл
     */
    public void clear() throws IOException {
        if (studyGroup.size() > 0) {
            studyGroup.clear();
        } else {
            Print("Коллекция очищена");
        }
//        Print(studyGroup.toString());
    }

    /**
     * Метод выхода из программы.
     * @throws IOException исключение возникающее при ошибке чтения или записи в файл
     */
    public void exit() throws IOException {
        Print("Завершение программы. Прощайте...");
        System.exit(0);
    }

    /**
     * Метод удаления элемента по индексу.
     * @param index индекс
     * @throws IOException исключение возникающее при ошибке чтения или записи в файл
     */
    public void removeByIndex(int index) throws IOException {
        int indeks = 0;
        for (StudyGroup group : studyGroup) {
            if (indeks == index) {
                studyGroup.remove(group);
                Print("Элемент удален");
                break;
            } else {
                indeks++;
            }
        }
    }

    /**
     * Метод удаления последнего элемента коллекции.
     * @throws IOException исключение возникающее при ошибке чтения или записи в файл
     */
    public void removeLast() throws IOException {
        studyGroup.remove(studyGroup.size() - 1);
        Print("Элемент удален");
    }

    /**
     * Метод удалить все элементы больше заданного
     * @param index индекс
     * @throws IOException исключение возникающее при ошибке чтения или записи в файл
     */
    public void removeAllGreater(int index) throws IOException {
        int indeks = studyGroup.size();
        System.out.println("indeks" + indeks);
        System.out.println("index" + index);
        int summa = (indeks + index);
        System.out.println("summa" + summa);
        int delete = Math.abs(indeks - summa);
        System.out.println("delete" + delete);
        int bomj = 0;
        String induks = Integer.toString(indeks);
        for (StudyGroup group : studyGroup) {
//            if (group.getId().compareTo(sg.getId()) > 0) {
            if (index < indeks) {
                if (bomj < delete) {
                    System.out.println("bomj" + bomj);
                    System.out.println("delete" + delete);
                    studyGroup.remove(group);
                    Print("Элемент удален");
                    delete--;
                    System.out.println("deleteNEW" + delete);
                } else {
                    bomj++;
                }
//                Print("Элемент удален");
//                Print(studyGroup.toString());
            }
        }
    }

    /**
     * Метод подсчета суммы отчисленных студентов.
     * @throws IOException исключение возникающее при ошибке чтения или записи в файл
     */
    public void sumOfExpelledStudents() throws IOException {
        int sum = 0;
        for (StudyGroup group : studyGroup) {
            sum += group.getExpelledStudents();
        }
        Print("Сумма выбранных элементов: " + sum);
    }

    /**
     * Метод вывода элементов, значение поля name которых начинаются с заданной подстроки
     * @param name имя в поле name
     * @return элементы, удовлетворяющие условиям
     * @throws IOException исключение возникающее при ошибке чтения или записи в файл
     */
    public String filterStartsWithName(String name) throws IOException {
        String fswn = "";
        for (StudyGroup group : studyGroup) {
            if (name.equals("") || name == null) {
                PrintError("Имя не может начинаться с пустоты");
                break;
            } else if (group.getName().startsWith(name.trim())) {
                fswn += group.toString() + "\n";
                Print("Элементы, значения поля 'имя' которых начинается с " + "'" + name + "'");
                Print(fswn);
            }
        }
        if (fswn.equals("")) {
            Print("Элементов, подходящих под условия, в коллекции нет, попробуйте другие варианты");
        }
        return fswn;
    }

    /**
     * Метод вывода значений поля StudentsCoubt всех элементов в порядке возрастания
     * @throws IOException исключение возникающее при ошибке чтения или записи в файл
     */
    public void printFieldAscendingStudentsCount() throws IOException {
        studyGroup.sort(Comparator.comparing(StudyGroup::getStudentsCount));
        Print("Коллекция отсортирована по полю studentsCount по возрастанию");
//        Print(studyGroup.toString());
    }
}