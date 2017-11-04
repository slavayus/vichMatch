package GUI.ode.controller;

import GUI.ode.algorithms.OdeStrategy;
import GUI.ode.algorithms.PredictorAndCorrector;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.List;
import java.util.Map;

public class OdeController {
    @FXML
    public TextField textFieldYFirst;
    private double yFirst;

    @FXML
    public TextField textFieldYZero;
    private double yZero;

    @FXML
    public TextField textFieldXStart;
    private double xStart;

    @FXML
    public TextField textFieldXFinish;
    private double xFinish;

    @FXML
    public TextField textFieldStep;
    private double step;

    @FXML
    public LineChart<String, Double> functionLineChart;

    @FXML
    public Label errorLabel;

    public void calculateButton() {
        ErrorInputData errorInputData = checkInputData();
        if (errorInputData == ErrorInputData.ALL_IS_WELL) {
            errorLabel.setStyle("-fx-text-fill: green");
            errorLabel.setText(errorInputData.getMessage());
            drawChart();
        } else {
            errorLabel.setStyle("-fx-text-fill: red");
            errorLabel.setText(errorInputData.getMessage());
        }
    }

    private void drawChart() {
        OdeStrategy odeStrategy = new PredictorAndCorrector();
        odeStrategy.calc(xStart, xFinish, step, yZero, yFirst);

        List<Double> xCoordinates = odeStrategy.getXCoordinates();
        List<Double> yCoordinates = odeStrategy.getYCoordinates();
        functionLineChart.getData().add(transferDataToXYChart(xCoordinates, yCoordinates));

    }

    private XYChart.Series<String, Double> transferDataToXYChart(List<Double> xCoordinates, List<Double> yCoordinates) {
        XYChart.Series<String, Double> series = new XYChart.Series<>();
        ObservableList<XYChart.Data<String, Double>> listPoints = FXCollections.observableArrayList();

        for (int i = 0; i < xCoordinates.size(); i++) {
            listPoints.add(new XYChart.Data<>(String.format("%.2f", xCoordinates.get(i)), yCoordinates.get(i)));
        }

        series.getData().addAll(listPoints);
        return series;
    }

    private ErrorInputData checkInputData() {
        ErrorInputData errorInputData = ErrorInputData.ALL_IS_WELL;
        try {
            xStart = Double.parseDouble(textFieldXStart.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_X_START;
        }

        try {
            xFinish = Double.parseDouble(textFieldXFinish.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_X_FINISH;
        }

        try {
            step = Double.parseDouble(textFieldStep.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_STEP;
        }


        try {
            yZero = Double.parseDouble(textFieldYZero.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_Y_ZERO;
        }


        try {
            yFirst = Double.parseDouble(textFieldYFirst.getText());
        } catch (Exception ex) {
            errorInputData = ErrorInputData.ERROR_Y_FIRST;
        }

        return errorInputData;
    }
}
