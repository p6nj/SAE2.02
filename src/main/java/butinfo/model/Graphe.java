package butinfo.model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Vector;

public class Graphe extends Scenario{
    
    public Graphe(int id) throws NoSuchElementException, FileNotFoundException, IOException{
        super(id);
    }

    private ArrayList<Quest> quetesDispos(boolean [] quetesFaits){
        ArrayList<Quest> temp = new ArrayList<>();
        boolean caAMarche = true;
        for (Quest q: quests) {
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

    public ArrayList<Quest>  gloutton1(){
        ArrayList<Quest> resultats = new ArrayList<>();
        Quest quete = premiereQuete(); ;
        boolean [] queteFaits = new boolean[quests.size()];
        do {
            queteFaits[quete.getId()] = true;
            resultats.add(quete);
            quete = queteProche(quete);
        } while (!queteFaits[0]);
        return resultats;
    } 

    public Quest premiereQuete(){
        for (Quest quete : quests){
            Vector<Vector<Integer>> antecedants = quete.getAntecedants() ;
            if (antecedants == new Vector<Vector<Integer>>()){
                return quete;
            }
        }
        return null;
    }



}
