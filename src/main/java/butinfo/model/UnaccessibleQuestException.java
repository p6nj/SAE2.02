package butinfo.model;

/**
 * A function may need a specific quest to be reachable, aka accessible. If it
 * can't deal with an unreachable quest, this exception is thrown.
 */
public class UnaccessibleQuestException extends Exception {
    private Quest q;

    public UnaccessibleQuestException(Quest q) {
        super(q.toString());
        this.q = q;
    }

    public Quest getQuest() {
        return q;
    }
}
