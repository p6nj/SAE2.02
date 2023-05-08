package butinfo.model;

public class Coordinates {
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
}
