package GUI;

import javafx.scene.Node;

public abstract class LabNode {
    protected Node node;

    public LabNode() {
        draw();
    }

    protected abstract void draw();

    Node getNode() {
        return node;
    }
}
