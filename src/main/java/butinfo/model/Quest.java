package butinfo.model;

import java.util.Vector;

public class Quest implements Comparable<Quest> {
        public int id;
        private int t, xp;

        public Coordinates coords;
        private Vector<Vector<Integer>> __antecedents;
        private Vector<Vector<Quest>> antecedents;
        private String name;
        private final static String sep = "|";

        public Quest(int questId, Coordinates questCoordinates,
                        Vector<Vector<Integer>> antecedents, int time, int exp, String title) {
                id = questId;
                coords = questCoordinates;
                this.__antecedents = antecedents;
                t = time;
                xp = exp;
                name = title;
        }

        /**
         * Turns all antecedents into quest objects. Cannot be done at construction time
         * if the other quests aren't made yet.
         * 
         * @param context: source scenario to get the other quests
         */
        public void antecedentsFromInt(Scenario context) {
                antecedents = new Vector<>();
                for (Vector<Integer> antclass : __antecedents) {
                        Vector<Quest> temp = new Vector<>();
                        for (int ant : antclass)
                                temp.add(context.getQuest(ant));
                        antecedents.add(temp);
                }
        }

        public int antecedentNumber() {
                int result = 0;
                for (Vector<Integer> antclass : __antecedents)
                        result += antclass.size();
                return result;
        }

        /**
         * Shows the accessibility from a set of done quests. If at least one quest from
         * each antecedent group is done, this quest can be done.
         * 
         * @param done: quests already done
         * @return boolean satisfying the affirmation "This quest can be accessed."
         * @see java.util.Vector#contains(Object)
         */
        public boolean accessible(Vector<Quest> done) {
                boolean temp = false;
                for (Vector<Quest> antclass : antecedents) {
                        for (Quest ant : antclass) {
                                if (done.contains(ant)) {
                                        temp = true;
                                        break;
                                }
                        }
                        if (!temp)
                                return false;
                        temp = false;
                }
                return true;
        }

        /**
         * Returns a CSV-like string representation of the conditions for
         * {@link #toString()}.
         * 
         * 
         * @return a string representation of the conditions in tuples as seen in the
         *         CSV files
         */
        private String stringAntecedents() {
                String result = "(";
                for (Vector<Integer> antecedents : this.__antecedents) {
                        result = result + "(";
                        for (int cond : antecedents) {
                                result = result + Integer.toString(cond) + ", ";
                        }
                        result = result.substring(0, result.length() - (antecedents.size() > 1 ? 2 : 1));
                        result = result + "), ";
                }
                if (!this.antecedents.isEmpty())
                        result = result.substring(0, result.length() - (this.antecedents.size() > 1 ? 2 : 1));
                result = result + ")";
                return result;
        }

        /**
         * String representation of the quest with CSV-like structure.
         * 
         * 
         * * @return a string representation of the quest in tuples as seen in the CSV
         * files
         */
        public String toString() {
                return String.format("%d%s%s%s%s%s%d%s%d%s%s", id, sep, coords.toString(), sep,
                                stringAntecedents(), sep, t,
                                sep, xp, sep, name);
        }

        public Vector<Vector<Quest>> getAntecedents() {
                return antecedents;
        }

        public int compareTo(Quest other) {
                return coords.compareTo(other.coords);
        }

        public int getId() {
                return id;
        }

        public int getXp() {
                return xp;
        }

        public int getTime() {
                return t;
        }
}
