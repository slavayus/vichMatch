package GUI.lab1;

import GUI.LabsNode;
import algorithms.lab1.Gauss;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.HashMap;

/**
 * Created by slavik on 08.09.17.
 */
public class Lab1Node extends LabsNode {

    private int lengthMatrixX;
    private TextField[][] matrixTextField = new TextField[25][25];
    private ComboBox<String> comboBox = new ComboBox<>();
    private Label messageLabel;


    @Override
    protected void draw() {
        VBox rootVBox = new VBox();
        rootVBox.getChildren().addAll(getVBoxForSizeMatrix(), getDataFromFile());


        node = rootVBox;
    }

    private Node getDataFromFile() {
        ColumnConstraints columnConstraints0 = new ColumnConstraints(15);

        Button dataFromFileButton = new Button("Получить данные из файла");

        GridPane dataFromFileGridPane = new GridPane();
        dataFromFileGridPane.add(dataFromFileButton, 1, 1);
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

        messageLabel = new Label();
        sizeMatrixGridPane.add(messageLabel, 7, 1);

        VBox sizeMatrixVBox = new VBox();
        sizeMatrixVBox.getChildren().add(sizeMatrixGridPane);


        button.setOnMouseClicked(mouseEvent -> {
            try {
                sizeMatrixVBox.getChildren().remove(1);
            } catch (Exception e) {
//                System.out.println("YEE");
            }
            try {
                this.lengthMatrixX = Integer.parseInt(textFieldX.getText());
                if (lengthMatrixX <= 0 || lengthMatrixX > 20) {
                    throw new NumberFormatException();
                }
                messageLabel.setText("");

                VBox determinantVBox = new VBox(getRandomMatrixData(), getResolveMatrix(), getMethod());

                HBox sizeMatrixHBox = new HBox();
                sizeMatrixHBox.getChildren().addAll(getMatrix(), determinantVBox);
                sizeMatrixVBox.getChildren().addAll(sizeMatrixHBox);
            } catch (NumberFormatException e) {
                messageLabel.setText("   " + "Размер матрицы неверен");
                messageLabel.setStyle("-fx-text-fill: red;");
            }
        });

        sizeMatrixGridPane.getColumnConstraints().addAll(columnConstraints0, columnConstraints1);
        sizeMatrixGridPane.getRowConstraints().add(rowConstraints0);
        sizeMatrixGridPane.setGridLinesVisible(false);


        return sizeMatrixVBox;
    }

    private Node getResolveMatrix() {
        ColumnConstraints columnConstraints0 = new ColumnConstraints(5);
        ColumnConstraints columnConstraints1 = new ColumnConstraints(205);
        RowConstraints rowConstraints0 = new RowConstraints(15);
        RowConstraints rowConstraints1 = new RowConstraints(20);


        GridPane resolveMatrixGridPane = new GridPane();
        resolveMatrixGridPane.add(new Label("Определитель матрицы: "), 1, 1);
        Label determinantMessage = new Label("No");
        resolveMatrixGridPane.add(determinantMessage, 2, 1);

        Button resolveMatrixButton = new Button("Вычислить определитель");
        resolveMatrixButton.setOnMouseClicked(mouseEvent -> {
            if (checkDataInMatrix()) {
                String method = comboBox.getValue();
                switch (method) {
                    case "Гаусса":
                        Gauss<Double> gauss = new Gauss<>(matrixTextField, lengthMatrixX);
                        gauss.calculate();
                        gauss.soutMatr();
                        HashMap<Integer, Double> unknowns = gauss.getUnknowns();
                        if(unknowns == null){
                            messageLabel.setText("  Не удалось посчитать определитель");
                            messageLabel.setStyle("-fx-text-fill: red;");
                        }else {
                           unknowns.forEach((integer, aDouble) -> System.out.println(aDouble));
                        }
                        break;
                    case "Гаусса с выбором главного элемента":
                        break;
                    case "Простых итераций":
                        break;
                    case "Гаусса-Зейделя":
                        break;
                }

            }


        });


        resolveMatrixGridPane.add(resolveMatrixButton, 1, 2);

        resolveMatrixGridPane.getColumnConstraints().addAll(columnConstraints0, columnConstraints1);
        resolveMatrixGridPane.getRowConstraints().addAll(rowConstraints0, rowConstraints1);

        return resolveMatrixGridPane;


    }

