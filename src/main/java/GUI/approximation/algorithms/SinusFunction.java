package GUI.approximation.algorithms;

public class SinusFunction extends ApproximationStrategy {
    public SinusFunction(){
        super(0);
    }

    @Override
    public void initializePoints() {

    }

    public void calc() {
        for (double i = 0; i < 2*Math.PI; i+=0.1) {
            points.put(String.format("%.2f", i), Math.sin(i));
        }
    }
}
