package GUI.approximation.algorithms;

public class TenPointsWithOneError extends ApproximationStrategy {

    public TenPointsWithOneError() {
        super(9);
    }

    @Override
    protected void initializePoints() {
        coordinateX[0] = 0.7;
        coordinateX[1] = 0.9;
        coordinateX[2] = 1.3;
        coordinateX[3] = 1.6;
        coordinateX[4] = 2.1;
        coordinateX[5] = 2.9;
        coordinateX[6] = 3.5;
        coordinateX[7] = 4.2;
        coordinateX[8] = 5.5;
        coordinateX[9] = 6.2;

        coordinateY[0] = 0.644;
        coordinateY[1] = 0.783;
        coordinateY[2] = 0.963;
        coordinateY[3] = 0.999;
        coordinateY[4] = 1.2;
        coordinateY[5] = 0.239;
        coordinateY[6] = -0.35;
        coordinateY[7] = -0.87;
        coordinateY[8] = -0.705;
        coordinateY[9] = -0.08;

    }
}
