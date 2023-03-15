public class Quest {
    public int id;
    private int t, xp;
    private Coordinates coords;
    private Quest[][] conds = new Quest[2][2];
    private String name;

    public Quest(int questId, Coordinates questCoordinates, Quest[][] antecedents, int time, int exp, String title) {
        id = questId;
        coords = questCoordinates;
        conds = antecedents;
        t = time;
        xp = exp;
        name = title;
    }

    public String toString() {
        return String.format("%d|(%d, %d)|%s|%d|%d|%s", id, coords, conds, t, xp, name);
    }
}
