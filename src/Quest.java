public class Quest {
        public int id;
        private int t, xp;
        private Coordinates coords;
        private int[][] conds = new int[2][2];
        private String name;

        public Quest(int questId, Coordinates questCoordinates, int[][] antecedents, int time, int exp, String title) {
                id = questId;
                coords = questCoordinates;
                conds = antecedents;
                t = time;
                xp = exp;
                name = title;
        }

        private Quest debugconds() {
                System.out.println("DEBUG: [");
                for (int[] cond : conds) {
                        System.out.print("\t[ ");
                        for (int c : cond)
                                System.out.printf("%d, ", c);
                        System.out.print("]\n");
                }
                System.out.print("]\n");
                return this;
        }

        public String toString() {
                return String.format(
                                "%d|%s|(%s)|%d|%d|%s", id, coords,
                                conds[0][0] == 0 && conds[0][1] == 0 && conds[1][0] == 0
                                                && conds[1][1] == 0 ? ""
                                                                : String
                                                                                .format("%s,%s",
                                                                                                conds[0][0] == 0 && conds[0][1] == 0
                                                                                                                ? ""
                                                                                                                : String.format("(%d,%s)",
                                                                                                                                conds[0][0],
                                                                                                                                conds[0][1] == 0 ? ""
                                                                                                                                                : String.format("%d",
                                                                                                                                                                conds[0][1])),
                                                                                                conds[1][0] == 0 && conds[1][1] == 0
                                                                                                                ? ""
                                                                                                                : String.format("(%d,%s)",
                                                                                                                                conds[1][0],
                                                                                                                                conds[1][1] == 0 ? ""
                                                                                                                                                : String.format("%d",
                                                                                                                                                                conds[1][1]))),
                                t, xp, name);
        }
}
