package GUI.approximation.controller;

import GUI.approximation.algorithms.ApproximationStrategy;
import GUI.approximation.algorithms.FourPointsFunction;
import GUI.approximation.algorithms.SinusFunction;
import GUI.approximation.algorithms.TenPointsFunction;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;

public class ApproximationController {

    @FXML
    public LineChart<String, Double> tenPointsChart;

    @FXML
    private LineChart<String, Double> sinChart;

    @FXML
    private LineChart<String, Double> fourPointsChart;

    @FXML
    private LineChart<String, Double> lineChart;

    @FXML
    private void initialize() {

        ApproximationStrategy strategy = new SinusFunction();
        fillChart(strategy, sinChart);

        strategy = new FourPointsFunction();
        fillChart(strategy, fourPointsChart);

        strategy = new TenPointsFunction();
        fillChart(strategy, tenPointsChart);
    }

    private void fillChart(ApproximationStrategy strategy, LineChart<String, Double> chart) {
        strategy.calc();
        chart.getData().add(strategy.getDataAsXYChart());
    }
}
