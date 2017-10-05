package GUI.integration.view;

import GUI.LabsNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;

public class IntegrationNode implements LabsNode {
    @Override
    public Node draw() {
        try {
            return FXMLLoader.<Parent>load(IntegrationNode.class.getResource("/GUI/integration/view/IntegrationView.fxml"));
        } catch (IOException e) {
            return new Label("Cannot load integration view ");
        }

    }
}
