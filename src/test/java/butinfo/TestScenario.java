package butinfo;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;

import org.junit.Test;
import butinfo.model.Scenario;

public class TestScenario {
  @Test
  public void scenario_assert() throws NoSuchElementException, FileNotFoundException, IOException {
    String fileContents = "2|(2, 2)|((4, 1),)|2|100|explorer tombeau de Reha Thalor\n5|(4, 3)|((1, 4), (2,))|1|150|explorer jardin de Syhe Lenora\n3|(1, 0)|((4,), (1, 2))|7|100|dialoguer avec Morrigan la déesse de la mort\n1|(3, 1)|()|2|50|dialoguer avec Alaric le mage noir\n4|(4, 0)|()|2|100|explorer porte de Ifha Ennore\n0|(2, 3)|((3,),)|3|400|explorer collines de Kortorhm";
    Scenario testScenario = new Scenario(fileContents);
    assertEquals(fileContents, testScenario.toString());
  }
}
