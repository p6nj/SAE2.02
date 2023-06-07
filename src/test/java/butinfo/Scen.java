package butinfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import butinfo.model.Scenario;

public class Scen {

  @ParameterizedTest(name = "Scénario {0} OK")
  @MethodSource("argsProviderFactory")
  public void scen(int input) throws NoSuchElementException, FileNotFoundException, IOException {
    String fileContents = Scenario.readScenarioToString(input);
    Scenario testScenario = new Scenario(input);
    assertEquals(fileContents, testScenario.toString());
  }

  static IntStream argsProviderFactory() {
    return IntStream.range(0, 11);
  }
}