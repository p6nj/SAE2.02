package butinfo;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import java.util.stream.IntStream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import butinfo.model.Scenario;

public class Exhaust {

    @ParameterizedTest(name = "Scénario {0} OK")
    @MethodSource("argsProviderFactory")
    public void exhaust(int input) {
        assertDoesNotThrow(() -> new Scenario(input).exhaustive1());
    }

    static IntStream argsProviderFactory() {
        return IntStream.range(0, 10);
    }
}