package GUI.approximation.controller;

import GUI.approximation.algorithms.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.util.Map;

public class ApproximationController {

    @FXML
    public LineChart<String, Double> tenPointsChart;

    @FXML
    public LineChart<String, Double> tenPointsWithOneErrorChart;

    @FXML
    private LineChart<String, Double> sinChart;

    @FXML
    private LineChart<String, Double> fourPointsChart;

    @FXML
    private void initialize() {

        ApproximationStrategy strategy = new SinusFunction();
        fillChart(strategy, sinChart);

        strategy = new FourPointsFunction();
        fillChart(strategy, fourPointsChart);

        strategy = new TenPointsFunction();
        fillChart(strategy, tenPointsChart);

        strategy = new TenPointsWithOneError();
        fillChart(strategy, tenPointsWithOneErrorChart);
    }

    private void fillChart(ApproximationStrategy strategy, LineChart<String, Double> chart) {
        strategy.calc();
        Map<String, Double> data = strategy.getFunctionPoints();
        Map<String, Double> xyAsMap = strategy.getXYAsMap();
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
}
