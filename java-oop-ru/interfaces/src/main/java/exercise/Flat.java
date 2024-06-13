package exercise;

// BEGIN
class Flat implements Home {
    double area;
    double balconyArea;
    int floor;

    public Flat(double area, double balconyArea, int floor) {
        this.area = area;
        this.balconyArea = balconyArea;
        this.floor = floor;
    }

    @Override
    public double getArea() {
        return (area + balconyArea);
    }

    @Override
    public int compareTo(Home another) {
        if (getArea() > another.getArea()) {
            return 1;
        } else if (getArea() < another.getArea()) {
            return -1;
        } else {
            return 0;
        }
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getBalconyArea() {
        return balconyArea;
    }

    public void setBalconyArea(double balconyArea) {
        this.balconyArea = balconyArea;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    @Override
    public String toString() {
        var area = getArea();
        return "Квартира площадью " + area + " метров на " + floor + " этаже";
    }


}

// END
