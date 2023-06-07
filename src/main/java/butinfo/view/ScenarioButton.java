package butinfo.view;

import javafx.scene.control.Button;

public class ScenarioButton extends Button implements Comparable<ScenarioButton> {
    private int n;

    public ScenarioButton(int n) {
        super(Integer.toString(n));
        this.n = n;
    }

    public int getN() {
        return n;
    }

    @Override
    public int compareTo(ScenarioButton other) {
        return Integer.compare(n, other.getN());
    }
}
