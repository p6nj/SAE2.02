package butinfo.model;

import java.util.Vector;

public class Parser {
    private static String space0(String input) {
        return input.stripLeading();
    }

    private static String separator(String input) {
        return input.charAt(0) == '|' ? input.substring(1) : input;
    }

    private static String tupleDelimiter(String input) {
        return input.charAt(0) == '(' ? input.substring(1) : input;
    }

    private static String tupleSeparator(String input) {
        return input.charAt(0) == ',' ? input.substring(1) : input;
    }

    private static ParseResult<Integer> integer(String input) {
        int result = 0;
        char temp;
        while (Character.isDigit(temp = input.charAt(0))) {
            result = result * 10 + temp - '0';
            input = input.substring(1);
        }
        return new ParseResult<Integer>(input, result);
    }

    private static ParseResult<Vector<Integer>> tuple(String input) {
        Vector<Integer> result = new Vector<>();
        input = tupleDelimiter(input);
        if (input.charAt(0) != ')')
            while (true) {
                ParseResult<Integer> temp = integer(input);
                result.add(temp.getResult());
                if (input.charAt(0) == ')')
                    break;
                input = space0(tupleSeparator(temp.getRest()));
                if (input.charAt(0) == ')')
                    break;
            }
        return new ParseResult<Vector<Integer>>(input.substring(1), result);
    }

    private static ParseResult<Vector<Vector<Integer>>> tuples(String input) {
        Vector<Vector<Integer>> result = new Vector<>();
        input = tupleDelimiter(input);
        if (input.charAt(0) != ')')
            while (true) {
                ParseResult<Vector<Integer>> temp = tuple(input);
                result.add(temp.getResult());
                if (input.charAt(0) == ')')
                    break;
                input = space0(tupleSeparator(temp.getRest()));
                if (input.charAt(0) == ')')
                    break;
            }
        return new ParseResult<Vector<Vector<Integer>>>(input.substring(1), result);
    }

    public static Quest parse(String quest) {
        Coordinates questCoordinates;
        Vector<Vector<Integer>> antecedents;
        int questId, time, exp;
        String title;

        // ID
        ParseResult<Integer> result0 = integer(quest);
        quest = result0.getRest();
        questId = result0.getResult();

        quest = separator(quest);

        // coordinates
        ParseResult<Vector<Integer>> result1 = tuple(quest);
        quest = result1.getRest();
        questCoordinates = new Coordinates(result1.getResult().get(0), result1.getResult().get(1));

        quest = separator(quest);

        // antecedents
        ParseResult<Vector<Vector<Integer>>> result2 = tuples(quest);
        quest = result2.getRest();
        antecedents = result2.getResult();

        quest = separator(quest);

        // time
        result0 = integer(quest);
        quest = result0.getRest();
        time = result0.getResult();

        quest = separator(quest);

        // EXP
        result0 = integer(quest);
        quest = result0.getRest();
        exp = result0.getResult();

        // title
        title = separator(quest);

        return new Quest(questId, questCoordinates, antecedents, time, exp, title);
    }
}
