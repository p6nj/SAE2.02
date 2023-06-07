package butinfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Vector;
import java.util.stream.Stream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import butinfo.model.Quest;
import butinfo.model.Scenario;

public class Effic {

    @ParameterizedTest(name = "{0}")
    @MethodSource("argsProviderFactory")
    public void effic(Scenario s, Vector<Quest> expected) {
        assertEquals(expected, s.efficace1());
    }

    static Stream<Arguments> argsProviderFactory() throws NoSuchElementException, FileNotFoundException, IOException {
        Scenario alpha = new Scenario("1|(0,0)|()|4|10|A" + System.lineSeparator() + "2|(1, 1)|((1,),)|4|10|B"
                + System.lineSeparator() + "0|(1, 1)|((2,),)|4|10|C");
        Scenario s0 = new Scenario(0);
        Scenario beta = new Scenario("1|(0,0)|()|4|10|A" + System.lineSeparator() + "2|(1, 1)|((1,),)|4|10|B"
                + System.lineSeparator() + "3|(4, 9)|()|4|10|D"
                + System.lineSeparator() + "0|(1, 1)|((2,),)|4|30|C");
        Scenario s1 = new Scenario(1);
        return Stream
                .of(Arguments.of(alpha,
                        new Vector<>(Arrays.asList(alpha.getQuest(1), alpha.getQuest(2), alpha.getQuest(0)))),
                        Arguments.of(s0,
                                new Vector<>(Arrays.asList(s0.getQuest(1), s0.getQuest(2), s0.getQuest(4),
                                        s0.getQuest(0)))),
                        Arguments.of(beta,
                                new Vector<>(Arrays.asList(beta.getQuest(1), beta.getQuest(2), beta.getQuest(3),
                                        beta.getQuest(0)))),
                        Arguments.of(s1,
                                new Vector<>(Arrays.asList(s1.getQuest(1), s1.getQuest(4), s1.getQuest(3),
                                        s1.getQuest(2), s1.getQuest(5),
                                        s1.getQuest(0)))));
    }
}