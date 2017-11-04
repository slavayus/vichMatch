package GUI.ode.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class OdeController {
    @FXML
    public TextField textFieldYFirst;

    @FXML
    public TextField textFieldYZero;

    @FXML
    public TextField textFieldXStart;

    @FXML
    public TextField textFieldXFinish;

    @FXML
    public TextField textFieldStep;

    @FXML
    public LineChart functionLineChart;

    @FXML
    public Label errorLabel;

    public void calculateButton() {
        ErrorInputData errorInputData = checkInputData();
        if (errorInputData == ErrorInputData.ALL_IS_WELL) {
            errorLabel.setStyle("-fx-text-fill: green");
            errorLabel.setText(errorInputData.getMessage());
            //do something
        } else {
            errorLabel.setStyle("-fx-text-fill: red");
            errorLabel.setText(errorInputData.getMessage());
        }
    }


    private ErrorInputData checkInputData() {
        ErrorInputData errorInputData = ErrorInputData.ALL_IS_WELL;
        try {
            Double.parseDouble(textFieldXStart.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_X_START;
        }

        try {
            Double.parseDouble(textFieldXFinish.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_X_FINISH;
        }

        try {
            Double.parseDouble(textFieldStep.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_STEP;
        }


        try {
            Double.parseDouble(textFieldYZero.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_Y_ZERO;
        }


        try {
            Double.parseDouble(textFieldYFirst.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_Y_FIRST;
        }

        return errorInputData;
    }
}
