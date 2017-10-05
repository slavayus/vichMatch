package GUI.integration.algorithms;

import GUI.integration.algorithms.functions.FirstFunction;
import GUI.integration.algorithms.functions.SecondFunction;
import GUI.integration.algorithms.functions.ThirdFunction;

public class Rectangle implements IntegrationStrategy {
    private float leftIntegration;
    private float middleIntegration;
    private float rightIntegration;
    private int numberOfSplit;
    private float numberOfError;

    @Override
    public float getLeftIntegration() {
        return this.leftIntegration;
    }

    @Override
    public float getMiddleIntegration() {
        return this.middleIntegration;
    }

    @Override
    public float getRightIntegration() {
        return this.rightIntegration;
    }

    @Override
    public int getNumberOfSplit() {
        return this.numberOfSplit;
    }

    @Override
    public float getNumberOfError() {
        return this.numberOfError;
    }

    @Override
    public void calc(int intDataFromTextFieldA, int intDataFromTextFieldB, double doubleEpsilon, String function) {
        FunctionStrategyClient functionStrategy = new FunctionStrategyClient();

        switch (function) {
            case "sin(x)*cos(x)": {
                functionStrategy.setMethod(new FirstFunction());
                functionStrategy.getMethod().calc(intDataFromTextFieldA, intDataFromTextFieldB, doubleEpsilon);
                getData(functionStrategy);
                break;
            }
            case "cos(x)*tn(x)*sin(x)": {
                functionStrategy.setMethod(new SecondFunction());
                functionStrategy.getMethod().calc(intDataFromTextFieldA, intDataFromTextFieldB, doubleEpsilon);
                getData(functionStrategy);
                break;
            }
            case "x^3+x^2+x+10": {
                functionStrategy.setMethod(new ThirdFunction());
                functionStrategy.getMethod().calc(intDataFromTextFieldA, intDataFromTextFieldB, doubleEpsilon);
                getData(functionStrategy);
                break;
            }
        }
    }

    private void getData(FunctionStrategyClient functionStrategy) {
        leftIntegration = functionStrategy.getMethod().getLeftIntegration();
        rightIntegration = functionStrategy.getMethod().getRightIntegration();
        middleIntegration = functionStrategy.getMethod().getMiddleIntegration();
        numberOfSplit = functionStrategy.getMethod().getNumberOfSplit();
        numberOfError = functionStrategy.getMethod().getNumberOfError();
    }
}
