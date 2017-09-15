package GUI;

import javafx.scene.Node;

public abstract class LabsNode {
    protected Node node;

    public LabsNode() {
        draw();
    }

    protected abstract void draw();

    Node getNode() {
        return node;
    }
}
