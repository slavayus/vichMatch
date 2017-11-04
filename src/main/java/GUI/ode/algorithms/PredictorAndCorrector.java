package GUI.ode.algorithms;

public class PredictorAndCorrector extends OdeStrategy {
    @Override
    public void calc(double xStart, double xFinish, double step, double yZero, double yFirst) {
        xCoordinates.add(xStart);
        yCoordinates.add(yZero);

        xCoordinates.add(xStart += step);
        yCoordinates.add(yFirst);

        double yTempI;
        double p;
        double pTemp;
        double c;
        double errorCorrection;
        xStart += step;
        for (int i = 2; xFinish >= xStart; xStart += step, i++) {
            xCoordinates.add(xStart);
            yTempI = xCoordinates.get(i-1) - yCoordinates.get(i-1);
            p = yCoordinates.get(i - 2) + 2 * step * yTempI;
            pTemp = xCoordinates.get(i) - p;
            c = yCoordinates.get(i-1) + (step / 2) * (pTemp + yTempI);
            errorCorrection = (p - c) / 5;
            yCoordinates.add(c + errorCorrection);
        }
    }
}
