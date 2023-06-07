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

public class Access {

    @ParameterizedTest(name = "{index}: Quêtes effectuées {0} ; quête actuelle {1} ; résultat attendu {2}")
    @MethodSource("argsProviderFactory")
    public void access(Vector<Quest> done, Quest current, boolean expected) {
        assertEquals(expected, current.accessible(done));
    }

    public static Stream<Arguments> argsProviderFactory()
            throws NoSuchElementException, FileNotFoundException, IOException {
        Scenario test = new Scenario("1|(0,0)|()|0|50|1" + System.lineSeparator() + "2|(0,0)|()|0|50|2"
                + System.lineSeparator()
                + "3|(0,0)|((2,),)|0|50|3" + System.lineSeparator() + "4|(0,0)|((3,),)|0|50|4" + System.lineSeparator()
                + "0|(0,0)|((4,),)|0|50|0");
        return Stream.of(
                Arguments.of(new Vector<>(Arrays.asList(test.getQuest(1), test.getQuest(2))), test.getQuest(3), true),
                Arguments.of(new Vector<>(Arrays.asList(test.getQuest(1), test.getQuest(2))), test.getQuest(4), false),
                Arguments.of(new Vector<>(), test.getQuest(1), true),
                Arguments.of(
                        new Vector<>(
                                Arrays.asList(test.getQuest(1), test.getQuest(2), test.getQuest(3), test.getQuest(4))),
                        test.getQuest(0), true),
                Arguments.of(new Vector<>(Arrays.asList(test.getQuest(4))), test.getQuest(0), false),
                Arguments.of(new Vector<>(Arrays.asList(test.getQuest(1))), test.getQuest(0), false),
                Arguments.of(new Vector<>(Arrays.asList(test.getQuest(1), test.getQuest(2), test.getQuest(3))),
                        test.getQuest(0), false));
    }

}