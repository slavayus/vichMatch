package GUI.lab1;

import GUI.LabsNode;
import algorithms.lab1.Gauss;
import algorithms.lab1.Zeydel;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import java.util.Arrays;
import java.util.HashMap;

/**
 * Created by slavik on 08.09.17.
 */
public class Lab1Node implements LabsNode {

    private int lengthMatrixX;
    private TextField[][] matrixTextField = new TextField[25][25];
    private ComboBox<String> comboBox = new ComboBox<>();
    private Label messageLabel;
    private TextField[] answersTextField = new TextField[25];
    private TextField[] errorsTextField = new TextField[25];
    private DataFromFile dataFromFile = new DataFromFile();
    private TextField epsilon = new TextField("0.0001");
    private Label determinantMessage = new Label();
    private Label diagonalMessage = new Label();

    @Override
    public Node draw() {
        VBox rootVBox = new VBox();
        rootVBox.getChildren().addAll(getVBoxForSizeMatrix());
        return rootVBox;
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
                sizeMatrixVBox.getChildren().remove(1);
                sizeMatrixVBox.getChildren().remove(1);
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

                VBox determinantVBox = new VBox(dataFromFile.getButton(matrixTextField, lengthMatrixX, epsilon), getRandomMatrixData(), getResolveMatrix(), getMethod());

                HBox sizeMatrixHBox = new HBox();
                sizeMatrixHBox.getChildren().addAll(getMatrix(), determinantVBox);
                sizeMatrixVBox.getChildren().addAll(sizeMatrixHBox, getAnswers());
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
        RowConstraints rowConstraints0 = new RowConstraints(7);


        GridPane resolveMatrixGridPane = new GridPane();

        Button resolveMatrixButton = new Button("Вычислить определитель");
        StrategyMatrixClient strategyMatrixClient = new StrategyMatrixClient();
        resolveMatrixButton.setOnMouseClicked(mouseEvent -> {
            if (checkDataInMatrix()) {
                String method = comboBox.getValue();

                TextField[][] copyMatrixTextField = new TextField[25][25];
                for (int i = 1; i < lengthMatrixX+2; i++) {
                    for (int j = 1; j < lengthMatrixX+1; j++) {
                        copyMatrixTextField[i][j] = new TextField(matrixTextField[i][j].getText());
                    }
                }
                switch (method) {
                    case "Гаусса":
                        strategyMatrixClient.setMethod(new Gauss(matrixTextField, lengthMatrixX));
                        double[] unknowns = strategyMatrixClient.findSolution();
                        if (unknowns == null) {
                            determinantMessage.setText("  Не удалось посчитать определитель");
                            determinantMessage.setStyle("-fx-text-fill: red;");
                        } else {
                            determinantMessage.setText(String.valueOf(strategyMatrixClient.getDeterminant()));


                            for (int i = 1; i < lengthMatrixX+1; i++) {
                                answersTextField[i].setText(String.valueOf(unknowns[i]));
                            }

                            HashMap<Integer, Double> errors = strategyMatrixClient.getErrors(unknowns, copyMatrixTextField);
                            errors.forEach((integer, aDouble) -> errorsTextField[integer + 1].setText(aDouble.toString()));
                        }
                        break;
                    case "Гаусса с выбором главного элемента":
                        break;
                    case "Простых итераций":
                        break;
                    case "Гаусса-Зейделя":
                        strategyMatrixClient.setMethod(new Zeydel(matrixTextField, lengthMatrixX, Double.parseDouble(epsilon.getText())));
                        try {
                            double[] answers = strategyMatrixClient.findSolution();

                            for (int i = 0; i < lengthMatrixX; i++) {
                                answersTextField[i + 1].setText(String.valueOf(answers[i]));
                            }

                            HashMap<Integer, Double> errors = strategyMatrixClient.getErrors(answers, matrixTextField);
                            errors.forEach((integer, aDouble) -> errorsTextField[integer + 1].setText(aDouble.toString()));

                            determinantMessage.setText(String.valueOf(String.valueOf(strategyMatrixClient.getNumberOfIteration())));
                            ZeydelCheckerDiagonal diagonal = new ZeydelCheckerDiagonal();
                            diagonalMessage.setText(diagonal.getDiagonal(lengthMatrixX, copyMatrixTextField) ? "Да" : "Нет");
                        } catch (NumberFormatException e) {
                            determinantMessage.setText("Не удалось посчитать");
                            determinantMessage.setStyle("-fx-text-fill: red;");
                        }
                        break;
                }

            }


        });

        resolveMatrixGridPane.add(resolveMatrixButton, 1, 3);

        resolveMatrixGridPane.getColumnConstraints().addAll(columnConstraints0);
        resolveMatrixGridPane.getRowConstraints().addAll(rowConstraints0);

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
//            ex.printStackTrace();
            messageLabel.setText("  Данные в матрицу введены некорректно");
            messageLabel.setStyle("-fx-text-fill: red;");
            return false;
        }

    }

    private Node getRandomMatrixData() {
        ColumnConstraints columnConstraints0 = new ColumnConstraints(5);
        RowConstraints rowConstraints0 = new RowConstraints(7);


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
        comboBox = new ComboBox<>();
        comboBox.getItems().addAll(
                "Гаусса",
//                "Гаусса с выбором главного элемента",
//                "Простых итераций",
                "Гаусса-Зейделя"
        );
        comboBox.getSelectionModel().selectFirst();


        ColumnConstraints columnConstraints0 = new ColumnConstraints(5);
        RowConstraints rowConstraints0 = new RowConstraints(7);


        GridPane comboBoxGridPane = new GridPane();
        comboBoxGridPane.add(comboBox, 1, 1);


        comboBoxGridPane.getColumnConstraints().addAll(columnConstraints0);
        comboBoxGridPane.getRowConstraints().addAll(rowConstraints0);


        Label information = new Label("Определитель матрицы: ");
        comboBoxGridPane.add(information, 1, 2);
        comboBoxGridPane.add(determinantMessage, 2, 2);
        Label diagonal = new Label("Диагональ: ");
        Label precision = new Label("Введите точность: ");
        comboBox.valueProperty().addListener((observableValue, s, t1) -> {
            try {
                comboBoxGridPane.getChildren().remove(diagonal);
                comboBoxGridPane.getChildren().remove(diagonalMessage);
                comboBoxGridPane.getChildren().remove(precision);
                comboBoxGridPane.getChildren().remove(epsilon);
                determinantMessage.setText("");
            } catch (Exception e) {
//                e.printStackTrace();
            }

            switch (t1) {
                case "Гаусса":
                    information.setText("Определитель матрицы: ");
                    break;
                case "Гаусса с выбором главного элемента":
                    break;
                case "Простых итераций":
                    break;
                case "Гаусса-Зейделя":
                    information.setText("Число итераций: ");
                    comboBoxGridPane.add(diagonal, 1, 6);
                    comboBoxGridPane.add(diagonalMessage, 2, 6);
                    comboBoxGridPane.add(precision, 1, 5);
                    epsilon.setMaxWidth(100);
                    comboBoxGridPane.add(epsilon, 2, 5);
                    break;
            }
            System.out.println(t1);
        });


        return comboBoxGridPane;


    }

    private Node getAnswers() {

        GridPane answersGridPane = new GridPane();
        for (int i = 1; i < lengthMatrixX + 1; i++) {
            answersGridPane.add(new Label("  x" + i), i, 1);
        }

        for (int i = 1; i < lengthMatrixX + 1; i++) {
            answersTextField[i] = new TextField("0");
            answersTextField[i].setMaxWidth(40);
            answersGridPane.add(answersTextField[i], i, 2);
        }

        GridPane errorsGridPane = new GridPane();
        for (int i = 1; i < lengthMatrixX + 1; i++) {
            errorsGridPane.add(new Label("  " + i), i, 1);
        }

        for (int i = 1; i < lengthMatrixX + 1; i++) {
            errorsTextField[i] = new TextField("0");
            errorsTextField[i].setMaxWidth(40);
            errorsGridPane.add(errorsTextField[i], i, 2);
        }

        VBox vBoxAnswers = new VBox();
        vBoxAnswers.getChildren().addAll(new Label("Столбец неизвестных:"), answersGridPane, new Label("Столбец невязок:"), errorsGridPane);
        vBoxAnswers.setPadding(new Insets(5, 0, 0, 25));
        return vBoxAnswers;
    }
}
