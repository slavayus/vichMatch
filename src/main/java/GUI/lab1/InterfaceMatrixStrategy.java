package GUI.lab1;

import javafx.scene.control.TextField;

import java.util.HashMap;

public interface InterfaceMatrixStrategy {

    double[] findSolution();

    HashMap<Integer, Double> getErrors(double[] unknowns, TextField[][] matrixTextField);

    double getDeterminant();

    int getNumberOfIteration();
}
