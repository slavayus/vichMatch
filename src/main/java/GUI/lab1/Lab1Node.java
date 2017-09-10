package GUI.lab1;

import GUI.LabNode;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;

import java.util.Random;

/**
 * Created by slavik on 08.09.17.
 */
public class Lab1Node extends LabNode {

    private int lengthMatrixX;
    private TextField[][] matrixTextField = new TextField[21][21];


    @Override
    protected void draw() {
        VBox rootVBox = new VBox();
        rootVBox.getChildren().addAll(getVBoxForSizeMatrix(),getDataFromFile());


        node = rootVBox;
    }

    private Node getDataFromFile() {
        ColumnConstraints columnConstraints0 = new ColumnConstraints(15);

        Button dataFromFileButton = new Button("Получить данные из файла");

        GridPane dataFromFileGridPane = new GridPane();
        dataFromFileGridPane.add(dataFromFileButton,1,1);
        RowConstraints rowConstraints0 = new RowConstraints(7);

        dataFromFileGridPane.getColumnConstraints().add(columnConstraints0);
        dataFromFileGridPane.getRowConstraints().add(rowConstraints0);
        return dataFromFileGridPane;
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
//                System.out.println("YEE");
            }
            try {
                this.lengthMatrixX = Integer.parseInt(textFieldX.getText());
                if (lengthMatrixX <= 0 || lengthMatrixX > 20) {
                    throw new NumberFormatException();
                }
                messageLabel.setText("");
                HBox sizeMatrixHBox = new HBox();
                sizeMatrixHBox.getChildren().addAll(getMatrix(), getRandomMatrixData());
                sizeMatrixVbox.getChildren().addAll(sizeMatrixHBox);
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

    private Node getRandomMatrixData() {
        ColumnConstraints columnConstraints0 = new ColumnConstraints(5);
        RowConstraints rowConstraints0 = new RowConstraints(15);



        GridPane randomMatrixDataGridPane = new GridPane();

        Button randomButton = new Button("Заполнить случайными числами");
        randomMatrixDataGridPane.add(randomButton,1,1);
        randomButton.setOnMouseClicked(mouseEvent -> {
            for (int i = 1; i < lengthMatrixX+1 ; i++) {
                for (int j = 1; j < lengthMatrixX+1; j++) {
                    matrixTextField[i][j].setText(Integer.toString((int)(Math.random()*30)));
                }
            }
        });

        randomMatrixDataGridPane.getColumnConstraints().add(columnConstraints0);
        randomMatrixDataGridPane.getRowConstraints().add(rowConstraints0);

        return randomMatrixDataGridPane;
    }

    private Node getMatrix() {
        GridPane matrixGridPane = new GridPane();

        for (int i = 1; i < lengthMatrixX+1; i++) {
            matrixGridPane.add(new Label("  x"+i),i,0);
        }

        for (int i = 1; i < lengthMatrixX+1; i++) {
            Label columnLabel = new Label(" "+Integer.toString(i));
            columnLabel.setMinWidth(25);
            columnLabel.setContentDisplay(ContentDisplay.CENTER);
            matrixGridPane.add(columnLabel,0,i);
        }

        for (int i = 1; i < lengthMatrixX+1; i++) {
            for (int j = 1; j < lengthMatrixX+1; j++) {
                matrixTextField[i][j] = new TextField("0");
                matrixTextField[i][j].setMaxWidth(40);
                matrixGridPane.add(matrixTextField[i][j], i, j);
            }
        }

        return matrixGridPane;
    }
}
