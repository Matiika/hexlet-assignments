package exercise;

// BEGIN
class Circle {
    private Point point;
    private Integer radius;

    public Circle(Point point, Integer radius) {
        this.point = point;
        this.radius = radius;
    }

    public Integer getRadius() {
        return radius;
    }

    public double getSquare() throws NegativeRadiusException {
        if (radius < 0) {
            throw new NegativeRadiusException("Не удалось посчитать площадь");
        }

        return (Math.PI * (radius * radius));
    }
}
// END
