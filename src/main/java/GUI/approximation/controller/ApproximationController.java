package GUI.approximation.controller;

import GUI.approximation.algorithms.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.Map;

public class ApproximationController {

    @FXML
    public LineChart<String, Double> tenPointsChart;

    @FXML
    public LineChart<String, Double> tenPointsWithOneErrorChart;

    @FXML
    public GridPane tenPointsWithOneErrorGridPane;

    @FXML
    public GridPane fourPointsGridPane;

    @FXML
    public GridPane tenPointsGridPane;

    @FXML
    public GridPane sinGridPane;
    public TextField userPointTextField;
    public Button userPointButton;

    @FXML
    private LineChart<String, Double> sinChart;

    @FXML
    private LineChart<String, Double> fourPointsChart;

    @FXML
    private void initialize() {

        ApproximationStrategy strategy = new SinusFunction();
        fillChart(strategy, sinChart);
        loadPointsGridPane(strategy, sinGridPane);

        strategy = new FourPointsFunction();
        fillChart(strategy, fourPointsChart);
        loadPointsGridPane(strategy, fourPointsGridPane);

        strategy = new TenPointsFunction();
        fillChart(strategy, tenPointsChart);
        loadPointsGridPane(strategy, tenPointsGridPane);

        strategy = new TenPointsWithOneError();
        fillChart(strategy, tenPointsWithOneErrorChart);
        loadPointsGridPane(strategy, tenPointsWithOneErrorGridPane);
    }

    private void loadPointsGridPane(ApproximationStrategy strategy, GridPane tenPointsWithOneErrorGridPane) {
        double[] coordinateX = strategy.getCoordinateX();

        for (int i = 0; i < strategy.getNumberOfPoints(); i++) {
            tenPointsWithOneErrorGridPane.add(new Label(String.valueOf(coordinateX[i])), i, 0);

        }

        double[] coordinateY = strategy.getCoordinateY();

        for (int i = 0; i < strategy.getNumberOfPoints(); i++) {
            tenPointsWithOneErrorGridPane.add(new Label(String.valueOf(coordinateY[i])), i, 1);

        }
    }

    private void fillChart(ApproximationStrategy strategy, LineChart<String, Double> chart) {
        strategy.calc();
        Map<String, Double> data = strategy.getFunctionPoints();
        Map<String, Double> xyAsMap = strategy.getCoordinateXCoordinateYAsMap();
        chart.getData().add(transferDataToXYChart(data));
        chart.getData().add(transferDataToXYChart(xyAsMap));
    }

    private XYChart.Series<String, Double> transferDataToXYChart(Map<String, Double> points) {
        XYChart.Series<String, Double> series = new XYChart.Series<>();

        ObservableList<XYChart.Data<String, Double>> listPoints = FXCollections.observableArrayList();
        points.forEach((s, aDouble) -> listPoints.add(new XYChart.Data<>(s, aDouble)));

        series.getData().addAll(listPoints);
        return series;
    }


    private Label fourPointsLabel = new Label();
    private Label tenPointsLabel = new Label();
    private Label tenPointsWithOneErrorLabel = new Label();

    public void calculateUserPoint() {
        try {
            ApproximationStrategy strategy = new FourPointsFunction();
            strategy.calc();
            Double userPoint = strategy.getUserCalculatedPoint(Double.parseDouble(userPointTextField.getText()));
            fourPointsLabel.setText(String.valueOf(userPoint));
            sinGridPane.add(fourPointsLabel, 0, 1);

            strategy = new TenPointsFunction();
            strategy.calc();
            userPoint = strategy.getUserCalculatedPoint(Double.parseDouble(userPointTextField.getText()));
            tenPointsLabel.setText(String.valueOf(userPoint));
            sinGridPane.add(tenPointsLabel, 1, 1);

            strategy = new TenPointsWithOneError();
            strategy.calc();
            userPoint = strategy.getUserCalculatedPoint(Double.parseDouble(userPointTextField.getText()));
            tenPointsWithOneErrorLabel.setText(String.valueOf(userPoint));
            sinGridPane.add(tenPointsWithOneErrorLabel, 2, 1);
        } catch (NumberFormatException e) {
            //Do nothing
        }


    }
}
