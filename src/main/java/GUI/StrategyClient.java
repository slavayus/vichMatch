package GUI;

import javafx.scene.Node;

class StrategyClient {
    private LabsNode node;

    StrategyClient(LabsNode labsNode) {
        this.node = labsNode;
    }

    Node doDraw() {
        return node.draw();
    }

    public void setConcreteNode(LabsNode node) {
        this.node = node;
    }

    public LabsNode getNode() {
        return node;
    }
}
