package GUI;

import GUI.approximation.view.ApproximationNode;
import GUI.integration.view.IntegrationNode;
import GUI.lab1.Lab1Node;
import GUI.ode.view.OdeNode;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by slavik on 08.09.17.
 */
public class MainWindow extends Application {

    private Map<String, Node> nodes = new HashMap<>();

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Вычислительная математика");
        FlowPane rootFlowPane = new FlowPane();


        Scene scene = new Scene(rootFlowPane, 1200, 800);

        rootFlowPane.getChildren().addAll(setButtons(rootFlowPane, scene));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    private List<Button> setButtons(FlowPane rootFlowPane, Scene scene) {
        List<Button> buttonList = new ArrayList<>();
        StrategyClient strategyClient = new StrategyClient();

        //Height = 25
        Button buttonLab1 = new Button("lab_1");
        buttonLab1.setPrefWidth(240);
        buttonLab1.setOnMouseClicked(keyEvent -> {
            try {
                rootFlowPane.getChildren().remove(5);
            } catch (Exception e) {
//                e.printStackTrace();
            }

            if (nodes.containsKey("1")) {
                rootFlowPane.getChildren().add(nodes.get("1"));
            } else {
                strategyClient.setConcreteNode(new Lab1Node());
                Node node = strategyClient.doDraw();
                rootFlowPane.getChildren().add(node);
                nodes.put("1", node);
            }
        });
        buttonList.add(buttonLab1);


        Button buttonLab2 = new Button("lab_2");
        buttonLab2.setPrefWidth(240);
        buttonLab2.setOnMouseClicked(keyEvent -> {
            try {
                rootFlowPane.getChildren().remove(5);
            } catch (Exception e) {
//                e.printStackTrace();
            }

            if (nodes.containsKey("2")) {
                rootFlowPane.getChildren().add(nodes.get("2"));
            } else {
                strategyClient.setConcreteNode(new IntegrationNode());
                Node node = strategyClient.doDraw();
                rootFlowPane.getChildren().add(node);
                nodes.put("2", node);
            }
        });
        buttonList.add(buttonLab2);


        Button buttonLab3 = new Button("lab_3");
        buttonLab3.setPrefWidth(240);
        buttonLab3.setOnMouseClicked(keyEvent -> {
            scene.getStylesheets().add(MainWindow.class.getResource("/GUI/approximation/css/chart.css").toExternalForm());
            try {
                rootFlowPane.getChildren().remove(5);
            } catch (Exception e) {
//                e.printStackTrace();
            }

            if (nodes.containsKey("3")) {
                rootFlowPane.getChildren().add(nodes.get("3"));
            } else {
                strategyClient.setConcreteNode(new ApproximationNode());
                Node node = strategyClient.doDraw();
                rootFlowPane.getChildren().add(node);
                nodes.put("3", node);
            }
        });
        buttonList.add(buttonLab3);


        Button buttonLab4 = new Button("lab_4");
        buttonLab4.setPrefWidth(240);
        buttonLab4.setOnMouseClicked(mouseEvent -> {
            try {
                rootFlowPane.getChildren().remove(5);
            } catch (Exception e) {
//                e.printStackTrace();
            }

            if (nodes.containsKey("4")) {
                rootFlowPane.getChildren().add(nodes.get("4"));
            } else {
                strategyClient.setConcreteNode(new OdeNode());
                Node node = strategyClient.doDraw();
                rootFlowPane.getChildren().add(node);
                nodes.put("4", node);
            }

        });
        buttonList.add(buttonLab4);


        Button buttonLab5 = new Button("lab_5");
        buttonLab5.setPrefWidth(240);
        buttonList.add(buttonLab5);
        return buttonList;
    }


}
