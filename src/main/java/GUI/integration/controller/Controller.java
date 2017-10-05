package GUI.integration.controller;

import GUI.integration.algorithms.IntegrationStrategyClient;
import GUI.integration.algorithms.Rectangle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class Controller {
    @FXML
    private ComboBox<String> variantComboBox;

    @FXML
    private TextField textFieldA;

    @FXML
    private TextField textFieldB;

    @FXML
    private TextField textFieldEpsilon;

    @FXML
    private Label messageLabel;

    @FXML
    private Button calculateButton;

    @FXML
    private Label leftIntegration;

    @FXML
    private Label middleIntegration;

    @FXML
    private Label rightIntegration;

    @FXML
    private Label numberOfSplit;

    @FXML
    private Label error;


    @FXML
    private void initialize() {
        ObservableList<String> tasks = FXCollections.observableArrayList("sin(x)*cos(x)", "cos(x)*tn(x)*sin(x)", "x^3+x^2+x+10");
        variantComboBox.setItems(tasks);
    }

    @FXML
    private void setCalculateButton() {
        String stringDataFromTextFieldA = textFieldA.getText();
        String stringDataFromTextFieldB = textFieldB.getText();
        String stringEpsilon = textFieldEpsilon.getText();

        int intDataFromTextFieldA;
        int intDataFromTextFieldB;
        double doubleEpsilon;
        if (stringDataFromTextFieldA.equals("") || stringDataFromTextFieldB.equals("") || stringEpsilon == null || variantComboBox.getValue() == null) {
            messageLabel.setStyle("-fx-text-fill: red");
            messageLabel.setText("Введите данные");
        } else {
            try {
                intDataFromTextFieldA = Integer.parseInt(stringDataFromTextFieldA);
                intDataFromTextFieldB = Integer.parseInt(stringDataFromTextFieldB);
                doubleEpsilon = Double.parseDouble(stringEpsilon);
                messageLabel.setText("Вычислено");
                messageLabel.setStyle("-fx-text-fill: green");
                calculate(intDataFromTextFieldA, intDataFromTextFieldB, doubleEpsilon);
            } catch (NumberFormatException ex) {
                messageLabel.setStyle("-fx-text-fill: red");
                messageLabel.setText("Данные введены некорректно");
            }
        }

    }

    private void calculate(int intDataFromTextFieldA, int intDataFromTextFieldB, double doubleEpsilon) {
        IntegrationStrategyClient integrationStrategyClient = new IntegrationStrategyClient(new Rectangle());
        integrationStrategyClient.getMethod().calc(intDataFromTextFieldA, intDataFromTextFieldB, doubleEpsilon, variantComboBox.getValue());

        leftIntegration.setText(String.valueOf(integrationStrategyClient.getMethod().getLeftIntegration()));
        middleIntegration.setText(String.valueOf(integrationStrategyClient.getMethod().getMiddleIntegration()));
        rightIntegration.setText(String.valueOf(integrationStrategyClient.getMethod().getRightIntegration()));
        numberOfSplit.setText(String.valueOf(integrationStrategyClient.getMethod().getNumberOfSplit()));
        error.setText(String.valueOf(integrationStrategyClient.getMethod().getNumberOfError()));
    }
}
