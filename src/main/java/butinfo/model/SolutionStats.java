package butinfo.model;

import java.util.Vector;

public class SolutionStats {
    int time, xp, distance, nb;

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
