package butinfo;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;

import butinfo.model.Scenario;

@RunWith(Parameterized.class)
public class TestScenario {
  @Parameter
  public int input;

  @Parameterized.Parameters
  public static final List<Object[]> data() {
    return Arrays.asList(new Object[][] {
        { 0 }, { 1 }, { 2 }, { 3 }, { 4 }, { 5 }, { 6 }, { 7 }, { 8 }, { 9 }, { 10 }
    });
  }

  @Test
  public void scenario_assert() throws NoSuchElementException, FileNotFoundException, IOException {
    String fileContents = Scenario.readScenarioToString(input);
    Scenario testScenario = new Scenario(input);
    assertEquals(fileContents, testScenario.toString());
  }
}