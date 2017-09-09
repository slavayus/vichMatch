package GUI;

import GUI.LabNode;
import javafx.scene.Node;

class BackEnd {
    private LabNode node;

    BackEnd(LabNode labNode) {
        this.node = labNode;
    }

    Node getNode() {
        return node.getNode();
    }
}