    private boolean checkDataInMatrix() {
        messageLabel.setText("");
        try {
            for (int i = 1; i <= lengthMatrixX; i++) {
                for (int j = 1; j <= lengthMatrixX + 1; j++) {
                    Double.parseDouble(matrixTextField[j][i].getText());
                }
            }
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            messageLabel.setText("  Данные в матрицу введены некорректно");
            messageLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

    }

    private Node getRandomMatrixData() {
        ColumnConstraints columnConstraints0 = new ColumnConstraints(5);
        RowConstraints rowConstraints0 = new RowConstraints(15);


        GridPane randomMatrixDataGridPane = new GridPane();

        Button randomButton = new Button("Заполнить случайными числами");
        randomMatrixDataGridPane.add(randomButton, 1, 1);
        randomButton.setOnMouseClicked(mouseEvent -> {
            for (int i = 1; i < lengthMatrixX + 2; i++) {
                for (int j = 1; j < lengthMatrixX + 1; j++) {
                    matrixTextField[i][j].setText(Integer.toString((int) (Math.random() * 30)));
                }
            }
        });

        randomMatrixDataGridPane.getColumnConstraints().add(columnConstraints0);
        randomMatrixDataGridPane.getRowConstraints().add(rowConstraints0);

        return randomMatrixDataGridPane;
    }

    private Node getMatrix() {
        GridPane matrixGridPane = new GridPane();

        for (int i = 1; i < lengthMatrixX + 1; i++) {
            matrixGridPane.add(new Label("  x" + i), i, 0);
        }

        for (int i = 1; i < lengthMatrixX + 1; i++) {
            Label columnLabel = new Label(" " + Integer.toString(i));
            columnLabel.setMinWidth(25);
            columnLabel.setContentDisplay(ContentDisplay.CENTER);
            matrixGridPane.add(columnLabel, 0, i);
        }

        for (int i = 1; i < lengthMatrixX + 1; i++) {
            for (int j = 1; j < lengthMatrixX + 1; j++) {
                matrixTextField[i][j] = new TextField("0");
                matrixTextField[i][j].setMaxWidth(40);
                matrixGridPane.add(matrixTextField[i][j], i, j);
            }
        }

        for (int i = 1; i < lengthMatrixX + 1; i++) {
            Label columnLabel = new Label(" = ");
            columnLabel.setMinWidth(25);
            columnLabel.setContentDisplay(ContentDisplay.CENTER);
            matrixGridPane.add(columnLabel, 22, i);
        }

        for (int i = 1; i < lengthMatrixX + 1; i++) {
            matrixTextField[lengthMatrixX + 1][i] = new TextField("0");
            matrixTextField[lengthMatrixX + 1][i].setMaxWidth(40);
            matrixGridPane.add(matrixTextField[lengthMatrixX + 1][i], 23, i);
        }

        return matrixGridPane;
    }

    private Node getMethod() {
        comboBox.getItems().addAll(
                "Гаусса",
                "Гаусса с выбором главного элемента",
                "Простых итераций",
                "Гаусса-Зейделя"
        );
        comboBox.getSelectionModel().selectFirst();

        comboBox.valueProperty().addListener((observableValue, s, t1) -> {

            System.out.println(t1);
        });

        ColumnConstraints columnConstraints0 = new ColumnConstraints(5);
        RowConstraints rowConstraints0 = new RowConstraints(7);


        GridPane ComboBoxGridPane = new GridPane();
        ComboBoxGridPane.add(comboBox, 2, 1);


        ComboBoxGridPane.getColumnConstraints().addAll(columnConstraints0);
        ComboBoxGridPane.getRowConstraints().addAll(rowConstraints0);

        return ComboBoxGridPane;


    }
}
