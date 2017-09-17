package GUI.lab1;


import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.Scanner;

class DataFromFile {
    private File file;


    Node getButton(TextField[][] matrixTextField, int lengthMatrixX, TextField epsilon) {
        ColumnConstraints columnConstraints0 = new ColumnConstraints(7);

        Button dataFromFileButton = new Button("Получить данные из файла");

        dataFromFileButton.setOnMouseClicked(mouseEvent -> {
            FileChooser fileChooser = new FileChooser();
            this.file = fileChooser.showOpenDialog(new Stage());
            try (Scanner scanner = new Scanner(file)) {
                for (int i = 1; i < lengthMatrixX + 1; i++) {
                    for (int j = 1; j < lengthMatrixX + 2; j++) {
                        matrixTextField[j][i].setText(scanner.hasNext() ? scanner.next() : "");
                    }
                }
                epsilon.setText(String.valueOf(scanner.hasNext() ? scanner.next() : 0.0001));
            } catch (Exception e) {
            }
        });

        GridPane dataFromFileGridPane = new GridPane();
        dataFromFileGridPane.add(dataFromFileButton, 1, 1);
        RowConstraints rowConstraints0 = new RowConstraints(10);

        dataFromFileGridPane.getColumnConstraints().add(columnConstraints0);
        dataFromFileGridPane.getRowConstraints().add(rowConstraints0);
        return dataFromFileGridPane;
    }
}
