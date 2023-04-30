package butinfo.model;

import java.util.Vector;

public class Quest {
        public int id;
        private int t, xp;
        private Coordinates coords;
        private Vector<Vector<Integer>> conds;
        private String name;
        private Boolean done = false;
        private final static String sep = "|";

        public Quest(int questId, Coordinates questCoordinates,
                        Vector<Vector<Integer>> antecedents, int time, int exp, String title) {
                id = questId;
                coords = questCoordinates;
                conds = antecedents;
                t = time;
                xp = exp;
                name = title;
        }

        public void complete() {
                done = true;
        }

        public Boolean isComplete() {
                return done;
        }

        private Quest debugconds() {
                System.out.println("DEBUG: [");
                for (Vector<Integer> cond : conds) {
                        System.out.print("\t[ ");
                        for (int c : cond)
                                System.out.printf("%d, ", c);
                        System.out.print("]\n");
                }
                System.out.print("]\n");
                return this;
        }

        private String stringConds() {
                String result = "(";
                for (Vector<Integer> conds : this.conds) {
                        result = result + "(";
                        for (int cond : conds) {
                                result = result + Integer.toString(cond) + ", ";
                        }
                        result = result.substring(0, result.length() - (conds.size() > 1 ? 2 : 1));
                        result = result + "), ";
                }
                if (!this.conds.isEmpty())
                        result = result.substring(0, result.length() - (this.conds.size() > 1 ? 2 : 1));
                result = result + ")";
                return result;
        }

        public String toString() {
                return String.format("%d%s%s%s%s%s%d%s%d%s%s", id, sep, coords.toString(), sep, stringConds(), sep, t,
                                sep, xp, sep, name);
        }
}
