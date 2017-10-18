package GUI.approximation.algorithms;

public class TenPointsFunction extends ApproximationStrategy {

    public TenPointsFunction() {
        super(9);
    }

    @Override
    protected void initializePoints() {
        x[0] = 0.7;
        x[1] = 0.9;
        x[2] = 1.3;
        x[3] = 1.6;
        x[4] = 2.1;
        x[5] = 2.9;
        x[6] = 3.5;
        x[7] = 4.2;
        x[8] = 5.5;
        x[9] = 6.2;

        y[0] = 0.644;
        y[1] = 0.783;
        y[2] = 0.963;
        y[3] = 0.999;
        y[4] = 0.863;
        y[5] = 0.239;
        y[6] = -0.35;
        y[7] = -0.87;
        y[8] = -0.705;
        y[9] = -0.08;

    }
}
