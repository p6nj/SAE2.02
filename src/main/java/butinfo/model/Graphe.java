package butinfo.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Vector;

public class Graphe extends Scenario {
    private ArrayList<Quest> resultats = new ArrayList<>();

    public Graphe(int id) throws NoSuchElementException, FileNotFoundException, IOException {
        super(id);
        gloutton1();
    }

    private ArrayList<Quest> quetesDispos(boolean[] quetesFaits) {
        ArrayList<Quest> temp = new ArrayList<>();
        boolean caAMarche = true;
        for (Quest q : quests) {
            for (Vector<Integer> a1 : q.getAntecedants()) {
                for (Integer a2 : a1) {
                    if (!quetesFaits[a2]) {
                        caAMarche = false;
                        break;
                    }
                }
                if (!caAMarche)
                    break;
            }
            if (caAMarche)
                temp.add(q);
            caAMarche = true;
        }
        return temp;
    }

    private Quest queteProche(Quest quete, boolean[] queteFaits) {
        Quest result = null;
        for (Quest q : quetesDispos(queteFaits)) {
            if (result == null || (result.compareTo(quete) > q.compareTo(quete)))
                result = q;
        }
        return result;
    }

    public void gloutton1() {
        Quest quete = premiereQuete();
        boolean[] queteFaits = new boolean[quests.size()];
        do {
            queteFaits[quete.getId()] = true;
            resultats.add(quete);
            quete = queteProche(quete, queteFaits);
        } while (!queteFaits[0]);
    }

    public Quest premiereQuete() {
        for (Quest quete : quests) {
            Vector<Vector<Integer>> antecedants = quete.getAntecedants();
            if (antecedants.isEmpty()) {
                return quete;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        String result = "";
        for (Quest q : resultats) {
            result = result + q.getId() + ',';
        }
        return result;
    }

}
