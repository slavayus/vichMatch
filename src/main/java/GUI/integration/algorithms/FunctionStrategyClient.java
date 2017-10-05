package GUI.integration.algorithms;

public class FunctionStrategyClient {
    private FunctionStrategy function;

    public FunctionStrategyClient(FunctionStrategy function) {
        this.function = function;
    }

    FunctionStrategyClient() {

    }

    FunctionStrategy getMethod() {
        return function;
    }

    void setMethod(FunctionStrategy function) {
        this.function = function;
    }
}
