package util;

import StudyGroup.StudyGroup;

import java.io.IOException;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.Objects;
import java.util.Stack;
import java.util.Comparator;

import static util.ConsoleManager.Print;
import static util.ConsoleManager.PrintError;
//import static util.FileManager.ReadCollection;
//import static util.FileManager.WriteCollection;

/**
 * класс, объект которого хранит в себе коллекцию и управляет ей.
 */
public class CollectionManager {
    private Stack<StudyGroup> studyGroup;
    private FileManager fileManager;
    private java.time.ZonedDateTime currTime;

    public CollectionManager(FileManager fm) throws IOException {
//        loadCollection();
//        saveCollection();
        this.fileManager = fm;
        studyGroup = fileManager.ReadCollection();
        currTime = java.time.ZonedDateTime.now();
        ;
        System.out.println("Для получения списка команд введите help");
    }

    public Stack<StudyGroup> loadCollection() throws IOException {
//        fileManager.ReadCollection();
        return studyGroup;
    }

    public Stack<StudyGroup> getStudyGroup() {
        return studyGroup;
    }

    public String getInfo() {
        return "Коллекция типа Stack из элементов StudyGroup"
                + ", дата инициализации: " + currTime.toString()
                + ", количество элементов в коллекции: " + (studyGroup.size());
    }

    public String show() {
        String show = "";
        for (StudyGroup sgp : studyGroup) {
            show += sgp.toString() + "\n";
        }
        return show;
    }

    public void add(StudyGroup sg) throws IOException {
        studyGroup.add(sg);
        Print("Элемент добавлен");
//        Print(studyGroup.toString()+"\n");
    }

    public long nextId() {
        if (studyGroup.isEmpty())
            return 1;
        else
            return studyGroup.lastElement().getId() + 1;
    }

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

    public void removeElement(Long id) throws IOException {
        for (StudyGroup group : studyGroup) {
            if (Objects.equals(group.getId(), id)) {
                studyGroup.remove(group);
                Print("Элемент удален");
//                Print(studyGroup.toString());
            }
        }
    }

    public void save() throws IOException {
        fileManager.WriteCollection(studyGroup);
        Print("Коллекция сохранена");
    }

    public void clear() throws IOException {
        if (studyGroup.size() > 0) {
            studyGroup.clear();
        } else {
            Print("Коллекция очищена");
        }
//        Print(studyGroup.toString());
    }

    public void exit() throws IOException {
        Print("Завершение программы. Прощайте...");
        System.exit(0);
    }

    public void removeByIndex(int index) throws IOException {
        int indeks = 0;
        for (StudyGroup group : studyGroup) {
            if (indeks == index) {
                studyGroup.remove(group);
                Print("Элемент удален");
                Print(studyGroup.toString());
            }
            indeks++;
        }
    }

    public void removeLast() throws IOException {
        studyGroup.remove(studyGroup.size() - 1);
        Print("Элемент удален");
//        Print(studyGroup.toString());
    }

    public void removeGreater(StudyGroup sg) throws IOException {
        for (StudyGroup group : studyGroup) {
            if (group.compareTo(sg) > 0) {
                studyGroup.remove(group);
                Print("Элемент удален");
//                Print(studyGroup.toString());
            }
        }
    }

    public void removeAllLower(StudyGroup sg) throws IOException {

        for (StudyGroup group : studyGroup) {
            if (group.compareTo(sg) < 0) {
                studyGroup.remove(group);
                Print("Элемент удален");
//                Print(studyGroup.toString());
            }
        }
    }

    public void sumOfExpelledStudents() throws IOException {
        int sum = 0;
        for (StudyGroup group : studyGroup) {
            sum += group.getExpelledStudents();
        }
        Print("Сумма выбранных элементов: " + sum);
    }

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

    public void printFieldAscendingStudentsCount() throws IOException {
        studyGroup.sort(Comparator.comparing(StudyGroup::getStudentsCount));
        Print("Коллекция отсортирована по полю studentsCount по возрастанию");
//        Print(studyGroup.toString());
    }
//    public void execute(String command) throws IOException {
//        switch (command) {
//            case "add":
//                add(new StudyGroup());
//                break;
//            case "update":
//                updateElement(new StudyGroup());
//                break;
//            case "remove":
//                removeElement(0L);
//                break;
//            case "clear":
//                clear();
//                break;
//            case "show":
//                Print(show());
//                break;
//            case "save":
//                save();
//                break;
//            case "exit":
//                exit();
//                break;
//            default:
//                Print("Неизвестная команда");
//        }
//    }
//    public void execute(String command, StudyGroup sg) throws IOException {
//        switch (command) {
//            case "add":
//                add(sg);
//                break;
//            case "update":
//                updateElement(sg);
//                break;
//            case "remove":
//                removeElement(sg.getId());
//                break;
//            case "clear":
//                clear();
//                break;
//            case "show":
//                Print(show());
//                break;
//            case "save":
//                save();
//                break;
//            case "exit":
//                exit();
//                break;
//            default:
//                Print("Неизвестная команда");
//        }
//    }
//    public void execute(String command, Long id) throws IOException {
//        switch (command) {
//            case "add":
//                add(new StudyGroup());
//                break;
//            case "update":
//                updateElement(new StudyGroup());
//                break;
//            case "remove":
//                removeElement(id);
//                break;
//            case "clear":
//                clear();
//                break;
//            case "show":
//                Print(show());
//                break;
//            case "save":
//                save();
//                break;
//            case "exit":
//                exit();
}
