package butinfo.model;

import java.util.Vector;

public class Parser {
    private String input;

    public Parser(String feed_me) {
        input = feed_me;
    }

    private void space0() {
        input = input.stripLeading();
    }

    private void separator() {
        input = input.charAt(0) == '|' ? input.substring(1) : input;
    }

    private void tupleDelimiter() {
        input = input.charAt(0) == '(' ? input.substring(1) : input;
    }

    private void tupleSeparator() {
        input = input.charAt(0) == ',' ? input.substring(1) : input;
    }

    private int integer() {
        int result = 0;
        char temp;
        while (Character.isDigit(temp = input.charAt(0))) {
            result = result * 10 + temp - '0';
            input = input.substring(1);
        }
        return result;
    }

    private Vector<Integer> tuple() {
        Vector<Integer> result = new Vector<>();
        tupleDelimiter();
        if (input.charAt(0) != ')')
            while (true) {
                result.add(integer());
                if (input.charAt(0) == ')')
                    break;
                tupleSeparator();
                space0();
                if (input.charAt(0) == ')')
                    break;
            }
        input = input.substring(1);
        return result;
    }

    private Vector<Vector<Integer>> tuples() {
        Vector<Vector<Integer>> result = new Vector<>();
        tupleDelimiter();
        if (input.charAt(0) != ')')
            while (true) {
                result.add(tuple());
                if (input.charAt(0) == ')')
                    break;
                tupleSeparator();
                space0();
                if (input.charAt(0) == ')')
                    break;
            }
        input = input.substring(1);
        return result;
    }

    public Quest parse() {
        Coordinates questCoordinates;
        Vector<Vector<Integer>> antecedents;
        int questId, time, exp;
        String title;

        // ID
        questId = integer();

        separator();

        // coordinates
        Vector<Integer> result1 = tuple();
        questCoordinates = new Coordinates(result1.get(0), result1.get(1));

        separator();

        // antecedents
        antecedents = tuples();

        separator();

        // time
        time = integer();

        separator();

        // EXP
        exp = integer();

        separator();

        // title
        title = input;

        return new Quest(questId, questCoordinates, antecedents, time, exp, title);
    }
}
