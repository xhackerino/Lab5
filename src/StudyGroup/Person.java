package StudyGroup;

/**
 * Класс Person
 */
public class Person {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private String passportID; //Поле не может быть null
    private Color eyeColor; //Поле не может быть null
    private Country nationality; //Поле может быть null

    /**
     * Конструктор класса Person.
     * @param name Имя. Не может быть null, строка не пустая
     * @param passportID Номер паспорта. Не может быть null
     * @param eyeColor Цвет глаз. Не может быть null
     * @param nationality Национальность. Не может быть null
     */
    public Person(String name, String passportID, Color eyeColor, Country nationality){
        this.name = name;
        this.passportID = passportID;
        this.eyeColor=eyeColor;
        this.nationality=nationality;
    }
    public Person(){}
    public String getName() {
        return name;
    }
    /**
     *
     * @param name Name can't be null or empty!
     */
    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name can't be null or empty!");
        } else {
            this.name = name;
        }
    }

    public String getPassportID() {
        return passportID;
    }

    /**
     *
     * @param passportID Passport ID can't be null
     */
    public void setPassportID(String passportID) {
        if (passportID == null) {
            throw new IllegalArgumentException("Passport ID can't be null");
        } else {
            this.passportID = passportID;
        }
    }

    public Color getEyeColor() {
        return eyeColor;
    }

    /**
     *
     * @param eyeColor Eye color can't be null
     */
    public void setEyeColor(Color eyeColor) {
        if (eyeColor == null) {
            throw new IllegalArgumentException("Eye color can't be null");
        } else {
            this.eyeColor = eyeColor;
        }
    }

    public Country getNationality() {
        return nationality;
    }

    /**
     *
     * @param nationality Nationality can't be null
     */
    public void setNationality(Country nationality) {
        if (nationality == null) {
            throw new IllegalArgumentException("Nationality can't be null");
        } else {
            this.nationality = nationality;
        }
    }
    @Override
    public String toString() {
        return "Персонаж {" +
                "имя ='" + name + '\'' +
                ", номер паспорта ='" + passportID + '\'' +
                ", цвет глаз = " + eyeColor +
                ", национальность = " + nationality +
                '}';
    }
}
