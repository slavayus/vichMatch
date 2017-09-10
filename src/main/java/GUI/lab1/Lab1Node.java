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

/**
 * Created by slavik on 08.09.17.
 */
public class Lab1Node extends LabNode {

    private int lengthMatrixX;

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

        sizeMatrixGridPane.add(new Label("   "), 5, 1);

        Button button = new Button("oк");
        sizeMatrixGridPane.add(button, 6, 1);

        Label messageLabel = new Label();
        sizeMatrixGridPane.add(messageLabel, 7, 1);

        VBox sizeMatrixVbox = new VBox();
        sizeMatrixVbox.getChildren().add(sizeMatrixGridPane);


        button.setOnMouseClicked(mouseEvent -> {
            try {
                sizeMatrixVbox.getChildren().remove(1);
            } catch (Exception e) {
                System.out.println("YEE");
            }
            try {
                this.lengthMatrixX = Integer.parseInt(textFieldX.getText());
                if (lengthMatrixX <= 0 || lengthMatrixX > 20) {
                    throw new NumberFormatException();
                }
                messageLabel.setText("");
                sizeMatrixVbox.getChildren().add(getMatrix());
            } catch (NumberFormatException e) {
                messageLabel.setText("   " + "Размер матрицы неверен");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });

        sizeMatrixGridPane.getColumnConstraints().addAll(columnConstraints0, columnConstraints1);
        sizeMatrixGridPane.getRowConstraints().add(rowConstraints0);
        sizeMatrixGridPane.setGridLinesVisible(false);


        return sizeMatrixVbox;
    }

    private Node getMatrix() {
        GridPane matrixGridPane = new GridPane();

        TextField[][] matrixTextField = new TextField[20][20];

        for (int i = 0; i < lengthMatrixX; i++) {
            for (int j = 0; j < lengthMatrixX; j++) {
                matrixTextField[i][j] = new TextField("0");
                matrixTextField[i][j].setMaxWidth(40);
                matrixGridPane.add(matrixTextField[i][j], i, j);
            }
        }

        return matrixGridPane;
    }
}
