package GUI.approximation.algorithms;

public class FourPointsFunction extends ApproximationStrategy {

    public FourPointsFunction() {
        super(3);
    }

    protected void initializePoints() {
        coordinateX[0] = 1.3;
        coordinateX[1] = 2.5;
        coordinateX[2] = 4.2;
        coordinateX[3] = 5.9;

        coordinateY[0] = 0.96;
        coordinateY[1] = 0.59;
        coordinateY[2] = -0.87;
        coordinateY[3] = -0.37;
    }
}