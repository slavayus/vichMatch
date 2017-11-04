package GUI.ode.algorithms;

import org.junit.Test;

import static org.junit.Assert.*;

public class PredictorAndCorrectorTest {
    @Test
    public void calc() throws Exception {
        OdeStrategy odeStrategy = new PredictorAndCorrector();
        odeStrategy.calc(0,1,0.001, 0 ,0.004);

//        System.out.println(odeStrategy.xCoordinates);
//        System.out.println(odeStrategy.yCoordinates);

    }

}