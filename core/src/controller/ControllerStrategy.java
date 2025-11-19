public class ControllerStrategy {
    private GameStrategy strategy;

    setStrategy(GameStrategy strategy) {
        this.strategy = strategy;
    }

    void startGame() {
        strategy.startGame();
    }

    void endGame() {
        strategy.endGame();
    }

    void endTurn() {
        strategy.endTurn();
    }
}