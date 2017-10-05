package GUI.integration.algorithms;

public class IntegrationStrategyClient {
    private IntegrationStrategy method;

    public IntegrationStrategyClient(IntegrationStrategy method) {
        this.method = method;
    }

    public IntegrationStrategy getMethod() {
        return method;
    }

    public void setMethod(IntegrationStrategy method) {
        this.method = method;
    }
}
