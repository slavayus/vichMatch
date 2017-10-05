package GUI;

import GUI.integration.view.IntegrationNode;
import GUI.lab1.Lab1Node;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by slavik on 08.09.17.
 */
public class MainWindow extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Вычислительная математика");
        FlowPane rootFlowPane = new FlowPane();


        rootFlowPane.getChildren().addAll(setButtons(rootFlowPane));
        primaryStage.setScene(new Scene(rootFlowPane, 1200, 800));
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    private List<Button> setButtons(FlowPane rootFlowPane) {
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
            strategyClient.setConcreteNode(new Lab1Node());
            rootFlowPane.getChildren().add(strategyClient.doDraw());
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
            strategyClient.setConcreteNode(new IntegrationNode());
            rootFlowPane.getChildren().add(strategyClient.doDraw());
        });
        buttonList.add(buttonLab2);


        Button buttonLab3 = new Button("lab_3");
        buttonLab3.setPrefWidth(240);
        buttonList.add(buttonLab3);


        Button buttonLab4 = new Button("lab_4");
        buttonLab4.setPrefWidth(240);
        buttonList.add(buttonLab4);


        Button buttonLab5 = new Button("lab_5");
        buttonLab5.setPrefWidth(240);
        buttonList.add(buttonLab5);
        return buttonList;
    }


}
