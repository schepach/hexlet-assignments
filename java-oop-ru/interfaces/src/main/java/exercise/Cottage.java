package exercise;

// BEGIN
public class Cottage implements Home {

    private double area;
    private int floorCount;

    public Cottage(double area, int floorCount) {
        this.area = area;
        this.floorCount = floorCount;
    }

    public int getFloorCount() {
        return floorCount;
    }

    @Override
    public String toString() {
        return this.floorCount + " этажный коттедж площадью " + this.area + " метров";
    }

    @Override
    public double getArea() {
        return this.area;
    }

    @Override
    public int compareTo(Home another) {
        return Double.compare(this.area, another.getArea());
    }
}
// END
