package GUI.integration.algorithms.functions;

import GUI.integration.algorithms.FunctionStrategy;

import static java.lang.StrictMath.cos;
import static java.lang.StrictMath.sin;
import static java.lang.StrictMath.tan;

public class SecondFunction extends FunctionStrategy {

    @Override
    protected void integrate(int intDataFromTextFieldA, int intDataFromTextFieldB, double doubleEpsilon) {
        double functionArgument;
        for (double i = intDataFromTextFieldB; i < intDataFromTextFieldA; i += doubleEpsilon) {
            numberOfSplit++;
            functionArgument = i;
            rightIntegration += doubleEpsilon * cos(functionArgument) * tan(functionArgument) * sin(functionArgument);
            functionArgument = i + (doubleEpsilon / 2);
            middleIntegration += doubleEpsilon * cos(functionArgument) * tan(functionArgument) * sin(functionArgument);
            functionArgument = i + doubleEpsilon;
            leftIntegration += doubleEpsilon * cos(functionArgument) * tan(functionArgument) * sin(functionArgument);
        }
    }
}
