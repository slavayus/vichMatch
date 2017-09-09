package GUI.lab1;

import GUI.LabNode;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import java.awt.*;

/**
 * Created by slavik on 08.09.17.
 */
public class Lab1Node extends LabNode {

    private int lengthMatrixX;
    private int lengthMatrixY;

    @Override
    protected void draw() {
        VBox rootVBox = new VBox();
        rootVBox.getChildren().addAll(getVBoxForSizeMatrix());


        node = rootVBox;
    }

    private Node getVBoxForSizeMatrix() {
        ColumnConstraints columnConstraints0 = new ColumnConstraints(15);
        ColumnConstraints columnConstraints1 = new ColumnConstraints(135);
        RowConstraints rowConstraints0 = new RowConstraints(7);


        GridPane sizeMatrixGridPane = new GridPane();
        sizeMatrixGridPane.add(new Label("Размер матрицы:"), 1, 1);

        TextField textFieldX = new TextField();
        textFieldX.setPromptText("X");
        textFieldX.setMaxWidth(40);
        sizeMatrixGridPane.add(textFieldX, 2, 1);

        sizeMatrixGridPane.add(new Label("   x   "), 3, 1);

        TextField textFieldY = new TextField();
        textFieldY.setPromptText("Y");
        textFieldY.setMaxWidth(40);
        sizeMatrixGridPane.add(textFieldY, 4, 1);

        sizeMatrixGridPane.add(new Label("   "), 5, 1);

        Button button = new Button("oк");
        sizeMatrixGridPane.add(button, 6, 1);

        Label messageLabel = new Label();
        sizeMatrixGridPane.add(messageLabel, 7, 1);

        button.setOnMouseClicked(mouseEvent -> {
            try {
                this.lengthMatrixX = Integer.parseInt(textFieldX.getText());
                this.lengthMatrixY = Integer.parseInt(textFieldY.getText());
                if (lengthMatrixY <= 0 || lengthMatrixY > 20 || lengthMatrixX <= 0 || lengthMatrixX > 20) {
                    throw new NumberFormatException();
                }
                messageLabel.setText("");
            } catch (NumberFormatException e) {
                messageLabel.setText("   " + "Данные некорректны");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });

        sizeMatrixGridPane.getColumnConstraints().addAll(columnConstraints0, columnConstraints1);
        sizeMatrixGridPane.getRowConstraints().add(rowConstraints0);
        sizeMatrixGridPane.setGridLinesVisible(false);

        return sizeMatrixGridPane;
    }
}
