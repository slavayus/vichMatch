package GUI;

import javafx.scene.Node;

class BackEnd {
    private LabsNode node;

    BackEnd(LabsNode labsNode) {
        this.node = labsNode;
    }

    Node getNode() {
        return node.getNode();
    }
}
