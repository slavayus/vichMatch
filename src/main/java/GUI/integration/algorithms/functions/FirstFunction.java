package GUI.integration.algorithms.functions;

import GUI.integration.algorithms.FunctionStrategy;

import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;

public class FirstFunction extends FunctionStrategy {


    @Override
    protected void integrate(int intDataFromTextFieldA, int intDataFromTextFieldB, double doubleEpsilon) {
        double functionArgument;
        for (double i = intDataFromTextFieldB; i < intDataFromTextFieldA; i += doubleEpsilon) {
            numberOfSplit++;
            functionArgument = i;
            rightIntegration += doubleEpsilon * sin(functionArgument) * cos(functionArgument);
            functionArgument = i + (doubleEpsilon / 2);
            middleIntegration += doubleEpsilon * sin(functionArgument) * cos(functionArgument);
            functionArgument = i + doubleEpsilon;
            leftIntegration += doubleEpsilon * sin(functionArgument) * cos(functionArgument);
        }
    }
}