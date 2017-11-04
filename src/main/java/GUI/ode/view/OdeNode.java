package GUI.ode.view;

import GUI.LabsNode;
import GUI.integration.view.IntegrationNode;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;

public class OdeNode implements LabsNode {
    @Override
    public Node draw() {
        try {
            return FXMLLoader.<Parent>load(IntegrationNode.class.getResource("/GUI/ode/view/odeView.fxml"));
        } catch (IOException e) {
            return new Label("Cannot load integration view ");
        }

    }
}
