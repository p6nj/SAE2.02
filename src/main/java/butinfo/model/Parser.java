package butinfo.model;

import java.util.Vector;

public class Parser {
    private String input;

    public Parser(String feed_me) {
        input = feed_me;
    }

    /**
     * Strip no or any space at the beginning of the input.
     */
    private void space0() {
        input = input.stripLeading();
    }

    /**
     * Remove a field separator from the input if one is found.
     */
    private void separator() {
        input = input.charAt(0) == '|' ? input.substring(1) : input;
    }

    /**
     * Remove a tuple delimiter from the input if one is found.
     */
    private void tupleDelimiter() {
        input = input.charAt(0) == '(' ? input.substring(1) : input;
    }

    /**
     * Removes the tuple separator from the input if one is found.
     */
    private void tupleSeparator() {
        input = input.charAt(0) == ',' ? input.substring(1) : input;
    }

    /**
     * Parses and returns an integer from the input string. If none is found,
     * returns 0.
     */
    private int digit0() {
        int result = 0;
        char temp;
        // Get the next digit from input.
        while (Character.isDigit(temp = input.charAt(0))) {
            result = result * 10 + temp - '0';
            input = input.substring(1);
        }
        return result;
    }

    /**
     * Parses and returns an integer tuple (that starts with a tuple delimiter and
     * is separated by a tuple separator).
     */
    private Vector<Integer> tuple() {
        Vector<Integer> result = new Vector<>();
        tupleDelimiter();
        if (input.charAt(0) != ')')
            while (true) {
                result.add(digit0());
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

    /**
     * Parses and returns a tuple of integer tuples (that starts with a tuple
     * delimiter and
     * is separated by a tuple separator) using {@link #tuple()}.
     */
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

    /**
     * Parses the input and returns a Quest. This is the method that should be
     * called by the user when they want to parse data that has been written to the
     * file.
     * 
     * 
     * @return The data that has been read from the file and is ready to be written
     *         to the Quest object
     */
    public Quest parse() {
        Coordinates questCoordinates;
        Vector<Vector<Integer>> antecedents;
        int questId, time, exp;
        String title;

        // ID
        questId = digit0();

        separator();

        // coordinates
        Vector<Integer> result1 = tuple();
        questCoordinates = new Coordinates(result1.get(0), result1.get(1));

        separator();

        // antecedents
        antecedents = tuples();

        separator();

        // time
        time = digit0();

        separator();

        // EXP
        exp = digit0();

        separator();

        // title
        title = input;

        return new Quest(questId, questCoordinates, antecedents, time, exp, title);
    }
}
