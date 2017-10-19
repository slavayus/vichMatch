package GUI.approximation.algorithms;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class ApproximationStrategy {
    private int numberOfPoints;
    private double[] h;
    private double[] l;
    private double[] delta;
    private double[] lambda;
    private double[] c;
    private double[] d;
    private double[] b;
    double[] x;
    double[] y;
    Map<String, Double> functionPoints = new LinkedHashMap<>();

    ApproximationStrategy() {
    }

    ApproximationStrategy(int numberOfPoints) {
        this.numberOfPoints = numberOfPoints;
        this.h = new double[numberOfPoints + 1];
        this.l = new double[numberOfPoints + 1];
        this.delta = new double[numberOfPoints + 1];
        this.lambda = new double[numberOfPoints + 1];
        this.c = new double[numberOfPoints + 1];
        this.d = new double[numberOfPoints + 1];
        this.b = new double[numberOfPoints + 1];
        this.x = new double[numberOfPoints + 1];
        this.y = new double[numberOfPoints + 1];
    }


    protected abstract void initializePoints();

    public void calc() {
        initializePoints();

        for (int i = 1; i <= numberOfPoints; i++) {
            h[i] = x[i] - x[i - 1];
            l[i] = (y[i] - y[i - 1]) / h[i];
        }

        delta[1] = -h[2] / (2 * (h[1] + h[2]));
        lambda[1] = 1.5 * (l[2] - l[1]) / (h[1] + h[2]);
        for (int i = 3; i <= numberOfPoints; i++) {
            delta[i - 1] = -h[i] / (2 * h[i - 1] + 2 * h[i] + h[i - 1] * delta[i - 2]);
            lambda[i - 1] = (3 * l[i] - 3 * l[i - 1] - h[i - 1] * lambda[i - 2]) /
                    (2 * h[i - 1] + 2 * h[i] + h[i - 1] * delta[i - 2]);
        }
        c[0] = 0;
        c[numberOfPoints] = 0;
        for (int i = numberOfPoints; i >= 2; i--) {
            c[i - 1] = delta[i - 1] * c[i] + lambda[i - 1];
        }
        for (int i = 1; i <= numberOfPoints; i++) {
            d[i] = (c[i] - c[i - 1]) / (3 * h[i]);
            b[i] = l[i] + (2 * c[i] * h[i] + h[i] * c[i - 1]) / 3;
        }

        printResult();
        calcPoints();

    }


    private void calcPoints() {
        for (int i = 1; i <= numberOfPoints; i++) {
            for (double j = x[i - 1]; j <= x[i]; j += 0.1) {
                functionPoints.put(String.format("%.2f", j), getCubFunctionResult(i, j - x[i]));

            }
        }
    }


    private Double getCubFunctionResult(int i, double hk) {
        return y[i] + b[i] * hk + c[i] * Math.pow(hk, 2) + d[i] * Math.pow(hk, 3);
    }


    private void printResult() {
        System.out.printf("\nA[k]\tB[k]\tC[k]\tD[k]\n");
        for (int i = 1; i <= numberOfPoints; i++) {
            System.out.printf("%f\t%f\t%f\t%f\n", y[i], b[i], c[i], d[i]);
        }
    }

    public Map<String, Double> getFunctionPoints() {
        return functionPoints;
    }

    public Map<String, Double> getXYAsMap() {
        LinkedHashMap<String, Double> xyMap = new LinkedHashMap<>();
        for (int i = 0; i < numberOfPoints; i++) {
            xyMap.put(String.format("%.2f", x[i]), y[i]);
        }

        return xyMap;
    }
}
