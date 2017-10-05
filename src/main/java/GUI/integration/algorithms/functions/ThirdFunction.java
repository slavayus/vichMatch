package GUI.integration.algorithms.functions;

import GUI.integration.algorithms.FunctionStrategy;

import static java.lang.StrictMath.pow;

public class ThirdFunction extends FunctionStrategy {
    @Override
    public void integrate(int intDataFromTextFieldA, int intDataFromTextFieldB, double doubleEpsilon) {
        double functionArgument;
        for (double i = intDataFromTextFieldB; i < intDataFromTextFieldA; i += doubleEpsilon) {
            numberOfSplit++;
            functionArgument = i;
            rightIntegration += doubleEpsilon * (pow(functionArgument, 3) + pow(functionArgument, 2) + functionArgument + 10);
            functionArgument = i + (doubleEpsilon / 2);
            middleIntegration += doubleEpsilon * (pow(functionArgument, 3) + pow(functionArgument, 2) + functionArgument + 10);
            functionArgument = i + doubleEpsilon;
            leftIntegration += doubleEpsilon * (pow(functionArgument, 3) + pow(functionArgument, 2) + functionArgument + 10);
        }
    }
}
