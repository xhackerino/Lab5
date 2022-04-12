package util;
import java.time.ZonedDateTime;

import StudyGroup.StudyGroup;
import StudyGroup.FormOfEducation;
import StudyGroup.Semester;
import StudyGroup.Color;
import StudyGroup.Country;
import StudyGroup.Coordinates;
import StudyGroup.Person;
import java.io.IOException;
import Exception.EmptyIOException;

import java.util.Arrays;
import java.util.EmptyStackException;
import java.util.Scanner;

/**
 * Менеджер консоли
 */
public class ConsoleManager {
    private Scanner scanner;

    /**
     * Ввод данных
     * @param scanner Сканер для ввода
     */
    public ConsoleManager(Scanner scanner) {
        this.scanner = scanner;
    }

    /**
     * Вывод на экран
     * @param message Сообщение
     */
    public static void Print(String message) {
        System.out.println(message);
    }

    /**
     * Вывод на экран
     * @param message Сообщение об ошибке
     */
    public static void PrintError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Аскер для нового элемента
     * @param Id Идентификатор
     * @return Новый элемент
     * @throws IOException Ошибка ввода
     */
    public StudyGroup askGroup(Long Id) throws IOException {
        Print("id = " + Id);
        String name = parseString("Введите имя:");
        java.time.ZonedDateTime creationDate = ZonedDateTime.now();
        long stdCnt;
        while (true) {
            stdCnt = parseLong("Введите количество студентов:");
            if (stdCnt < 0) {
                PrintError("Количество студентов не может быть отрицательным");
            } else {
                break;
            }
        }
        long xpdStd;
        while (true) {
            xpdStd = parseLong("Введите количество отчисленных студентов:");
            if (xpdStd < 0) {
                PrintError("Количество отчисленных студентов не может быть отрицательным");
            } else {
                break;
            }
        }
        FormOfEducation foe = parseFormOfEducation("Введите форму обучения");
        Semester sem = parseSemester("Введите семестр");
        String grAdm = parseString("Введите имя админа:");
        String passId = parseString("Введите номер пасспорта:");
        Color color = parseColor("Введите цвет");
        Country nazi = parseCountry("Введите национальность");
        Long x = parseLong("Введите координату x:");
        double y;
        while (true) {
            y = parseDouble("Введите координату y:");
            if (y < -352) {
                Print("Error: y должен быть больше -325");
            } else {
                break;
            }
        }
        return new StudyGroup(Id, name, creationDate, stdCnt,
                xpdStd, foe, sem, new Person(grAdm, passId, color, nazi), new Coordinates(x, y));
    }
    public String parseString(String message) {
        String str = null;
        while (str == null) {
            try {
                Print(message);
                String message2 = scanner.nextLine().trim();
                if (message2.equals(""))
                    throw new EmptyIOException();
                if (message2 == null)
                    throw new EmptyIOException();
                str = message2;
            } catch (EmptyIOException e) {
                PrintError("Ввод не может быть пустым");
            }
        }
        return str;
    }
    public Long parseLong(String message) {
        Long out = null;
        while (out == null) {
            try {
                Print(message);
                String message2 = scanner.nextLine().trim();
                if (message2.equals(""))
                    throw new EmptyIOException();
                if (message2 == null)
                    throw new EmptyIOException();
                out = Long.parseLong(message2);
            } catch (EmptyIOException e) {
                PrintError("Ввод не может быть пустым");
            } catch (NumberFormatException e) {
                PrintError("Ввод должен быть числом");
            }
        }
        return out;
    }
    public Double parseDouble(String message) {
        Double outta = null;
        while (outta == null) {
            try {
                Print(message);
                String message2 = scanner.nextLine().trim();
                if (message2.equals(""))
                    throw new EmptyIOException();
                if (message2 == null)
                    throw new EmptyIOException();
                outta = Double.parseDouble(message2);
            } catch (EmptyIOException e) {
                PrintError("Ввод не может быть пустым");
            } catch (NumberFormatException e) {
                PrintError("Ввод должен быть числом");
            }
        }
        return outta;
    }
    public Semester parseSemester(String message) {
        Semester out = null;
        while (out == null) {
            try {
                Print(message);
                Print("Список семестров:\n" + Arrays.toString(Semester.values()));
                String message2 = scanner.nextLine().trim();
                if (message2.equals(""))
                    throw new EmptyIOException();
                if (message2 == null)
                    throw new EmptyIOException();
                out = Semester.valueOf(message2);
            } catch (EmptyIOException e) {
                PrintError("Ввод не может быть пустым");
            } catch (IllegalArgumentException e) {
                PrintError("Элемент должен быть из списка");
            }
        }
        return out;
    }
    public FormOfEducation parseFormOfEducation(String message) {
        FormOfEducation out = null;
        while (out == null) {
            try {
                Print(message);
                Print("Список форм обучения:\n" + Arrays.toString(FormOfEducation.values()));
                String message2 = scanner.nextLine().trim();
                if (message2.equals(""))
                    throw new EmptyIOException();
                if (message2 == null)
                    throw new EmptyIOException();
                out = FormOfEducation.valueOf(message2);
            } catch (EmptyIOException e) {
                PrintError("Ввод не может быть пустым");
            } catch (IllegalArgumentException e) {
                PrintError("Элемент должен быть из списка");
            }
        }
        return out;
    }
    public Color parseColor(String message) {
        Color out = null;
        while (out == null) {
            try {
                Print(message);
                Print("Список цветов:\n" + Arrays.toString(Color.values()));
                String message2 = scanner.nextLine().trim();
                if (message2.equals(""))
                    throw new EmptyIOException();
                if (message2 == null)
                    throw new EmptyIOException();
                out = Color.valueOf(message2);
            } catch (EmptyIOException e) {
                PrintError("Ввод не может быть пустым");
            } catch (IllegalArgumentException e) {
                PrintError("Элемент должен быть из списка");
            }
        }
        return out;
    }
    public Country parseCountry(String message) {
        Country out = null;
        while (out == null) {
            try {
                Print(message);
                Print("Национальность:\n" + Arrays.toString(Country.values()));
                String message2 = scanner.nextLine().trim();
                if (message2.equals(""))
                    throw new EmptyIOException();
                if (message2 == null)
                    throw new EmptyIOException();
                out = Country.valueOf(message2);
            } catch (EmptyIOException e) {
                PrintError("Ввод не может быть пустым");
            } catch (IllegalArgumentException e) {
                PrintError("Элемент должен быть из списка");
            }
        }
        return out;
    }
    public void ChangeScanner(Scanner scanner) {
        this.scanner = scanner;
    }
}
