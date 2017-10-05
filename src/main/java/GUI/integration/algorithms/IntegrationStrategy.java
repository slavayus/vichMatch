package GUI.integration.algorithms;

public interface IntegrationStrategy {
    float getLeftIntegration();

    float getMiddleIntegration();

    float getRightIntegration();

    int getNumberOfSplit();

    float getNumberOfError();

    void calc(int intDataFromTextFieldA, int intDataFromTextFieldB, double doubleEpsilon, String function);
}
