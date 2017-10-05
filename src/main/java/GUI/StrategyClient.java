package GUI;

import javafx.scene.Node;

class StrategyClient {
    private LabsNode node;

    StrategyClient(LabsNode labsNode) {
        this.node = labsNode;
    }

    StrategyClient() {

    }

    Node doDraw() {
        return node.draw();
    }

    void setConcreteNode(LabsNode node) {
        this.node = node;
    }

    public LabsNode getNode() {
        return node;
    }
}
