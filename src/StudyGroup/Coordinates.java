package StudyGroup;

public class Coordinates {
    private Long x; //Поле не может быть null
    private double y; //Значение поля должно быть больше -352
    public Coordinates(Long x, double y) {
        this.x = x;
        this.y = y;
    }
    public Coordinates(){}
    /**
     *
     * @throws IllegalArgumentException if any parameter is not correct...
     */
    public Long getX() {
        return x;
    }
    /**
     *
     * @param x X coordinate. X cannot be null
     */
    public void setX(Long x) {
        if (x == null) {
            throw new IllegalArgumentException("X cannot be null");
        } else {
            this.x = x;
        }
    }

    public double getY() {
        return y;
    }
    /**
     *
     * @param y Y coordinate. Y should be greater than '-352'
     */
    public void setY(double y) {
        if (y<=-352) {
            throw new IllegalArgumentException("Y should be greater than '-352'");
        } else {
            this.y = y;
        }
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
