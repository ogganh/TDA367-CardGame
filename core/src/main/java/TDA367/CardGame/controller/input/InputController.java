package TDA367.CardGame.controller.input;

public class InputController {
    private InputStrategy strategy;

    public void setStrategy(InputStrategy strategy) {
        this.strategy = strategy;
    }

    public void handleInput() {
        strategy.handleInput();
    }
}
