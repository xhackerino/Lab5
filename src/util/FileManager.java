package util;

import StudyGroup.StudyGroup;
import StudyGroup.Person;
import StudyGroup.Coordinates;
import StudyGroup.FormOfEducation;
import StudyGroup.Semester;
import StudyGroup.Color;
import StudyGroup.Country;

import java.io.*;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Stack;

import static util.ConsoleManager.Print;
import static util.ConsoleManager.PrintError;

public class FileManager {
    private String fileName;
    private static ArrayList<String> fields;
    private static ArrayList<String> keys;

    public FileManager(String fileName) {
        this.fileName = fileName;
    }

    private static void MakeFieldsArray(String line) {
        String word = "";
        fields = new ArrayList<String>();
        keys = new ArrayList<String>();
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ';') {
                word += line.charAt(i);
            } else {
                fields.add(word);
                word = "";
            }
        }
        fields.add(word);
    }

    public void WriteCollection(Stack<StudyGroup> stack) throws IOException {
        if (fileName == null) {
            PrintError("Нельзя сохранять");
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(fileName);
            String stek = "ID;NAME;CREATIONDATE;STUDENTSCOUNT;EXPELLEDSTUDENTS;FORMOFEDUCATION;" +
                    "SEMESTERENUM;PERSONNAME;PASSPORTID;EYECOLOR;NATIONALITY;X;Y\n";
            for (StudyGroup StudyGroup : stack) {
                stek += StudyGroup.getId().toString() + ";" + StudyGroup.getName() + ";" + StudyGroup.getCreationDate().toString() + ";" +
                        StudyGroup.getStudentsCount() + ";" + StudyGroup.getExpelledStudents().toString() + ";" + StudyGroup.getFormOfEducation().toString() + ";" +
                        StudyGroup.getSemesterEnum() + ";" + StudyGroup.getGroupAdmin().getName() + ";"
                        + StudyGroup.getGroupAdmin().getPassportID() + ";" + StudyGroup.getGroupAdmin().getEyeColor().toString() + ";"
                        + StudyGroup.getGroupAdmin().getNationality().toString() + ";" + StudyGroup.getCoordinates().getX().toString() + ";" +
                        StudyGroup.getCoordinates().getY() + "\n";

            }
            fos.write(stek.getBytes());
        } catch (IOException e) {
            PrintError("Файл не найден");
        }
    }

    public Stack<StudyGroup> ReadCollection() throws IOException {
        if (fileName == null) {
            return new Stack<StudyGroup>();
        }
        try {
            Stack<StudyGroup> stack = new Stack<>();
            BufferedReader reader = new BufferedReader(new FileReader(fileName));
            MakeFieldsArray(reader.readLine());
            while (reader.ready()) {
                stack.add(studyGroupFromCSV(reader.readLine()));
            }
            reader.close();
            Print("Коллекция загружена");
            return stack;
        } catch (FileNotFoundException e) {
            PrintError("Файл не найден");
        } catch (NoSuchElementException e) {
            PrintError("Загрузочный файл пуст");
        } catch (IOException e) {
            PrintError("Ошибка доступа к файлу");
        }
        return new Stack<>();
    }

    private StudyGroup studyGroupFromCSV(String line) {
        String word = "";
        Long id = null;
        String name = null;
        java.time.ZonedDateTime creationDate = null;
        Long coordinatesX = null;
        Double coordinatesY = null;
        Long studentsCount = null;
        Long expelledStudents = null;
        FormOfEducation formOfEducation = null;
        Semester semesterEnum = null;
        Person groupAdmin = null;
        StudyGroup studyGroup = new StudyGroup();
        Person person = new Person();
        Coordinates coordinates = new Coordinates();
        String personName = null;
        String personPassportId = null;
        Color eyeColor = null;
        Country nationality = null;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) != ';') {
                word += line.charAt(i);
            } else {
                keys.add(word);
                word = "";
            }

        }
        keys.add(word);
//        System.out.println(keys);
        for (int i = 0; i < keys.size(); i++) {
            switch (Chooser(i)) {
                case 1:
                    id = Long.parseLong(keys.get(i));
                    break;
                case 2:
                    name = keys.get(i);
                    break;
                case 3:
                    creationDate = ZonedDateTime.parse(keys.get(i));
                    break;
                case 4:
                    studentsCount = Long.parseLong(keys.get(i));
                    break;
                case 5:
                    if (keys.get(i).equals("null")) {
                        expelledStudents = null;
                    } else {
                        expelledStudents = Long.parseLong(keys.get(i));
                    }
                    break;
                case 6:
                    if (keys.get(i).equals("null")) {
                        formOfEducation = null;
                    } else {
                        formOfEducation = FormOfEducation.valueOf(keys.get(i));
                    }
                    break;
                case 7:
                    semesterEnum = Semester.valueOf(keys.get(i));
                    break;
                case 8:
                    personName = keys.get(i);
                    break;
                case 9:
                    personPassportId = keys.get(i);
                    break;
                case 10:
                    eyeColor = Color.valueOf(keys.get(i));
                    break;
                case 11:
                    nationality = Country.valueOf(keys.get(i));
                    break;
                case 12:
                    coordinatesX = Long.parseLong(keys.get(i));
                    break;
                case 13:
                    coordinatesY = Double.parseDouble(keys.get(i));
                    break;
            }
        }
        groupAdmin = new Person(personName, personPassportId, eyeColor, nationality);
        coordinates = new Coordinates(coordinatesX, coordinatesY);
        studyGroup = new StudyGroup(id, name, creationDate, studentsCount, expelledStudents,
                formOfEducation, semesterEnum, groupAdmin, coordinates);
        keys.clear();
        return studyGroup;
    }

    private static int Chooser(int i) {
        if (fields.get(i).equals("ID")) {
            return 1;
        } else if (fields.get(i).equals("NAME")) {
            return 2;
        } else if (fields.get(i).equals("CREATIONDATE")) {
            return 3;
        } else if (fields.get(i).equals("STUDENTSCOUNT")) {
            return 4;
        } else if (fields.get(i).equals("EXPELLEDSTUDENTS")) {
            return 5;
        } else if (fields.get(i).equals("FORMOFEDUCATION")) {
            return 6;
        } else if (fields.get(i).equals("SEMESTERENUM")) {
            return 7;
        } else if (fields.get(i).equals("PERSONNAME")) {
            return 8;
        } else if (fields.get(i).equals("PASSPORTID")) {
            return 9;
        } else if (fields.get(i).equals("EYECOLOR")) {
            return 10;
        } else if (fields.get(i).equals("NATIONALITY")) {
            return 11;
        } else if (fields.get(i).equals("X")) {
            return 12;
        } else if (fields.get(i).equals("Y")) {
            return 13;
        } else {
            return 0;
        }
    }
}