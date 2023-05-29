package butinfo.model;

public class Coordinates implements Comparable<Coordinates> {
    int x, y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
        x = y = 0;
    }

    /**
     * Returns a string representation of this Point with CSV-like structure.
     * 
     * 
     * @return a string representation of this Point as seen in the CSV files.
     */
    public String toString() {
        return String.format("(%d, %d)", x, y);
    }

    public int compareTo(Coordinates other) {
        return Math.abs(x - other.x) + Math.abs(y - other.y);
    }
}
