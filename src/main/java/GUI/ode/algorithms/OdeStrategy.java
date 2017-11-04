package GUI.ode.algorithms;

import java.util.LinkedList;
import java.util.List;

public abstract class OdeStrategy {
    List<Double> xCoordinates = new LinkedList<>();
    List<Double> yCoordinates = new LinkedList<>();


    public abstract void calc(double xStart, double xFinish, double step, double yZero, double yFirst);

    public List<Double> getXCoordinates() {
        return xCoordinates;
    }

    public List<Double> getYCoordinates() {
        return yCoordinates;
    }
}