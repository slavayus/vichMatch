package GUI.ode.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.control.TextField;

public class OdeController {
    @FXML
    public TextField textFieldStartX;


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

    public void calculateButton(ActionEvent actionEvent) {
        System.out.println("YEE");
    }
}
