package GUI.approximation.algorithms;

public class FourPointsFunction extends ApproximationStrategy {

    public FourPointsFunction() {
        super(3);
    }

    protected void initializePoints() {
        /*x[0] = 1;
        x[1] = 2;
        x[2] = 3;
        x[3] = 4;
        x[4] = 5;

        y[0] = 5;
        y[1] = 3;
        y[2] = 2.5;
        y[3] = 2;
        y[4] = 0;*/
        x[0] = 1.3;
        x[1] = 2.5;
        x[2] = 4.2;
        x[3] = 5.9;

        y[0] = 0.96;
        y[1] = 0.59;
        y[2] = -0.87;
        y[3] = -0.37;
    }
}