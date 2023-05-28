package butinfo.model;

import java.util.Vector;

public class Quest implements Comparable<Quest>{
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

        public void antecedentsFromInt(Scenario context){
                antecedents=new Vector<>();
                for (Vector<Integer> antclass : __antecedents) {
                        Vector<Quest>temp=new Vector<>();
                        for (int ant : antclass) 
                                temp.add(context.getQuest(ant));
                        antecedents.add(temp);
                }
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

        public Vector<Vector<Quest>> getAntecedents(){
                return antecedents;
        }

        public int compareTo(Quest other){
                return coords.compareTo(other.coords);
        }

        public int getId(){
                return id;
        }
}
