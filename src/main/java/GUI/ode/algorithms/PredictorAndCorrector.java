package GUI.ode.algorithms;

public class PredictorAndCorrector extends OdeStrategy {
    @Override
    public void calc(double xStart, double xFinish, double step, double yZero, double yFirst) {
        xCoordinates.add(5d);
        xCoordinates.add(2d);
        xCoordinates.add(8d);
        xCoordinates.add(6d);

        yCoordinates.add(1d);
        yCoordinates.add(3d);
        yCoordinates.add(5d);
        yCoordinates.add(7d);
    }
}
