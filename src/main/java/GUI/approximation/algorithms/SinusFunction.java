package GUI.approximation.algorithms;

public class SinusFunction extends ApproximationStrategy {
    @Override
    public void initializePoints() {
        throw new UnsupportedOperationException();
    }

    public void calc() {
        for (double i = 0; i < 2 * Math.PI; i += 0.1) {
            functionPoints.put(String.format("%.2f", i), Math.sin(i));
        }
    }
}
