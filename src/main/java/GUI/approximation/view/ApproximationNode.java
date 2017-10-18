package GUI.approximation.view;

import GUI.LabsNode;
import GUI.integration.view.IntegrationNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;

public class ApproximationNode implements LabsNode {
    @Override
    public Node draw() {
        try {
            return FXMLLoader.<Parent>load(IntegrationNode.class.getResource("/GUI/approximation/view/ApproximationView.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
            return new Label("Cannot load integration view ");
        }
    }
}
