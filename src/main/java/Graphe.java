import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Vector;

import butinfo.model.Quest;
import butinfo.model.Scenario;

public class Graphe extends Scenario{
    boolean [] tableauAntecedants = new boolean [quests.size()];
    
    public Graphe(int id) throws NoSuchElementException, FileNotFoundException, IOException{
        super(id);
    }

    public void QueteProche(){
        ArrayList<Quest> resultats = new ArrayList<>();
        Quest quete = premiereQuete();
        while (true) {
            
        }
    } 

    public Quest premiereQuete(){
        for (Quest quete : quests){
            Vector<Vector<Integer>> antecedants = quete.getAntecedants() ;
            if (antecedants == new Vector<Vector<Integer>>()){
                return quete;
            }
        }
    }

    public void remplitableau() {
        Quest quete = getQuest(0);
        boolean [] resultat = new boolean [quests.size()];
        while (true){
            //fonction récursive qui remplit le tableau
        }
    }

}
