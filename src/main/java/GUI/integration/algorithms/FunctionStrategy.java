package GUI.integration.algorithms;

import static java.lang.StrictMath.abs;

public abstract class FunctionStrategy {
    protected float leftIntegration;
    protected float middleIntegration;
    protected float rightIntegration;
    protected int numberOfSplit;
    private float numberOfErrorLeft;
    private float numberOfErrorRight;
    private float numberOfErrorMiddle;

    float getLeftIntegration() {
        return this.leftIntegration;
    }

    float getMiddleIntegration() {
        return this.middleIntegration;
    }

    float getRightIntegration() {
        return this.rightIntegration;
    }

    int getNumberOfSplit() {
        return this.numberOfSplit;
    }

    float getNumberOfErrorLeft() {
        return this.numberOfErrorLeft;
    }

    float getNumberOfErrorRight() {
        return numberOfErrorRight;
    }

    float getNumberOfErrorMiddle() {
        return numberOfErrorMiddle;
    }

    void calc(int intDataFromTextFieldA, int intDataFromTextFieldB, double doubleEpsilon) {

        if (intDataFromTextFieldA == intDataFromTextFieldB) {
            return;
        }

        if (intDataFromTextFieldB > intDataFromTextFieldA) {
            integrate(intDataFromTextFieldB, intDataFromTextFieldA, doubleEpsilon);
            calcError(intDataFromTextFieldB, intDataFromTextFieldA, doubleEpsilon / 2);
            leftIntegration *= -1;
            rightIntegration *= -1;
            middleIntegration *= -1;
            return;
        }

        integrate(intDataFromTextFieldA, intDataFromTextFieldB, doubleEpsilon);
        calcError(intDataFromTextFieldA, intDataFromTextFieldB, doubleEpsilon / 4);

    }

    private void calcError(int intDataFromTextFieldA, int intDataFromTextFieldB, double epsilon) {
        float cacheLeftIntegration = leftIntegration;
        float cacheMiddleIntegration = middleIntegration;
        float cacheRightIntegration = rightIntegration;
        int cacheNumberOfSplit = numberOfSplit;

        leftIntegration = 0;
        rightIntegration = 0;
        middleIntegration = 0;

        integrate(intDataFromTextFieldA, intDataFromTextFieldB, epsilon);

        numberOfErrorLeft = 1f / 3f * abs(leftIntegration - cacheLeftIntegration);
        numberOfErrorMiddle = 1f / 3f * abs(middleIntegration - cacheMiddleIntegration);
        numberOfErrorRight = 1f / 3f * abs(rightIntegration - cacheRightIntegration);

        leftIntegration = cacheLeftIntegration;
        middleIntegration = cacheMiddleIntegration;
        rightIntegration = cacheRightIntegration;
        numberOfSplit = cacheNumberOfSplit;
    }

    protected abstract void integrate(int intDataFromTextFieldA, int intDataFromTextFieldB, double doubleEpsilon);

}
