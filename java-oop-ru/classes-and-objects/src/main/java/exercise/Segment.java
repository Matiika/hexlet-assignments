package exercise;

// BEGIN
class Segment {
    private Point beginPoint;
    private Point endPoint;

    Segment(Point beginPoint, Point endPoint) {
        this.beginPoint = beginPoint;
        this.endPoint = endPoint;
    }

    public Point getMidPoint() {
        Integer begin = (beginPoint.getX() + endPoint.getX()) / 2;
        Integer end = (beginPoint.getY() + endPoint.getY()) / 2;
        return new Point(begin, end);
    }

    public Point getBeginPoint() {
        return beginPoint;
    }

    public void setBeginPoint(Point beginPoint) {
        this.beginPoint = beginPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public void setEndPoint(Point endPoint) {
        this.endPoint = endPoint;
    }
}
// END
