package butinfo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.stream.IntStream;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import butinfo.model.Scenario;

public class TestScenario {

  @ParameterizedTest(name = "Scénario {0} OK")
  @ValueSource(ints = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 })
  public void scenario_assert(int input) throws NoSuchElementException, FileNotFoundException, IOException {
    String fileContents = Scenario.readScenarioToString(input);
    Scenario testScenario = new Scenario(input);
    assertEquals(fileContents, testScenario.toString());
  }

  static IntStream argsProviderFactory() {
    return IntStream.range(0, 10);
  }
}