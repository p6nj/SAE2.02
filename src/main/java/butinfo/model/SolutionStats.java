package butinfo.model;

import java.util.Vector;

public class SolutionStats {
    int time, xp, distance, nb;
    Vector<Quest> solution;

    public Vector<Quest> getSolution() {
        return solution;
    }

    public int getTime() {
        return time;
    }

    public int getXp() {
        return xp;
    }

    public int getDistance() {
        return distance;
    }

    public int getNb() {
        return nb;
    }

    public SolutionStats(Vector<Quest> solution) {
        this.solution = solution;
        nb = solution.size();
        time = xp = distance = 0;
        Quest temp = solution.firstElement();
        for (Quest q : solution) {
            xp += q.getXp();
            time += q.getTime();
            distance += temp.compareTo(q);
            temp = q;
        }
    }
}
