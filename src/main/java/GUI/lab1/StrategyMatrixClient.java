package GUI.lab1;

import javafx.scene.control.TextField;

import java.util.HashMap;

public class StrategyMatrixClient {
    private InterfaceMatrixStrategy method;

    public StrategyMatrixClient(InterfaceMatrixStrategy method) {
        this.method = method;
    }

    StrategyMatrixClient() {

    }

    double[] findSolution() {
        return method.findSolution();
    }

    HashMap<Integer, Double> getErrors(double[] unknowns, TextField[][] matrixTextField) {
        return method.getErrors(unknowns, matrixTextField);
    }

    double getDeterminant() {
        return method.getDeterminant();
    }

    int getNumberOfIteration(){
        return method.getNumberOfIteration();
    }

    public InterfaceMatrixStrategy getMethod() {
        return method;
    }

    void setMethod(InterfaceMatrixStrategy method) {
        this.method = method;
    }
}
