
public class Coordinates {
    int x, y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates() {
        x = y = 0;
    }

    public String toString() {
        return String.format("(%d, %d)", x, y);
    }
}
